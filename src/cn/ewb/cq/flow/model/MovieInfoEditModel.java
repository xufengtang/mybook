package cn.ewb.cq.flow.model;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import cn.ewb.cq.com.BaseModel;
import cn.ewb.cq.com.HibernateUtil;
import cn.ewb.cq.com.StringUtil;
import cn.ewb.cq.com.SystemUtil;
import cn.ewb.cq.domain.MovieInfo;
import cn.ewb.cq.domain.PubDict;
import cn.ewb.cq.flow.form.MovieInfoEditForm;

public class MovieInfoEditModel extends BaseModel {

	public void movieInfoEditInit( MovieInfoEditForm movieInfoEditForm)throws Exception{
		String editId = movieInfoEditForm.getEditId();
		Session sess = HibernateUtil.getSession();
		try{
			if(StringUtil.isNotNull(editId)){
				MovieInfo movieInfo = (MovieInfo)sess.get(MovieInfo.class, editId);
				movieInfoEditForm.setMovieType(movieInfo.getMovieType());
				movieInfoEditForm.setMovieName(movieInfo.getMovieName());
				movieInfoEditForm.setMovieUrl(movieInfo.getMovieUrl());
				movieInfoEditForm.setOrderNum(String.valueOf(movieInfo.getOrderNum()));				
			}
			getMovieType(sess,movieInfoEditForm);
		}catch(Exception ex){
			throw ex;
		}finally{
			HibernateUtil.closeSession();
		}
		
	}
	
	
	public void saveMovieInfo( MovieInfoEditForm movieInfoEditForm)throws Exception{
		String editId = movieInfoEditForm.getEditId();
		String movieType = movieInfoEditForm.getMovieType();
		String movieName = movieInfoEditForm.getMovieName();
		String movieUrl = movieInfoEditForm.getMovieUrl();
		String orderNum = movieInfoEditForm.getOrderNum();
		
		
		Session sess = HibernateUtil.getSession();
		try{
			Transaction tx  = sess.beginTransaction();
			if(StringUtil.isNotNull(editId)){
				MovieInfo movieInfo = (MovieInfo)sess.get(MovieInfo.class, editId);
				movieInfo.setMovieType(movieType);
				movieInfo.setMovieName(movieName);
				movieInfo.setMovieUrl(movieUrl);
				movieInfo.setOrderNum(Integer.parseInt(orderNum));
				movieInfo.setUpdateId(getLoginUser());
				movieInfo.setUpdateTime(SystemUtil.getSystemTime());
				
				sess.update(movieInfo);
			}else{
				MovieInfo movieInfo = new MovieInfo();
				movieInfo.setMovieType(movieType);
				movieInfo.setMovieName(movieName);
				movieInfo.setMovieUrl(movieUrl);
				movieInfo.setOrderNum(Integer.parseInt(orderNum));
				movieInfo.setDelFlg("0");
				movieInfo.setCreateId(getLoginUser());
				movieInfo.setCreateTime(SystemUtil.getSystemTime());
				movieInfo.setUpdateId(getLoginUser());
				movieInfo.setUpdateTime(SystemUtil.getSystemTime());
				
				sess.save(movieInfo);
			}
			tx.commit();
			getMovieType(sess,movieInfoEditForm);
		}catch(Exception ex){
			throw ex;
		}finally{
			HibernateUtil.closeSession();
		}

	}
	
	private void getMovieType(Session sess,MovieInfoEditForm movieInfoEditForm)throws Exception{
		List<PubDict> movieTypeList = getPubDict(sess, "MOVIE");
		movieInfoEditForm.setMovieTypeList(movieTypeList);
	}
}
