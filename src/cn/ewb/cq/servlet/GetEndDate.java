package cn.ewb.cq.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import cn.ewb.cq.com.HibernateUtil;
import cn.ewb.cq.com.SystemUtil;
import cn.ewb.cq.domain.UserMember;

public class GetEndDate extends HttpServlet{
	
	private static Logger logger = Logger.getLogger(GetEndDate.class);
	
	@Override
	protected  synchronized void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		final String methodName = "GetEndDate()";
		
		String openId = req.getParameter("openId");
		Session sess = HibernateUtil.getSession();
		String menberMess = "";
		JSONObject jObject=new JSONObject();
		DateFormat df =  new SimpleDateFormat("yyyy-MM-dd");
		try{
			
			Criteria userMemberSearch = sess.createCriteria(UserMember.class);
			userMemberSearch.add(Restrictions.eq("openId", openId));
			userMemberSearch.add(Restrictions.eq("delFlg", "0"));
			userMemberSearch.addOrder(Order.desc("endDate"));
			List<UserMember> userMemberList = userMemberSearch.list();
			if(userMemberList!=null&&userMemberList.size()>0){
				Date endDate = userMemberList.get(0).getEndDate();
				Date now  = SystemUtil.getSystemTime();
				if(endDate.after(now)){
					menberMess = "会员到期日："  +  df.format(endDate);	
				}else{
					menberMess = "激活会员已到期，请续费";	
				}							
			}else{
				menberMess = "非激活会员";
			}
			jObject.put("menberMess", menberMess);
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
	protected synchronized void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		final String methodName = "GetEndDate()";
		
		String openId = req.getParameter("openId");
		Session sess = HibernateUtil.getSession();
		String menberMess = "";
		JSONObject jObject=new JSONObject();
		DateFormat df =  new SimpleDateFormat("yyyy-MM-dd");
		try{
			
			Criteria userMemberSearch = sess.createCriteria(UserMember.class);
			userMemberSearch.add(Restrictions.eq("openId", openId));
			userMemberSearch.add(Restrictions.eq("delFlg", "0"));
			userMemberSearch.addOrder(Order.desc("endDate"));
			List<UserMember> userMemberList = userMemberSearch.list();
			if(userMemberList!=null&&userMemberList.size()>0){
				Date endDate = userMemberList.get(0).getEndDate();
				Date now  = SystemUtil.getSystemTime();
				if(endDate.after(now)){
					menberMess = "会员到期日："  +  df.format(endDate);	
				}else{
					menberMess = "激活会员已到期，请续费";	
				}							
			}else{
				menberMess = "非激活会员";
			}
			jObject.put("menberMess", menberMess);
			resp.setContentType("text/html;charset=utf-8");
			PrintWriter pw = resp.getWriter();
			pw.println(jObject.toString());
		}catch(Exception ex){
			logger.error(SystemUtil.getErrorLog(req,this.getClass().getName(),methodName,ex.getMessage()));
		}finally{
			HibernateUtil.closeSession();
		}			
		
	}

}
