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
import cn.ewb.cq.com.HibernateUtil;
import cn.ewb.cq.com.SystemUtil;
import cn.ewb.cq.domain.NHKInfo;

public class GetNhkUrl extends HttpServlet{
	
	private static Logger logger = Logger.getLogger(GetNhkUrl.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		final String methodName = "GetNhkUrl()";
		Session sess = HibernateUtil.getSession();
		String urlName ="";
		JSONObject jObject=new JSONObject();
		try{
			Criteria nhkInfoSearch = sess.createCriteria(NHKInfo.class);
			List<NHKInfo> nhkInfoList = nhkInfoSearch.list();
			
			if(nhkInfoList!=null&&nhkInfoList.size()>0){
				NHKInfo nhkInfo = nhkInfoList.get(0);

				urlName = nhkInfo.getUrlName();
			}
			
			jObject.put("urlName", urlName);
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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		final String methodName = "GetNhkUrl()";
		Session sess = HibernateUtil.getSession();
		String urlName ="";
		JSONObject jObject=new JSONObject();
		try{
			Criteria nhkInfoSearch = sess.createCriteria(NHKInfo.class);
			List<NHKInfo> nhkInfoList = nhkInfoSearch.list();
			
			if(nhkInfoList!=null&&nhkInfoList.size()>0){
				NHKInfo nhkInfo = nhkInfoList.get(0);

				urlName = nhkInfo.getUrlName();
			}
			
			jObject.put("urlName", urlName);
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
