package cn.ewb.cq.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import cn.ewb.cq.com.HibernateUtil;
import cn.ewb.cq.com.SystemUtil;
import cn.ewb.cq.domain.UserMember;

public class SearchIsMember extends HttpServlet {
	
	private static Logger logger = Logger.getLogger(SearchIsMember.class);
	
	@Override
	protected synchronized  void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		final String methodName = "SearchIsMember()";
		String openId = req.getParameter("openId");
		Session sess = HibernateUtil.getSession();
		String memberFlg = "0";
		JSONObject jObject=new JSONObject();
		try{
			Criteria userMemberSearch = sess.createCriteria(UserMember.class);
			
			
			userMemberSearch.add(Restrictions.eq("openId", openId));
			userMemberSearch.add(Restrictions.eq("delFlg", "0"));
			userMemberSearch.add(Restrictions.ge("endDate", SystemUtil.getSystemTime()));
			
			List<UserMember> userMemberList = userMemberSearch.list();
			
			
			if(userMemberList!=null&&userMemberList.size()>0){
				memberFlg = "1";
			}
			
			
			
			jObject.put("memberFlg", memberFlg);
			
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
		final String methodName = "SearchIsMember()";
		String openId = req.getParameter("openId");
		Session sess = HibernateUtil.getSession();
		String memberFlg = "0";
		JSONObject jObject=new JSONObject();
		try{
			Criteria userMemberSearch = sess.createCriteria(UserMember.class);
			
			
			userMemberSearch.add(Restrictions.eq("openId", openId));
			userMemberSearch.add(Restrictions.eq("delFlg", "0"));
			userMemberSearch.add(Restrictions.ge("endDate", SystemUtil.getSystemTime()));
			
			List<UserMember> userMemberList = userMemberSearch.list();
			
			
			if(userMemberList!=null&&userMemberList.size()>0){
				memberFlg = "1";
			}
			
			
			
			jObject.put("memberFlg", memberFlg);
			
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
