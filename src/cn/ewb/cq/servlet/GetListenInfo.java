package cn.ewb.cq.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cn.ewb.cq.com.HibernateUtil;
import cn.ewb.cq.com.SystemUtil;
import cn.ewb.cq.domain.ListenInfo;

public class GetListenInfo extends HttpServlet {

	
	private static Logger logger = Logger.getLogger(GetListenInfo.class);
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		final String methodName = "GetListenInfo()";
		String listenInfoId = req.getParameter("listenInfoId");
		JSONObject jObject=new JSONObject();
		Session sess= HibernateUtil.getSession();
		try{
			ListenInfo listenInfo = (ListenInfo)sess.get(ListenInfo.class, listenInfoId);
			
			if(listenInfo!=null){
				jObject.put("listenName", listenInfo.getListenName());
				jObject.put("listenUrl", listenInfo.getListenUrl());
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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		final String methodName = "GetListenInfo()";
		String listenInfoId = req.getParameter("listenInfoId");
		JSONObject jObject=new JSONObject();
		Session sess= HibernateUtil.getSession();
		try{
			ListenInfo listenInfo = (ListenInfo)sess.get(ListenInfo.class, listenInfoId);
			
			if(listenInfo!=null){
				jObject.put("listenName", listenInfo.getListenName());
				jObject.put("listenUrl", listenInfo.getListenUrl());
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
	
	

}
