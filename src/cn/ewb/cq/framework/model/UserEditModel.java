package cn.ewb.cq.framework.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import cn.ewb.cq.com.BaseModel;
import cn.ewb.cq.com.HibernateUtil;
import cn.ewb.cq.com.SystemUtil;
import cn.ewb.cq.domain.Department;
import cn.ewb.cq.domain.Post;
import cn.ewb.cq.domain.Pwd;
import cn.ewb.cq.domain.Role;
import cn.ewb.cq.domain.UserDepart;
import cn.ewb.cq.domain.UserInfo;
import cn.ewb.cq.domain.UserRole;
import cn.ewb.cq.framework.form.UserEditForm;
public class UserEditModel extends BaseModel{
	
	private static String delUserDepartHql = "delete from UserDepart U " + 
	   "where " +
	   "U.userInfo = :userId ";
	
	private static String delUserRoleHql = "delete from UserRole U " + 
	   "where " +
	   "U.userInfo = :userId ";
	
	public List<Post> selectPostList(Session sess)throws Exception{
//		Session sess = HibernateUtil.getSession();
		try{
			// 职务列表取得
			Criteria post = sess.createCriteria(Post.class);
			post.add(Restrictions.ne("delFlg", "1"));
			List<Post> postList = post.list();
			return postList;
		}finally{
//			if(sess!=null){
//				HibernateUtil.closeSession();
//			}
		}

	}
	
	public List<Role>selectRoleList(Session sess)throws Exception{
//		Session sess = HibernateUtil.getSession();
		try{
			// 角色列表取得
			Criteria role = sess.createCriteria(Role.class);
			role.add(Restrictions.ne("delFlg", "1"));
			List<Role> roleList = 	role.list();
			return roleList;
		}finally{
//			if(sess!=null){
//				HibernateUtil.closeSession();
//			}
		}
	}
	
//	public List<Department>selectDepartList()throws Exception{
//		Session sess = HibernateUtil.getSession();
//		try{
//			// 部门列表取得
//			Criteria depart =  sess.createCriteria(Department.class);
//			depart.add(Restrictions.ne("delFlg", "1"));
//			List<Department> departList = depart.list();
//			return departList;
//		}finally{
//			if(sess!=null){
//				HibernateUtil.closeSession();
//			}
//		}
//
//	}
	
	public boolean isExistUser(Session sess,String userCode)throws Exception{
		boolean ret = false;
//		Session sess = HibernateUtil.getSession();
		try{
			Criteria userInfo = sess.createCriteria(UserInfo.class);
			userInfo.add(Restrictions.eq("userCode", userCode));
			List<UserInfo> userList = userInfo.list();
			if(userList!=null && userList.size()>0){
				ret = true;
			}
			return ret;
		}finally{
//			if(sess!=null){
//				HibernateUtil.closeSession();
//			}
		}
	}
	
	public boolean isErrorUserDepart(String departId,String[]userDepartId)throws Exception{
		boolean ret = false;
		if(userDepartId!=null&&userDepartId.length>0){
			for(int i=0;i<userDepartId.length;i++){
				if(departId.equals(userDepartId[i])){
					ret = true;
					break;
				}
			}
		}
		return ret;
	}
	
	public void selectEditUser(Session sess,UserEditForm userEditForm,String id)throws Exception{
//		Session sess = HibernateUtil.getSession();
		try{
			UserInfo user = (UserInfo)sess.get(UserInfo.class, id);
			List<UserDepart> userDepartList = user.getUserDepart();
			List<UserRole> userRoleList = user.getUserRole();
			String[] departs = new String[userDepartList.size()];
			String[] roles = new String[userRoleList.size()];
			for(int i=0;i<userDepartList.size();i++){
				UserDepart userDepart = userDepartList.get(i);
				departs[i] = userDepart.getDepartment().getId();
			}
			for(int i=0;i<userRoleList.size();i++){
				UserRole userRole = userRoleList.get(i);
				roles[i] = userRole.getRole().getId();
			}
			// 设置
			userEditForm.setUserCode(user.getUserCode());
			userEditForm.setUserName(user.getUserName());
			userEditForm.setPostId(user.getPost().getId());
			userEditForm.setDepartId(user.getDepartment().getId());
			userEditForm.setRoleId(roles);
			userEditForm.setUserDepartId(departs);
			userEditForm.setEditId(id);
			
		}finally{
//			if(sess!=null){
//				HibernateUtil.closeSession();
//			}
		}
	}
	
