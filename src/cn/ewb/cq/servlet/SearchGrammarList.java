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
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import cn.ewb.cq.com.HibernateUtil;
import cn.ewb.cq.com.SysConstant;
import cn.ewb.cq.com.SystemUtil;
import cn.ewb.cq.domain.Grammar;
import cn.ewb.cq.domain.UnitInfo;

public class SearchGrammarList extends HttpServlet{
	
	private static Logger logger = Logger.getLogger(SearchGrammarList.class);
	
	@Override
	protected synchronized  void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		final String methodName = "SearchGrammarList()";
		String searchKeyWord = req.getParameter("searchKeyWord");
		String  grammarId = req.getParameter("grammarIdParam");
		String currentPage = req.getParameter("currentPageParam");
		
		
		grammarId = grammarId.replace("[", "").replace("]", "").replace("\"","");
		String[] grammarIds = grammarId.split(",");
		
		Session sess = HibernateUtil.getSession();
		try{
			
			List <Grammar> grammarList= getGrammarList(sess,searchKeyWord,currentPage);
			int totalPage = getTotalPage(sess,searchKeyWord);
			
			JSONObject jObject=new JSONObject();
			JSONArray jArray = new JSONArray();
			
			if(grammarList!=null && grammarList.size()>0){
				for(int i=0;i<grammarList.size();i++){
					Grammar  grammar = grammarList.get(i);
					UnitInfo unitInfo  = grammar.getUnitInfo();
					String unitInfoName = unitInfo.getLevelId()+"级"+ unitInfo.getUnitName();

					JSONObject children = new JSONObject();
					children.put("grammarId", grammar.getId());
					children.put("titleName", grammar.getTitleName());
					children.put("checkStatus", false);
					children.put("unitInfoName", unitInfoName);
					
					for(int j=0;j<grammarIds.length;j++){
						if(grammar.getId().equals(grammarIds[j])){
							children.put("checkStatus", true);
						}
					}
					jArray.add(children);
				}
			}
			
			jObject.put("grammarList", jArray);
			
			
			jObject.put("totalPage", totalPage);
			
			if(Integer.parseInt(currentPage)==1){
				jObject.put("prePageStatus", true);
				
			}else{
				jObject.put("prePageStatus", false);
			}
			if(Integer.parseInt(currentPage)==totalPage){
				jObject.put("nextPageStatus", true);			
			}else{
				jObject.put("nextPageStatus", false);
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
	protected synchronized  void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		final String methodName = "SearchGrammarList()";
		String searchKeyWord = req.getParameter("searchKeyWord");
		String  grammarId = req.getParameter("grammarIdParam");
		String currentPage = req.getParameter("currentPageParam");
		
		
		grammarId = grammarId.replace("[", "").replace("]", "").replace("\"","");
		String[] grammarIds = grammarId.split(",");
		
		Session sess = HibernateUtil.getSession();
		try{
			
			List <Grammar> grammarList= getGrammarList(sess,searchKeyWord,currentPage);
			int totalPage = getTotalPage(sess,searchKeyWord);
			
			JSONObject jObject=new JSONObject();
			JSONArray jArray = new JSONArray();
			
			if(grammarList!=null && grammarList.size()>0){
				for(int i=0;i<grammarList.size();i++){
					Grammar  grammar = grammarList.get(i);
					UnitInfo unitInfo  = grammar.getUnitInfo();
					String unitInfoName = unitInfo.getLevelId()+"级"+ unitInfo.getUnitName();

					JSONObject children = new JSONObject();
					children.put("grammarId", grammar.getId());
					children.put("titleName", grammar.getTitleName());
					children.put("checkStatus", false);
					children.put("unitInfoName", unitInfoName);
					
					for(int j=0;j<grammarIds.length;j++){
						if(grammar.getId().equals(grammarIds[j])){
							children.put("checkStatus", true);
						}
					}
					jArray.add(children);
				}
			}
			
			jObject.put("grammarList", jArray);
			
			
			jObject.put("totalPage", totalPage);
			
			if(Integer.parseInt(currentPage)==1){
				jObject.put("prePageStatus", true);
				
			}else{
				jObject.put("prePageStatus", false);
			}
			if(Integer.parseInt(currentPage)==totalPage){
				jObject.put("nextPageStatus", true);			
			}else{
				jObject.put("nextPageStatus", false);
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
	
	
	private List<Grammar> getGrammarList(Session sess,String searchKeyWord,String currentPage)throws Exception{
		int currentPageNum = Integer.parseInt(currentPage);
		Criteria grammarSearch = sess.createCriteria(Grammar.class);
		grammarSearch.add(Restrictions.ilike("titleName", searchKeyWord,MatchMode.ANYWHERE));
		grammarSearch.add(Restrictions.ne("delFlg", "1"));
		grammarSearch.setFirstResult((currentPageNum-1)* SysConstant.PAGE2);
		grammarSearch.setMaxResults(SysConstant.PAGE2);
		List <Grammar> grammarList= grammarSearch.list();
		return  grammarList;	
	}
	
	
	private int getTotalPage(Session sess,String searchKeyWord)throws Exception{
		 String hql = " select count(id) from Grammar where titleName like :titleName and delFlg <> '1' ";
		 Query q = sess.createQuery(hql);
		 q.setParameter("titleName", "%"+searchKeyWord+"%");
		 int totalNum = Integer.parseInt(q.iterate().next().toString());
		 int totalPage = totalNum%SysConstant.PAGE2==0?totalNum/SysConstant.PAGE2:totalNum/SysConstant.PAGE2 + 1;
		 // 没有数据情况
		 if(totalPage==0){
			 totalPage = 1;
		 }
		 return totalPage;		
	}
}
