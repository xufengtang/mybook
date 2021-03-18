package cn.ewb.cq.com;



import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import cn.ewb.cq.domain.Department;
import cn.ewb.cq.domain.PubDict;
import cn.ewb.cq.domain.UserInfo;


public class BaseModel {
	private String loginUser;
	//private KpQs kpQs;

	/***
	 * 
	 * 共通新增方法
	 * 
	 * **/
	public void add(Session ses,Object entity) {
		//Session ses = null;
		Transaction tx = null;
		try {
			tx = ses.beginTransaction();
			ses.save(entity);
			tx.commit();
		} finally {
//			if (ses != null) {
//				ses.close();
//			}
		}
	}

	/***
	 * 
	 * 共通更新方法
	 * 
	 * **/
	public void update(Session ses,Object entity) {
		//Session ses = null;
		Transaction tx = ses.beginTransaction();
		try {
			tx = ses.beginTransaction();
			ses.update(entity);
			tx.commit();
		} finally {
//			if (ses != null) {
//				ses.close();
//			}
		}
	}

	/***
	 * 
	 * 共通删除方法
	 * 
	 * **/
	public void delete(Session ses,Object entity) {
		//Session ses = null;
		Transaction tx = null;
		try {
			ses = HibernateUtil.getSession();
			tx = ses.beginTransaction();
			ses.delete(entity);
			tx.commit();
		} finally {
//			if (ses != null) {
//				ses.close();
//			}
		}
	}

	/***
	 * 
	 * getById方法
	 * 
	 * **/
	public Object getById(Session ses,Class clazz, String id) {
//		Session ses = null;
		try {
			ses = HibernateUtil.getSession();
			Object obj = ses.get(clazz, id);
			return obj;
		} finally {
//			if (ses != null) {
//				ses.close();
//			}
		}
	}

	public String getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}

	public UserInfo getUserInfo(Session sess) {
	
		try {
			Criteria user = sess.createCriteria(UserInfo.class);
			user.add(Restrictions.eq("userCode", this.loginUser));
			List<UserInfo> listTmp = user.list();
			if (listTmp.size() > 0)
				return listTmp.get(0);
			else
				return null;
		} finally {
//			if (sess != null) {
//				HibernateUtil.closeSession();
//			}
		}
	}
	
//	public UserInfo getUserInfo(String userCode) {
//		Session sess = HibernateUtil.getSession();
//		try {
//			Criteria user = sess.createCriteria(UserInfo.class);
//			user.add(Restrictions.eq("userCode", userCode));
//			List<UserInfo> listTmp = user.list();
//			if (listTmp.size() > 0)
//				return listTmp.get(0);
//			else
//				return null;
//		} finally {
//			if (sess != null) {
//				HibernateUtil.closeSession();
//			}
//		}
//	}
	
	
	public UserInfo getUserInfo(Session sess,String userCode)throws Exception {
		Criteria user = sess.createCriteria(UserInfo.class);
		user.add(Restrictions.eq("userCode", userCode));
		List<UserInfo> listTmp = user.list();
		if (listTmp.size() > 0)
			return listTmp.get(0);
		else
			return null;
	}
	

	
	public List <PubDict> getPubDict(Session sess,String type){
		Criteria dict = sess.createCriteria(PubDict.class);
		dict.add(Restrictions.eq("dictType", type));
		dict.add(Restrictions.ne("delFlg", "1"));
		List <PubDict> dictList = dict.list();
		return dictList;
	}
	
	public String getPubDictName(Session sess,String type,String code){
		//Session sess = HibernateUtil.getSession();
		try {
			Criteria dict = sess.createCriteria(PubDict.class);
			dict.add(Restrictions.eq("dictType", type));
			dict.add(Restrictions.eq("dictCode", code));
			PubDict result = (PubDict)dict.uniqueResult();
			if(result!=null&&StringUtil.isNotNull(result.getDictName())){
				return result.getDictName();
			}else{
				return "";
			}
		} finally {
//			if (sess != null) {
//				HibernateUtil.closeSession();
//			}
		}
	}
	
//	public String getPubDictName(Session sess,String type,String code){
//			Criteria dict = sess.createCriteria(PubDict.class);
//			dict.add(Restrictions.eq("dictType", type));
//			dict.add(Restrictions.eq("dictCode", code));
//			PubDict result = (PubDict)dict.uniqueResult();
//			if(result!=null&&StringUtil.isNotNull(result.getDictName())){
//				return result.getDictName();
//			}else{
//				return "";
//			}
//			
//	}
	
