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
import cn.ewb.cq.domain.MovieInfo;

public class GetMovieInfo extends HttpServlet {

	
	private static Logger logger = Logger.getLogger(GetMovieInfo.class);
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		final String methodName = "GetMovieInfo()";
		String movieInfoId = req.getParameter("movieInfoId");
		JSONObject jObject=new JSONObject();
		Session sess= HibernateUtil.getSession();
		try{
			MovieInfo movieInfo = (MovieInfo)sess.get(MovieInfo.class, movieInfoId);
			
			if(movieInfo!=null){
				jObject.put("movieName", movieInfo.getMovieName());
				jObject.put("movieUrl", movieInfo.getMovieUrl());
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
		final String methodName = "GetMovieInfo()";
		String movieInfoId = req.getParameter("movieInfoId");
		JSONObject jObject=new JSONObject();
		Session sess= HibernateUtil.getSession();
		try{
			MovieInfo movieInfo = (MovieInfo)sess.get(MovieInfo.class, movieInfoId);
			
			if(movieInfo!=null){
				jObject.put("movieName", movieInfo.getMovieName());
				jObject.put("movieUrl", movieInfo.getMovieUrl());
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