	public void addUser(Session sess,UserEditForm userEditForm,String loginUser)throws Exception{
//		Session sess = HibernateUtil.getSession();
		Transaction tx = sess.beginTransaction();
		try{
				
			// 员工号
			String userCode = userEditForm.getUserCode();
			// 员工姓名
			String userName = userEditForm.getUserName();
			// 职务
			String postId = userEditForm.getPostId();
			// 部门(属)
			String departId = userEditForm.getDepartId();
			// 部门(兼)
			String[] userDepartId = userEditForm.getUserDepartId();
			List<UserDepart> userDepartList = new ArrayList<UserDepart>();
			
			// 角色
			String[] roleId = userEditForm.getRoleId();
			List<UserRole> userRoleList = new ArrayList<UserRole>();
			//======================保存对象创建========================
			// 用户
			UserInfo userInfo = new UserInfo();
			// 密码
			Pwd pwd = new Pwd();
			pwd.setUserInfo(userInfo);
			pwd.setPwd(SystemUtil.pwdEncryption("000000"));
			pwd.setCreateId(loginUser);
			pwd.setCreateTime(SystemUtil.getSystemTime());
			// 部门(属)
			Department department = new Department();
			department.setId(departId);
			// 职务
			Post post = new Post();
			post.setId(postId);
			// 角色
			if(roleId!=null&&roleId.length>0){
				for(int i=0;i<roleId.length;i++){
					Role role = new Role();
					role.setId(roleId[i]);
					UserRole userRole = new UserRole();
					userRole.setRole(role);
					userRole.setUserInfo(userInfo);
					userRole.setCreateId(loginUser);
					userRole.setCreateTime(SystemUtil.getSystemTime());
					userRoleList.add(userRole);
				}
			}
			
			// 部门(兼)
			if(userDepartId!=null&&userDepartId.length>0){
				for(int i=0;i<userDepartId.length;i++){
					Department departObject = new Department();
					departObject.setId(userDepartId[i]);
					UserDepart userDepart =  new UserDepart();
					userDepart.setDepartment(departObject);
					userDepart.setUserInfo(userInfo);
					userDepart.setCreateId(loginUser);
					userDepart.setCreateTime(SystemUtil.getSystemTime());
					userDepartList.add(userDepart);
				}
			}
			userInfo.setId(userCode);
			userInfo.setUserCode(userCode);
			userInfo.setUserName(userName);
			userInfo.setDepartment(department);
			userInfo.setPost(post);
			userInfo.setDelFlg("0");
			userInfo.setCreateId(loginUser);
			userInfo.setCreateTime(SystemUtil.getSystemTime());
			sess.save(userInfo);
			sess.save(pwd);
			for(int i=0;i<userRoleList.size();i++){
				sess.save(userRoleList.get(i));
			}
			for(int i=0;i<userDepartList.size();i++){
				sess.save(userDepartList.get(i));
			}
			
			tx.commit();
		}catch(Exception ex){
			tx.rollback();
			throw ex;
		}finally{
//			if(sess!=null){
//				HibernateUtil.closeSession();
//			}
		}
	}
	
	public void updateUser(Session sess,UserEditForm userEditForm,String loginUser)throws Exception{
//		Session sess = HibernateUtil.getSession();
		Transaction tx = sess.beginTransaction();
		try{
			// ID
			String id = userEditForm.getEditId();
			// 员工号
			//String userCode = userEditForm.getUserCode();
			// 员工姓名
			String userName = userEditForm.getUserName();
			// 职务
			String postId = userEditForm.getPostId();
			// 部门(属)
			String departId = userEditForm.getDepartId();
			// 部门(兼)
			String[] userDepartId = userEditForm.getUserDepartId();
			List<UserDepart> userDepartList = new ArrayList<UserDepart>();
			// 角色
			String[] roleId = userEditForm.getRoleId();
			List<UserRole> userRoleList = new ArrayList<UserRole>();
			//======================保存对象创建========================
			UserInfo userInfo = (UserInfo)sess.get(UserInfo.class, id);
			// 部门(属)
			Department department = new Department();
			department.setId(departId);
			// 职务
			Post post = new Post();
			post.setId(postId);
			
			// 角色
			if(roleId!=null&&roleId.length>0){
				for(int i=0;i<roleId.length;i++){
					Role role = new Role();
					role.setId(roleId[i]);
					UserRole userRole = new UserRole();
					userRole.setRole(role);
					userRole.setUserInfo(userInfo);
					userRole.setCreateId(loginUser);
					userRole.setCreateTime(SystemUtil.getSystemTime());
					userRoleList.add(userRole);
				}
			}
			
			// 部门(兼)
			if(userDepartId!=null&&userDepartId.length>0){
				for(int i=0;i<userDepartId.length;i++){
					Department departObject = new Department();
					departObject.setId(userDepartId[i]);
					UserDepart userDepart =  new UserDepart();
					userDepart.setDepartment(departObject);
					userDepart.setUserInfo(userInfo);
					userDepart.setCreateId(loginUser);
					userDepart.setCreateTime(SystemUtil.getSystemTime());
					userDepartList.add(userDepart);
				}
			}
			userInfo.setUserName(userName);
			userInfo.setDepartment(department);
			userInfo.setPost(post);
			userInfo.setDelFlg("0");
			userInfo.setUpdateId(loginUser);
			userInfo.setUpdateTime(SystemUtil.getSystemTime());
			sess.save(userInfo);
			
			// 部门兼，角色表操作
			// 先删除
			Query queryUserDepart = sess.createQuery(delUserDepartHql);
			queryUserDepart.setParameter("userId", userInfo);
			queryUserDepart.executeUpdate();
			
			Query queryUserRole = sess.createQuery(delUserRoleHql);
			queryUserRole.setParameter("userId", userInfo);
			queryUserRole.executeUpdate();	
			// 再添加
			for(int i=0;i<userRoleList.size();i++){
				sess.save(userRoleList.get(i));
			}
			for(int i=0;i<userDepartList.size();i++){
				sess.save(userDepartList.get(i));
			}
			tx.commit();
		}catch(Exception ex){
			tx.rollback();
			throw ex;
		}finally{
//			if(sess!=null){
//				HibernateUtil.closeSession();
//			}
		}
	}
	
	public void pwdReset(Session sess,UserEditForm userEditForm,String loginUser)throws Exception{
		String userId = userEditForm.getEditId();
		String userCode = userEditForm.getUserCode();
		Pwd pwd = (Pwd)getById(sess,Pwd.class, userId);
		pwd.setPwd(SystemUtil.pwdEncryption("000000"));
		pwd.setUpdateId(null);
		pwd.setUpdateTime(null);
		update(sess,pwd);
	}
}
