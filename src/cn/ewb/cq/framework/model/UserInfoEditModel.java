package cn.ewb.cq.framework.model;

import org.hibernate.Session;
import org.hibernate.Transaction;
import cn.ewb.cq.com.BaseModel;
import cn.ewb.cq.com.HibernateUtil;
import cn.ewb.cq.com.SystemUtil;
import cn.ewb.cq.domain.UserInfo;
import cn.ewb.cq.framework.form.UserInfoEditForm;

public class UserInfoEditModel extends BaseModel{
	
	public void initUserInfoEdit(UserInfoEditForm userInfoEditForm)throws Exception{
		Session sess = HibernateUtil.getSession();
		try{
			String userCode = getLoginUser();
			UserInfo userInfo = (UserInfo)sess.get(UserInfo.class, userCode);	
			userInfoEditForm.setTelephone(userInfo.getTelephone());	
			userInfoEditForm.setEditId(userCode);
		}catch(Exception ex){
			throw ex;
		}finally{
			if(sess!=null){
				HibernateUtil.closeSession();
			}
		}
	}
	
	public void saveUserInfoEdit(UserInfoEditForm userInfoEditForm)throws Exception{
		Session sess = HibernateUtil.getSession();
		try{
			Transaction tx = sess.beginTransaction();
			String userCode = userInfoEditForm.getEditId();
			String telephone = userInfoEditForm.getTelephone();
			UserInfo userInfo =	(UserInfo)sess.get(UserInfo.class, userCode);
			userInfo.setTelephone(telephone);
			userInfo.setUpdateId(userCode);
			userInfo.setUpdateTime(SystemUtil.getSystemTime());
			sess.save(userInfo);
			tx.commit();
		}catch(Exception ex){
			throw ex;
		}finally{
			if(sess!=null){
				HibernateUtil.closeSession();
			}
		}
	}
}
