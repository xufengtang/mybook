package cn.ewb.cq.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import net.sf.json.JSONObject;
import cn.ewb.cq.com.HttpRequestor;
import cn.ewb.cq.com.SystemUtil;


public class GetOpenId extends HttpServlet {

	
	private final String APPID = "wx3d5bde88815e29ed";
	private final String SECRET ="55457e3069e17a9da81c58f9912be1cd";
	private static Logger logger = Logger.getLogger(GetOpenId.class);
	
	@Override
	protected  synchronized  void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		final String methodName = "GetOpenId()";
		try{
			
			String code = req.getParameter("code");
			String requestUrl = "https://api.weixin.qq.com/sns/jscode2session?appid=" + APPID + "&secret=" + SECRET+"&js_code="+code+"&grant_type=authorization_code";
	        //HttpRequestor是一个网络请求工具类，贴在了下面
	        String returnValue = new HttpRequestor().doGet(requestUrl);
	        JSONObject obj = JSONObject.fromObject(returnValue);
	        
	        logger.info(returnValue);
	        
			resp.setContentType("text/html;charset=utf-8");
			PrintWriter pw = resp.getWriter();
			pw.println(obj.toString());
			
			
		}catch(Exception ex){
			logger.error(SystemUtil.getErrorLog(req,this.getClass().getName(),methodName,ex.getMessage()));
		}

	}

	@Override
	protected  synchronized  void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		final String methodName = "GetOpenId()";
		try{
			
			String code = req.getParameter("code");
			String requestUrl = "https://api.weixin.qq.com/sns/jscode2session?appid=" + APPID + "&secret=" + SECRET+"&js_code="+code+"&grant_type=authorization_code";
	        //HttpRequestor是一个网络请求工具类，贴在了下面
	        String returnValue = new HttpRequestor().doGet(requestUrl);
	        JSONObject obj = JSONObject.fromObject(returnValue);
	        
	        logger.info(returnValue);
	        
			resp.setContentType("text/html;charset=utf-8");
			PrintWriter pw = resp.getWriter();
			pw.println(obj.toString());
			
			
		}catch(Exception ex){
			logger.error(SystemUtil.getErrorLog(req,this.getClass().getName(),methodName,ex.getMessage()));
		}

	}

}