//	// 考试期数取得
//	public KpQs getKpQs(){
//		Session sess = HibernateUtil.getSession();
//		try {
//			KpQs retKqQS = null;
//			Date nowTime = SystemUtil.getSystemTime();
////			if(kpQs!=null){
////				retKqQS = kpQs;
////			}else{
//				Criteria kpQsSearch = sess.createCriteria(KpQs.class);
//				kpQsSearch.add(Restrictions.ge("dateEnd", nowTime));
//				kpQsSearch.add(Restrictions.le("dateStart", nowTime));
//				List<KpQs> retKqQSList = kpQsSearch.list();
//				if(retKqQSList!=null&&retKqQSList.size()>0){
//					retKqQS = retKqQSList.get(0);
//					//kpQs = retKqQS;
//				}
//		//	}
//			return retKqQS;
//		} finally {
//			if (sess != null) {
//				HibernateUtil.closeSession();
//			}
//		}
//	}
	
	
	
	
	public List<Department>selectDepartList(Session sess)throws Exception{
//		Session sess = HibernateUtil.getSession();
		try{
			// 部门列表取得
			Criteria depart =  sess.createCriteria(Department.class);
			depart.add(Restrictions.ne("delFlg", "1"));
			depart.addOrder(Order.asc("departmentCode"));
			List<Department> departList = depart.list();
			return departList;
		}finally{
//			if(sess!=null){
//				HibernateUtil.closeSession();
//			}
		}

	}

	
	public String getDepartName(Session sess, String departCode){
		Criteria depart =  sess.createCriteria(Department.class);
		depart.add(Restrictions.eq("departmentCode", departCode));
		Department department = (Department)depart.uniqueResult();
		return department.getDepartmentName();
	}
	
	public String getBankCode(Session sess, String departCode){
		Criteria depart =  sess.createCriteria(Department.class);
		depart.add(Restrictions.eq("departmentCode", departCode));
		Department department = (Department)depart.uniqueResult();
		return department.getBankCode();
	}
	
	public List<Department>selectByLikeDepartList(Session sess)throws Exception{
//		Session sess = HibernateUtil.getSession();
		try{
			// 部门列表取得
			Criteria depart =  sess.createCriteria(Department.class);
			depart.add(Restrictions.ne("delFlg", "1"));
			depart.add(Restrictions.or(Restrictions.ilike("departmentCode", "0231", MatchMode.START), Restrictions.ilike("departmentCode", "0232", MatchMode.START)));
			depart.addOrder(Order.asc("departmentCode"));
			List<Department> departList = depart.list();
			return departList;
		}finally{
//			if(sess!=null){
//				HibernateUtil.closeSession();
//			}
		}
		
	}
	
	public List<Department>selectByLikeDepartList2(Session sess)throws Exception{
//		Session sess = HibernateUtil.getSession();
		try{
			// 部门列表取得
			Criteria depart =  sess.createCriteria(Department.class);
			depart.add(Restrictions.ne("delFlg", "1"));
			depart.add(Restrictions.or(Restrictions.ilike("departmentCode", "0231", MatchMode.START),Restrictions.or(Restrictions.ilike("departmentCode", "0232", MatchMode.START),Restrictions.eq("departmentCode", "02399954"))));
			depart.addOrder(Order.asc("departmentCode"));
			List<Department> departList = depart.list();
			return departList;
			
			 
		}finally{
//			if(sess!=null){
//				HibernateUtil.closeSession();
//			}
		}
		
	}
	
	
	
	public String getDepartCodeByUser(Session sess,String userCode)throws Exception{
		Criteria userInfoSearch =  sess.createCriteria(UserInfo.class);
		userInfoSearch.add(Restrictions.eq("userCode", userCode));
		UserInfo userInfo = (UserInfo)userInfoSearch.uniqueResult();
		return userInfo.getDepartment().getDepartmentCode();
	} 
	
	public String[] getDepartByUser(Session sess,String userCode)throws Exception{
		Criteria userInfoSearch =  sess.createCriteria(UserInfo.class);
		String[] departs = new String[]{"",""};
		userInfoSearch.add(Restrictions.eq("userCode", userCode));
		UserInfo userInfo = (UserInfo)userInfoSearch.uniqueResult();
		Department depart = userInfo.getDepartment();
		departs[0]= depart.getDepartmentCode();
		departs[1]= depart.getDepartmentName();
		return departs;
	} 
	
	public Department getDepartObjectByUser(Session sess,String userCode)throws Exception{
		Criteria userInfoSearch =  sess.createCriteria(UserInfo.class);
		userInfoSearch.add(Restrictions.eq("userCode", userCode));
		UserInfo userInfo = (UserInfo)userInfoSearch.uniqueResult();
		Department depart = userInfo.getDepartment();
		return depart;
	}
	

	
	
	public void downFile(HttpServletRequest request,HttpServletResponse response,String fileName)throws Exception{
		String filePath ="";
		String floderName = fileName.substring(0, 6);
		String fileShowName = fileName.substring(14,fileName.length());
		filePath =  SystemUtil.getProperties(request).getProperty("PUB_FILE_PATH") +  floderName + "\\" + fileName;
		
		BufferedInputStream bis = null;
		BufferedOutputStream bos =null;
		InputStream fis = null;
		OutputStream fos = null;
		
		File uploadFile = new File(filePath);
		fis = new FileInputStream(uploadFile);
		bis = new BufferedInputStream(fis);
		fos = response.getOutputStream();
		bos = new BufferedOutputStream(fos);
			
		// 参数设置
		response.reset();
		response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileShowName,"utf-8"));
		int bytesRead =0;
		byte[] buffer = new byte[4096];
		while((bytesRead=bis.read(buffer,0,4096))!=-1){
			bos.write(buffer,0,bytesRead);
		}
		bos.flush();
		fis.close();
		bis.close();
		fos.close();
		bos.close();
	}


}
