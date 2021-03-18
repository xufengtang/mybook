package cn.ewb.cq.framework.model;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cn.ewb.cq.com.BaseModel;
import cn.ewb.cq.com.HibernateUtil;
import cn.ewb.cq.com.SystemUtil;
import cn.ewb.cq.domain.Post;


public class PostModel extends BaseModel {

	public List<Post> searchPost(Session sess)throws Exception{
	//	Session sess = HibernateUtil.getSession();
		try{
			Criteria post = sess.createCriteria(Post.class);
			post.add(Restrictions.ne("delFlg", "1"));
			post.addOrder(Order.asc("postCode"));
			List<Post> postList = post.list();
			return postList;
		}finally{
//			if(sess!=null){
//				HibernateUtil.closeSession();
//			}
		}
	}
	
	public boolean checkExistPost(Session sess,String postCode)throws Exception{
		boolean ret = false;
		//Session sess = HibernateUtil.getSession();
		try{
			Criteria post = sess.createCriteria(Post.class);
			post.add(Restrictions.eq("postCode", postCode));
			if(post.uniqueResult()!=null){
				ret = true;
			}
			return ret;
		}finally{
//			if(sess!=null){
//				HibernateUtil.closeSession();
//			}
		}
	}
	
	public void addPost(Session sess,String postCode,String postName,String loginUser)throws Exception{
		Post post = new Post();
		post.setPostCode(postCode);
		post.setPostName(postName);
		post.setDelFlg("0");
		post.setCreateId(loginUser);
		post.setCreateTime(SystemUtil.getSystemTime());
		add(sess,post);
	}
	
	public void updatePost(Session sess,String postId,String postName,String loginUser)throws Exception{
		Post post = (Post)getById(sess,Post.class, postId);
		post.setPostName(postName);
		post.setUpdateId(loginUser);
		post.setUpdateTime(SystemUtil.getSystemTime());
		update(sess,post);	
	}
	
	public void delPost(Session sess,String postId,String loginUser)throws Exception{
		Post post = (Post)getById(sess,Post.class, postId);
		post.setDelFlg("1");
		post.setUpdateId(loginUser);
		post.setUpdateTime(SystemUtil.getSystemTime());
		update(sess,post);
	}
}
