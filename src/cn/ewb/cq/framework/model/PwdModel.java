package cn.ewb.cq.framework.model;

import org.hibernate.Query;
import org.hibernate.Session;
import cn.ewb.cq.com.BaseModel;
import cn.ewb.cq.com.HibernateUtil;
import cn.ewb.cq.com.SystemUtil;
import cn.ewb.cq.domain.Pwd;
import cn.ewb.cq.domain.UserInfo;


public class PwdModel extends BaseModel{
	
	private static String HQL = "from UserInfo as U,Pwd as P where U.id = P.id and U.delFlg <> '1' and U.userCode = :userCode and P.pwd = :pwd ";
	
	public String searchUserId(Session sess,String userCode,String pwd)throws Exception{
		String userId = "";
//		Session sess = HibernateUtil.getSession();
		try{
			Query query = sess.createQuery(HQL);
			query.setParameter("userCode",userCode);
			query.setParameter("pwd",SystemUtil.pwdEncryption(pwd));
			
			Object userPwd = query.uniqueResult();
			if(userPwd!=null){
				UserInfo userinfo  = (UserInfo)((Object[])userPwd)[0];
				userId = userinfo.getId();
				//result = false;
			}
			return userId;		
		}finally{
//			if(sess!=null){
//				HibernateUtil.closeSession();
//			}
		}
	}
	
	public void pwdChange(Session sess,String userId,String newPwd,String userCode)throws Exception{
			Pwd pwd = (Pwd)getById(sess,Pwd.class, userId);
			pwd.setPwd(SystemUtil.pwdEncryption(newPwd));
			pwd.setUpdateId(userCode);
			pwd.setUpdateTime(SystemUtil.getSystemTime());
			update(sess,pwd);
	}
}
