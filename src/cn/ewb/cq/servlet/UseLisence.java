package cn.ewb.cq.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import cn.ewb.cq.com.HibernateUtil;
import cn.ewb.cq.com.StringUtil;
import cn.ewb.cq.com.SystemUtil;
import cn.ewb.cq.domain.LisenceInfo;
import cn.ewb.cq.domain.UserMember;

public class UseLisence extends HttpServlet {
	
	private static Logger logger = Logger.getLogger(UseLisence.class);
	
	
	@Override
	protected synchronized  void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		final String methodName = "UseLisence()";
		String lisenceWord1 = req.getParameter("lisenceWord1");
		String lisenceWord2 = req.getParameter("lisenceWord2");
		String lisenceWord3 = req.getParameter("lisenceWord3");
		//String lisenceWord4 = req.getParameter("lisenceWord4");
		String openId = req.getParameter("openId");
		String nickName = req.getParameter("nickName");
		
	    String lisenceWord = lisenceWord1 +"-"+ lisenceWord2+"-"+ lisenceWord3;
		
		Session sess = HibernateUtil.getSession();
		Transaction tx  = sess.beginTransaction();
		
		try{
			
			Criteria lisenceInfoSearch = sess.createCriteria(LisenceInfo.class);
			lisenceInfoSearch.add(Restrictions.eq("lisenceCode", lisenceWord));
			lisenceInfoSearch.add(Restrictions.isNull("useDate"));
			List<LisenceInfo>  lisenceInfoList = lisenceInfoSearch.list();
			JSONObject jObject=new JSONObject();
			if(lisenceInfoList!=null&&lisenceInfoList.size()>0){
				LisenceInfo  lisenceInfo = lisenceInfoList.get(0);
				String lisenceCode = lisenceInfo.getLisenceCode();
				saveUserMember(sess,openId,lisenceCode,nickName);
				// 修改激活码使用信息
				lisenceInfo.setOpenId(openId);
				lisenceInfo.setUseDate(SystemUtil.getSystemTime());
				sess.update(lisenceInfo);
				
				tx.commit();
				jObject.put("successFlg", "1");
			}else{
				
				jObject.put("successFlg", "0");
			}
			
			resp.setContentType("text/html;charset=utf-8");
			PrintWriter pw = resp.getWriter();
			pw.println(jObject.toString());
		}catch(Exception ex){		
			logger.error(SystemUtil.getErrorLog(req,this.getClass().getName(),methodName,ex.getMessage()));	
		}finally{
			HibernateUtil.closeSession();
		}
	}
	
	
	
	
	

	@Override
	protected  synchronized  void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		final String methodName = "UseLisence()";
		String lisenceWord1 = req.getParameter("lisenceWord1");
		String lisenceWord2 = req.getParameter("lisenceWord2");
		String lisenceWord3 = req.getParameter("lisenceWord3");
	//	String lisenceWord4 = req.getParameter("lisenceWord4");
		String openId = req.getParameter("openId");
		String nickName = req.getParameter("nickName");
		
	    String lisenceWord = lisenceWord1 +"-"+ lisenceWord2+"-"+ lisenceWord3;
		
		Session sess = HibernateUtil.getSession();
		Transaction tx  = sess.beginTransaction();
		
		try{
			
			Criteria lisenceInfoSearch = sess.createCriteria(LisenceInfo.class);
			lisenceInfoSearch.add(Restrictions.eq("lisenceCode", lisenceWord));
			lisenceInfoSearch.add(Restrictions.isNull("useDate"));
			List<LisenceInfo>  lisenceInfoList = lisenceInfoSearch.list();
			JSONObject jObject=new JSONObject();
			if(lisenceInfoList!=null&&lisenceInfoList.size()>0){
				LisenceInfo  lisenceInfo = lisenceInfoList.get(0);
				String lisenceCode = lisenceInfo.getLisenceCode();
				saveUserMember(sess,openId,lisenceCode,nickName);
				// 修改激活码使用信息
				lisenceInfo.setOpenId(openId);
				lisenceInfo.setUseDate(SystemUtil.getSystemTime());
				sess.update(lisenceInfo);
				tx.commit();
				jObject.put("successFlg", "1");
			}else{
				
				jObject.put("successFlg", "0");
			}
			
			resp.setContentType("text/html;charset=utf-8");
			PrintWriter pw = resp.getWriter();
			pw.println(jObject.toString());
		}catch(Exception ex){		
			logger.error(SystemUtil.getErrorLog(req,this.getClass().getName(),methodName,ex.getMessage()));	
		}finally{
			HibernateUtil.closeSession();
		}
	}
	
	
	
	
	private void saveUserMember(Session sess,String openId,String lisenceCode,String nickName)throws Exception{
		DateFormat df  = new SimpleDateFormat("yyyy-MM-dd");
		String lisenceYear = SystemUtil.getProperties(null).getProperty("LISENCE_YEAR");
		int lisenceYearNum = Integer.parseInt(lisenceYear);
		
		Criteria userMemberSearch = sess.createCriteria(UserMember.class);
		userMemberSearch.add(Restrictions.eq("openId", openId));
		userMemberSearch.add(Restrictions.eq("delFlg", "0"));
		UserMember userMember =(UserMember) userMemberSearch.uniqueResult();
		// 数据存在情况
		if(userMember!=null){
			
			userMember.setLisenceCode(lisenceCode);
			userMember.setNickName(nickName);
			userMember.setStartDate(userMember.getEndDate());
			userMember.setEndDate(df.parse(StringUtil.addYear(df.format(userMember.getEndDate()),lisenceYearNum)));	
			userMember.setUpdateTime(SystemUtil.getSystemTime());
			sess.update(userMember);
		// 不存在情况	
		}else{
			
			UserMember userMemberNew  = new UserMember();
			
			userMemberNew.setOpenId(openId);
			userMemberNew.setLisenceCode(lisenceCode);
			userMemberNew.setNickName(nickName);
			userMemberNew.setStartDate(SystemUtil.getSystemTime());
			userMemberNew.setEndDate(df.parse(StringUtil.addYear(df.format(SystemUtil.getSystemTime()),lisenceYearNum)));
			userMemberNew.setCreateId("admin");
			userMemberNew.setCreateTime(SystemUtil.getSystemTime());
			userMemberNew.setUpdateId("admin");
			userMemberNew.setUpdateTime(SystemUtil.getSystemTime());
			userMemberNew.setDelFlg("0");	
			sess.save(userMemberNew);
		
		}
		
		
	}
}
