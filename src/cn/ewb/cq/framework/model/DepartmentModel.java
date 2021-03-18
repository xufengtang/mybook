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

public class DepartmentModel extends BaseModel {

	public List<Department> searchDepart(Session sess) throws Exception {
		
		try{
			Criteria department = sess.createCriteria(Department.class);
			department.add(Restrictions.ne("delFlg", "1"));
			department.addOrder(Order.asc("departmentCode"));
			List<Department> result = department.list();
			return result;
		}finally{
//			if(sess!=null){
//				HibernateUtil.closeSession();
//			}
		}

	}

	public boolean checkExistDepart(Session sess,String departmentCode) throws Exception {
		boolean ret = false;
		//Session sess = HibernateUtil.getSession();
		try{
			Criteria department = sess.createCriteria(Department.class);
			department.add(Restrictions.eq("departmentCode", departmentCode));
			if (department.uniqueResult() != null) {
				ret = true;
			}
			return ret;
		}finally{
//			if(sess!=null){
//				HibernateUtil.closeSession();
//			}
		}
	}

	public void addDepart(Session sess,String departmentCode, String departmentName,String loginUser,String bankCode) throws Exception {
		Department department = new Department();
		department.setId(departmentCode);
		department.setDepartmentCode(departmentCode);
		department.setDepartmentName(departmentName);
		department.setDelFlg("0");
		department.setCreateId(loginUser);
		department.setCreateTime(SystemUtil.getSystemTime());
		department.setBankCode(bankCode);
		add(sess,department);
	}

	public void updateDepart(Session sess,String departmentId, String departmentName,String loginUser,String bankCode) throws Exception {
		Department department = (Department)getById(sess,Department.class, departmentId);
		department.setDepartmentName(departmentName);
		department.setUpdateId(loginUser);
		department.setUpdateTime(SystemUtil.getSystemTime());
		department.setBankCode(bankCode);
		update(sess,department);
	}
	
	public void delDepart(Session sess,String departmentId,String loginUser)throws Exception{
		Department department = (Department)getById(sess,Department.class, departmentId);
		department.setDelFlg("1");
		department.setUpdateId(loginUser);
		department.setUpdateTime(SystemUtil.getSystemTime());
		update(sess,department);
	}
}
