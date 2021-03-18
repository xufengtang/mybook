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
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import cn.ewb.cq.com.HibernateUtil;
import cn.ewb.cq.com.StringUtil;
import cn.ewb.cq.com.SysConstant;
import cn.ewb.cq.com.SystemUtil;
import cn.ewb.cq.domain.ListenInfo;

public class SearchListenList extends HttpServlet{
	
	private static Logger logger = Logger.getLogger(SearchListenList.class);
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		final String methodName = "SearchListenList()";
		
		String levelIdSearch = req.getParameter("levelIdParam");
		String currentPage = req.getParameter("currentPageParam");
		
		Session sess= HibernateUtil.getSession();
		try{
			
			List<ListenInfo> listenInfoList = getListenList(sess,levelIdSearch,currentPage);
			int totalPage = getTotalPage(sess,levelIdSearch);
			
			JSONObject jObject=new JSONObject();
			JSONArray jArray = new JSONArray();
			
			if(listenInfoList!=null && listenInfoList.size()>0){
				for(int i=0;i<listenInfoList.size();i++){
					ListenInfo listenInfo = listenInfoList.get(i);
					
					JSONObject children = new JSONObject();
					children.put("listenInfoId", listenInfo.getId());
					children.put("listenName", listenInfo.getListenName());
					jArray.add(children);
				}
			}
			
			jObject.put("listenInfoList", jArray);

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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		final String methodName = "SearchListenList()";
		
		String levelIdSearch = req.getParameter("levelIdSearch");
		String currentPage = req.getParameter("currentPageParam");
		
		Session sess= HibernateUtil.getSession();
		try{
			
			List<ListenInfo> listenInfoList = getListenList(sess,levelIdSearch,currentPage);
			int totalPage = getTotalPage(sess,levelIdSearch);
			
			JSONObject jObject=new JSONObject();
			JSONArray jArray = new JSONArray();
			
			if(listenInfoList!=null && listenInfoList.size()>0){
				for(int i=0;i<listenInfoList.size();i++){
					ListenInfo listenInfo = listenInfoList.get(i);
					
					JSONObject children = new JSONObject();
					children.put("listenInfoId", listenInfo.getId());
					children.put("listenName", listenInfo.getListenName());
					jArray.add(children);
				}
			}
			
			jObject.put("listenInfoList", jArray);

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
	
	private List<ListenInfo> getListenList(Session sess,String levelIdSearch,String currentPage)throws Exception{
		int currentPageNum = Integer.parseInt(currentPage);
		Criteria listenInfoSearch = sess.createCriteria(ListenInfo.class);
		listenInfoSearch.add(Restrictions.eq("levelId", levelIdSearch));
		listenInfoSearch.add(Restrictions.eq("delFlg", "0"));
		listenInfoSearch.addOrder(Order.asc("orderNum"));
		listenInfoSearch.setFirstResult((currentPageNum-1)* SysConstant.PAGE2);
		listenInfoSearch.setMaxResults(SysConstant.PAGE2);		
		List<ListenInfo> listenInfoList = listenInfoSearch.list();
		return listenInfoList;
	}
	
	
	private int getTotalPage(Session sess,String levelIdSearch)throws Exception{
		 String hql = " select count(id) from ListenInfo  where levelId= :levelId and delFlg= '0' ";
		// 页数计算
		Query q = sess.createQuery(hql);
		if(StringUtil.isNotNull(levelIdSearch)){
		 	q.setParameter("levelId", levelIdSearch);
		}
		int totalNum = Integer.parseInt(q.iterate().next().toString());
		int totalPage = totalNum%SysConstant.PAGE2==0?totalNum/SysConstant.PAGE2:totalNum/SysConstant.PAGE2 + 1;
		// 没有数据情况
		if(totalPage==0){
			totalPage = 1;
		}
		return totalPage;		
	}
}
