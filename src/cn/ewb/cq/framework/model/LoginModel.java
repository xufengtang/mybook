package cn.ewb.cq.framework.model;

import org.hibernate.Query;
import org.hibernate.Session;
import cn.ewb.cq.com.BaseModel;
import cn.ewb.cq.com.HibernateUtil;
import cn.ewb.cq.com.SystemUtil;
import cn.ewb.cq.domain.Pwd;
import cn.ewb.cq.domain.UserInfo;

public class LoginModel extends BaseModel{
	private static String HQL = "from UserInfo as U,Pwd as P where U.id = P.id and U.delFlg <> '1' and U.userCode = :userCode  ";
	//private static String HQL = "from UserInfo as U,Pwd as P where U.id = P.id and U.delFlg <> '1' and U.userCode = :userCode and P.pwd = :pwd ";
	
	public String[] checkLogin(String userCode,String pwd,String comPwd) throws Exception {
		String[] successUserCode = new String[3];
		Session sess = HibernateUtil.getSession();
		try{
			Query query = sess.createQuery(HQL);
			query.setParameter("userCode",userCode);
			//query.setParameter("pwd",SystemUtil.pwdEncryption(pwd));
			
			Object result = query.uniqueResult();
			if(result!=null && comPwd.equals(pwd)){
				UserInfo userinfo  = (UserInfo)((Object[])result)[0];
				Pwd userPwd = (Pwd)((Object[])result)[1];
				successUserCode[0] = userinfo.getUserCode();
				successUserCode[1] = userinfo.getUserName();
				if(userPwd.getUpdateTime()== null){
					//successUserCode[2] = "pwdInit";	
					successUserCode[2] = "pwdNotInit";	
				}else{
					successUserCode[2] = "pwdNotInit";
				}
			}
			return successUserCode;
		}finally{
			if(sess!=null){
				HibernateUtil.closeSession();
			}
		}
	}
}
