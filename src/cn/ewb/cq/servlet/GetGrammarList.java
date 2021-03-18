package cn.ewb.cq.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import cn.ewb.cq.com.HibernateUtil;
import cn.ewb.cq.com.SystemUtil;
import cn.ewb.cq.domain.Grammar;
import cn.ewb.cq.domain.UnitInfo;




public class GetGrammarList  extends HttpServlet {
	
	private static Logger logger = Logger.getLogger(GetGrammarList.class);
	@Override
	protected synchronized  void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		final String methodName = "GetGrammarList()";
		String unitInfoId =  req.getParameter("unitInfoIdParam");
		String grammarId = req.getParameter("grammarIdParam");
		
		grammarId = grammarId.replace("[", "").replace("]", "").replace("\"","");
		String[] grammarIds = grammarId.split(",");
	
		Session sess = HibernateUtil.getSession();
		
		try{
			UnitInfo unitInfo = (UnitInfo)sess.get(UnitInfo.class, unitInfoId);
			//List<Grammar> grammarList = unitInfo.getGrammar();
			Criteria grammarSearch = sess.createCriteria(Grammar.class);
			grammarSearch.add(Restrictions.eq("unitInfo",unitInfo));
			grammarSearch.add(Restrictions.eq("delFlg","0"));	
			grammarSearch.addOrder(Order.asc("orderNum"));
			List<Grammar> grammarList = grammarSearch.list();
			
			
			JSONObject jObject=new JSONObject();
			JSONArray jArray = new JSONArray();
			
			if(grammarList!=null && grammarList.size()>0){
				for(int i=0;i<grammarList.size();i++){
					Grammar  grammar = grammarList.get(i);
					JSONObject children = new JSONObject();
					children.put("grammarId", grammar.getId());
					children.put("titleName", grammar.getTitleName());
					children.put("checkStatus", false);
					for(int j=0;j<grammarIds.length;j++){
						if(grammar.getId().equals(grammarIds[j])){
							children.put("checkStatus", true);
						}
						
					}
					
					
					jArray.add(children);
				}
			}
			
			jObject.put("grammarList", jArray);
			
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
		final String methodName = "GetGrammarList()";
		String unitInfoId =  req.getParameter("unitInfoIdParam");
		String grammarId = req.getParameter("grammarIdParam");
		
		grammarId = grammarId.replace("[", "").replace("]", "").replace("\"","");
		String[] grammarIds = grammarId.split(",");
	
		Session sess = HibernateUtil.getSession();
		
		try{
			UnitInfo unitInfo = (UnitInfo)sess.get(UnitInfo.class, unitInfoId);
			//List<Grammar> grammarList = unitInfo.getGrammar();
			Criteria grammarSearch = sess.createCriteria(Grammar.class);
			grammarSearch.add(Restrictions.eq("unitInfo",unitInfo));
			grammarSearch.add(Restrictions.eq("delFlg","0"));	
			grammarSearch.addOrder(Order.asc("orderNum"));
			List<Grammar> grammarList = grammarSearch.list();
			
			
			JSONObject jObject=new JSONObject();
			JSONArray jArray = new JSONArray();
			
			if(grammarList!=null && grammarList.size()>0){
				for(int i=0;i<grammarList.size();i++){
					Grammar  grammar = grammarList.get(i);
					JSONObject children = new JSONObject();
					children.put("grammarId", grammar.getId());
					children.put("titleName", grammar.getTitleName());
					children.put("checkStatus", false);
					for(int j=0;j<grammarIds.length;j++){
						if(grammar.getId().equals(grammarIds[j])){
							children.put("checkStatus", true);
						}
						
					}
					
					
					jArray.add(children);
				}
			}
			
			jObject.put("grammarList", jArray);
			
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
