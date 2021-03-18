package cn.ewb.cq.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import cn.ewb.cq.com.HibernateUtil;
import cn.ewb.cq.com.StringUtil;
import cn.ewb.cq.com.SystemUtil;
import cn.ewb.cq.domain.Grammar;

public class GetGrammarDetail extends HttpServlet{
	
	private static Logger logger = Logger.getLogger(GetGrammarDetail.class);

	@Override
	protected synchronized  void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		final String methodName = "GetGrammarDetail()";
		String grammarId =  req.getParameter("grammarIdParam");
		Session sess = HibernateUtil.getSession();
		try{
			Grammar grammar = (Grammar)sess.get(Grammar.class, grammarId);
			JSONObject jObject=new JSONObject();
			JSONArray jArray = new JSONArray();
			
			jObject.put("titleName", grammar.getTitleName());
			jObject.put("followValue", grammar.getFollowValue());
			jObject.put("explainValue", grammar.getExplainValue());
			if(StringUtil.isNotNull(grammar.getExample1())){
				JSONObject children = new JSONObject();
				children.put("exampleValue", grammar.getExample1());
				jArray.add(children);
			}
			if(StringUtil.isNotNull(grammar.getExample2())){
				JSONObject children = new JSONObject();
				children.put("exampleValue", grammar.getExample2());
				jArray.add(children);
			}
			if(StringUtil.isNotNull(grammar.getExample3())){
				JSONObject children = new JSONObject();
				children.put("exampleValue", grammar.getExample3());
				jArray.add(children);
			}
			if(StringUtil.isNotNull(grammar.getExample4())){
				JSONObject children = new JSONObject();
				children.put("exampleValue", grammar.getExample4());
				jArray.add(children);
			}
			if(StringUtil.isNotNull(grammar.getExample5())){
				JSONObject children = new JSONObject();
				children.put("exampleValue", grammar.getExample5());
				jArray.add(children);
			}
			if(StringUtil.isNotNull(grammar.getExample6())){
				JSONObject children = new JSONObject();
				children.put("exampleValue", grammar.getExample6());
				jArray.add(children);
			}			
			
			jObject.put("exampleList", jArray);
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
	protected synchronized  void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		final String methodName = "GetGrammarDetail()";
		String grammarId =  req.getParameter("grammarIdParam");
		Session sess = HibernateUtil.getSession();
		try{
			Grammar grammar = (Grammar)sess.get(Grammar.class, grammarId);
			JSONObject jObject=new JSONObject();
			JSONArray jArray = new JSONArray();
			
			jObject.put("titleName", grammar.getTitleName());
			jObject.put("followValue", grammar.getFollowValue());
			jObject.put("explainValue", grammar.getExplainValue());
			if(StringUtil.isNotNull(grammar.getExample1())){
				JSONObject children = new JSONObject();
				children.put("exampleValue", grammar.getExample1());
				jArray.add(children);
			}
			if(StringUtil.isNotNull(grammar.getExample2())){
				JSONObject children = new JSONObject();
				children.put("exampleValue", grammar.getExample2());
				jArray.add(children);
			}
			if(StringUtil.isNotNull(grammar.getExample3())){
				JSONObject children = new JSONObject();
				children.put("exampleValue", grammar.getExample3());
				jArray.add(children);
			}
			if(StringUtil.isNotNull(grammar.getExample4())){
				JSONObject children = new JSONObject();
				children.put("exampleValue", grammar.getExample4());
				jArray.add(children);
			}
			if(StringUtil.isNotNull(grammar.getExample5())){
				JSONObject children = new JSONObject();
				children.put("exampleValue", grammar.getExample5());
				jArray.add(children);
			}
			if(StringUtil.isNotNull(grammar.getExample6())){
				JSONObject children = new JSONObject();
				children.put("exampleValue", grammar.getExample6());
				jArray.add(children);
			}			
			
			jObject.put("exampleList", jArray);
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
