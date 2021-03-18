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
import cn.ewb.cq.domain.MovieInfo;

public class SearchMovieList extends HttpServlet{
	
	private static Logger logger = Logger.getLogger(SearchMovieList.class);
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		final String methodName = "SearchMovieList()";
		
		String movieTypeSearch = req.getParameter("movieTypeParam");
		String currentPage = req.getParameter("currentPageParam");
		
		Session sess= HibernateUtil.getSession();
		try{
			
			List<MovieInfo> movieInfoList = getMovieList(sess,movieTypeSearch,currentPage);
			int totalPage = getTotalPage(sess,movieTypeSearch);
			
			JSONObject jObject=new JSONObject();
			JSONArray jArray = new JSONArray();
			
			if(movieInfoList!=null && movieInfoList.size()>0){
				for(int i=0;i<movieInfoList.size();i++){
					MovieInfo movieInfo = movieInfoList.get(i);
					
					JSONObject children = new JSONObject();
					children.put("movieInfoId", movieInfo.getId());
					children.put("movieName", movieInfo.getMovieName());
					jArray.add(children);
				}
			}
			
			jObject.put("movieInfoList", jArray);

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
		final String methodName = "SearchMovieList()";
		
		String movieTypeSearch = req.getParameter("movieTypeParam");
		String currentPage = req.getParameter("currentPageParam");
		
		Session sess= HibernateUtil.getSession();
		try{
			
			List<MovieInfo> movieInfoList = getMovieList(sess,movieTypeSearch,currentPage);
			int totalPage = getTotalPage(sess,movieTypeSearch);
			
			JSONObject jObject=new JSONObject();
			JSONArray jArray = new JSONArray();
			
			if(movieInfoList!=null && movieInfoList.size()>0){
				for(int i=0;i<movieInfoList.size();i++){
					MovieInfo movieInfo = movieInfoList.get(i);
					
					JSONObject children = new JSONObject();
					children.put("movieInfoId", movieInfo.getId());
					children.put("movieName", movieInfo.getMovieName());
					jArray.add(children);
				}
			}
			
			jObject.put("movieInfoList", jArray);

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






	private List<MovieInfo> getMovieList(Session sess,String movieTypeSearch,String currentPage)throws Exception{
		int currentPageNum = Integer.parseInt(currentPage);
		
		
		Criteria movieInfoSearch = sess.createCriteria(MovieInfo.class);
		movieInfoSearch.add(Restrictions.eq("movieType", movieTypeSearch));
		movieInfoSearch.add(Restrictions.eq("delFlg", "0"));
		movieInfoSearch.addOrder(Order.asc("orderNum"));
		movieInfoSearch.setFirstResult((currentPageNum-1)* SysConstant.PAGE2);
		movieInfoSearch.setMaxResults(SysConstant.PAGE2);		
		List<MovieInfo> movieInfoList = movieInfoSearch.list();
		return movieInfoList;
	}
	
	
	private int getTotalPage(Session sess,String movieTypeSearch)throws Exception{
		 String hql = " select count(id) from MovieInfo  where movieType= :movieType and delFlg= '0' ";
		// 页数计算
		Query q = sess.createQuery(hql);
		if(StringUtil.isNotNull(movieTypeSearch)){
		 	q.setParameter("movieType", movieTypeSearch);
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
