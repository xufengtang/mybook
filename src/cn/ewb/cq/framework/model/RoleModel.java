package cn.ewb.cq.framework.model;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cn.ewb.cq.com.BaseModel;
import cn.ewb.cq.com.HibernateUtil;
import cn.ewb.cq.com.SystemUtil;
import cn.ewb.cq.domain.Department;
import cn.ewb.cq.domain.Role;

public class RoleModel extends BaseModel {
	
	public List<Role> searchRole(Session sess)throws Exception{
//		Session sess = HibernateUtil.getSession();
		try{
			Criteria role = sess.createCriteria(Role.class);
			role.add(Restrictions.ne("delFlg", "1"));
			List<Role> roleList = role.list();
			return roleList;
		}finally{
//			if(sess!=null){
//				HibernateUtil.closeSession();
//			}
		}
	}
	
	public boolean checkExistRole(Session sess,String roleName)throws Exception{
		boolean ret = false;

		try{
			Criteria role = sess.createCriteria(Role.class);
			role.add(Restrictions.eq("roleName", roleName));
			if(role.uniqueResult()!=null){
				ret=true;
			}
			return ret;
		}finally{

		}

	}
	
	public void addRole(Session sess,String roleName,String loginUser)throws Exception{
		Role role = new Role();
		role.setRoleName(roleName);
		role.setDelFlg("0");
		role.setCreateId(loginUser);
		role.setCreateTime(SystemUtil.getSystemTime());
		add(sess,role);
	}
	
	public void updateRole(Session sess,String roleId,String roleName,String loginUser)throws Exception{
		Role role = (Role)getById(sess,Role.class, roleId);
		role.setRoleName(roleName);
		role.setUpdateId(loginUser);
		role.setUpdateTime(SystemUtil.getSystemTime());
		update(sess,role);
	}
	
	public void delRole(Session sess,String roleId,String loginUser)throws Exception{
		Role role = (Role)getById(sess,Role.class, roleId);
		role.setDelFlg("1");
		role.setUpdateId(loginUser);
		role.setUpdateTime(SystemUtil.getSystemTime());
		update(sess,role);
	}
	
}
