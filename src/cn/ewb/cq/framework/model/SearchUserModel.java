package cn.ewb.cq.framework.model;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import cn.ewb.cq.com.BaseModel;
import cn.ewb.cq.com.HibernateUtil;
import cn.ewb.cq.com.StringUtil;
import cn.ewb.cq.com.SysConstant;
import cn.ewb.cq.com.SystemUtil;
import cn.ewb.cq.domain.Department;
import cn.ewb.cq.domain.UserDepart;
import cn.ewb.cq.domain.UserInfo;
import cn.ewb.cq.domain.UserRole;
import cn.ewb.cq.framework.bean.UserBean;
import cn.ewb.cq.framework.form.SearchUserForm;
public class SearchUserModel extends BaseModel{
	
	public List<UserBean>selectUserList(Session sess,SearchUserForm searchUserForm)throws Exception{
		List <UserBean> userList = new ArrayList<UserBean>();
		//Session sess = HibernateUtil.getSession();
		try{
			String userCode =searchUserForm.getUserCodeSearch();
			String userName =searchUserForm.getUserNameSearch();
			String departId =searchUserForm.getDepartIdSearch();
			
			Criteria userObject = sess.createCriteria(UserInfo.class);
			userObject.add(Restrictions.ne("delFlg", "1"));
			if(StringUtil.isNotNull(userCode)){
				userObject.add(Restrictions.eq("userCode", userCode));
			}
			if(StringUtil.isNotNull(userName)){
				userObject.add(Restrictions.ilike("userName", userName, MatchMode.ANYWHERE));
			}
			if(StringUtil.isNotNull(departId)){
				Department department = new Department();
				department.setId(departId);
				userObject.add(Restrictions.eq("department", department));
			}
			// 起始记录设置
			userObject.setFirstResult((searchUserForm.getCurrentPage()-1)* SysConstant.PAGE);
			userObject.setMaxResults(SysConstant.PAGE);
			userObject.addOrder(Order.asc("userCode"));
			List<UserInfo> userInfoList = userObject.list();
			// 数据存在判断
			if(userInfoList!=null&&userInfoList.size()>0){
				
				for(int i=0;i<userInfoList.size();i++){
					UserInfo userInfo = userInfoList.get(i);
					UserBean bean = new UserBean();
					bean.setId(userInfo.getId());
					bean.setUserCode(userInfo.getUserCode());
					bean.setUserName(userInfo.getUserName());
					bean.setDepartName(userInfo.getDepartment().getDepartmentName());
					bean.setPostName(userInfo.getPost().getPostName());
					String roleName = selectRoleName(userInfo);
					bean.setRoleName(roleName);
					String userDepartName = selectUserDepartName(userInfo);
					bean.setUserDepartName(userDepartName);	
					userList.add(bean);
				}
			}
			return userList;	
		}finally{
//			if(sess!=null){
//				HibernateUtil.closeSession();
//			}
		}
	}
	
	public void deleteUser(Session sess,String userId,String loginUser)throws Exception{
		UserInfo userInfo = (UserInfo)getById(sess,UserInfo.class, userId);
		userInfo.setDelFlg("1");
		userInfo.setUpdateId(loginUser);
		userInfo.setUpdateTime(SystemUtil.getSystemTime());
		update(sess,userInfo);
	}
	
	public int selectTotalPage(Session sess,SearchUserForm searchUserForm) throws Exception {
		//Session sess = HibernateUtil.getSession();
		try{
			String hql = "select count(id) from UserInfo  where delFlg<>'1' ";
			String userCode =searchUserForm.getUserCodeSearch();
			String userName =searchUserForm.getUserNameSearch();
			String departId =searchUserForm.getDepartIdSearch();
			if(StringUtil.isNotNull(userCode)){
				hql=hql + " and userCode=:userCode";
			}
			if(StringUtil.isNotNull(userName)){
				//hql=hql + " and userName=:userName";
				hql=hql + " and userName like :userName";
			}
			if(StringUtil.isNotNull(departId)){
				hql=hql + " and department=:department";
			}
			// 页数计算
			Query q = sess.createQuery(hql);
			if(StringUtil.isNotNull(userCode)){
				q.setParameter("userCode", userCode);
			}
			if(StringUtil.isNotNull(userName)){
				q.setParameter("userName", "%"+userName+"%");
			}
			if(StringUtil.isNotNull(departId)){
				Department department = new Department();
				department.setId(departId);
				q.setParameter("department", department);
			}
			
			int totalNum = Integer.parseInt(q.iterate().next().toString());
			int totalPage = totalNum%SysConstant.PAGE==0?totalNum/SysConstant.PAGE:totalNum/SysConstant.PAGE+1;
			return totalPage;
		}finally{
//			if(sess!=null){
//				HibernateUtil.closeSession();
//			}
		}
	}
	
	private String  selectRoleName(UserInfo userInfo){
		String roleName = "";
		List<UserRole> userRoleList = userInfo.getUserRole();
		for(int i=0;i<userRoleList.size();i++){
			UserRole userRole = userRoleList.get(i);
			if(i>0){
				roleName = roleName + ",";
				roleName = roleName + userRole.getRole().getRoleName();
			}else{
				roleName = roleName + userRole.getRole().getRoleName();
			}
		}
		return roleName;
	}
	
	private String selectUserDepartName(UserInfo userInfo){
		String departName = "";
		List<UserDepart> userDepartList = userInfo.getUserDepart();
		for(int i=0;i<userDepartList.size();i++){
			UserDepart userDepart = userDepartList.get(i);
			if(i>0){
				departName = departName + ",";
				departName = departName + userDepart.getDepartment().getDepartmentName();
			}else{
				departName = departName + userDepart.getDepartment().getDepartmentName();
			}
		}
		return departName;
	}
	
	public List<Department>selectDepartList()throws Exception{
		Session sess = HibernateUtil.getSession();
		try{
			// 部门列表取得
			Criteria depart =  sess.createCriteria(Department.class);
			depart.add(Restrictions.ne("delFlg", "1"));
			List<Department> departList = depart.list();
			return departList;
		}finally{
			if(sess!=null){
				HibernateUtil.closeSession();
			}
		}
	}
}
