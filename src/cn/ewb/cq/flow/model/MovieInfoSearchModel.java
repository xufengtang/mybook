package cn.ewb.cq.flow.model;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import cn.ewb.cq.com.BaseModel;
import cn.ewb.cq.com.HibernateUtil;
import cn.ewb.cq.com.StringUtil;
import cn.ewb.cq.com.SysConstant;
import cn.ewb.cq.com.SystemUtil;
import cn.ewb.cq.domain.ListenInfo;
import cn.ewb.cq.domain.MovieInfo;
import cn.ewb.cq.domain.PubDict;


import cn.ewb.cq.flow.form.ListenInfoSearchForm;
import cn.ewb.cq.flow.form.MovieInfoSearchForm;

public class MovieInfoSearchModel extends BaseModel {

	
	public void searchMovieInfoInit(MovieInfoSearchForm movieInfoSearchForm)throws Exception{
		
		Session sess = HibernateUtil.getSession();
		try{
			getMovieType(sess,movieInfoSearchForm);
		}catch(Exception ex){
			throw ex;
		}finally{
			HibernateUtil.closeSession();
		}
	}
	
	
	public List<MovieInfo> searchMovieInfo(Session sess,MovieInfoSearchForm movieInfoSearchForm )throws Exception{

		String  MovieTypeSearch  =  movieInfoSearchForm.getMovieTypeSearch();
		
		try{
			
			Criteria movieInfoSearch = sess.createCriteria(MovieInfo.class);
			movieInfoSearch.add(Restrictions.eq("movieType", MovieTypeSearch));
			movieInfoSearch.add(Restrictions.eq("delFlg", "0"));
			movieInfoSearch.addOrder(Order.asc("orderNum"));
			movieInfoSearch.setFirstResult((movieInfoSearchForm.getCurrentPage()-1)* SysConstant.PAGE);
			movieInfoSearch.setMaxResults(SysConstant.PAGE);		
			List<MovieInfo> movieInfoList = movieInfoSearch.list();	
			return movieInfoList;
		}catch(Exception ex){	
			throw ex;
		}
	}
	
	
	public int selectTotalPage(Session sess,MovieInfoSearchForm movieInfoSearchForm )throws Exception{
		String MovieTypeSearch  =  movieInfoSearchForm.getMovieTypeSearch();
		String hql = " select count(id) from MovieInfo  where movieType= :movieType and delFlg= '0' ";
		try{
			
			// Ò³Êý¼ÆËã
			Query q = sess.createQuery(hql);
			if(StringUtil.isNotNull(MovieTypeSearch)){
				q.setParameter("movieType", MovieTypeSearch);
			}
			
			
			int totalNum = Integer.parseInt(q.iterate().next().toString());
			int totalPage = totalNum%SysConstant.PAGE==0?totalNum/SysConstant.PAGE:totalNum/SysConstant.PAGE+1;
			return totalPage;
			
		}catch(Exception ex){
			throw ex;
		}
	}
	
	
	public void movieInfoDel(String editId,MovieInfoSearchForm movieInfoSearchForm,Session sess)throws Exception{
		try{		
			Transaction tx = sess.beginTransaction();	
			if(StringUtil.isNotNull(editId)){			
				MovieInfo movieInfo  = (MovieInfo)sess.get(MovieInfo.class, editId);
				movieInfo.setDelFlg("1");
				movieInfo.setUpdateId(getLoginUser());
				movieInfo.setUpdateTime(SystemUtil.getSystemTime());
				sess.update(movieInfo);
			}
			tx.commit();
		}catch(Exception ex){
			throw ex;
		}
	}
	
	
	
	
	public void getMovieType(Session sess,MovieInfoSearchForm movieInfoSearchForm)throws Exception{
		List<PubDict> movieTypeList = getPubDict(sess, "MOVIE");
		movieInfoSearchForm.setMovieTypeList(movieTypeList);
	}
	
}
