package cn.ewb.cq.framework.model;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import cn.ewb.cq.com.BaseModel;
import cn.ewb.cq.com.HibernateUtil;
import cn.ewb.cq.com.SystemUtil;
import cn.ewb.cq.domain.Authority;
import cn.ewb.cq.domain.Menu;
import cn.ewb.cq.domain.Role;
import cn.ewb.cq.framework.bean.AuthorityBean;


public class AuthorityModel extends BaseModel {

	private static String hql = "from Role as R,Authority as A,Menu as M " + 
								"where " +
								"R.id = A.role and " + 
								"A.menu = M.id and " + 
								"R.delFlg <> '1' and "+
								"M.delFlg <> '1' and "+
								"M.rank = '2' and "+
								"R.id = :roleId ";
	
	private static String delhql = "delete from Authority A " + 
								   "where " +
								   "A.role = :roleId ";
	
	public List<Role>  searchRoleList()throws Exception{ 
		Session sess = HibernateUtil.getSession();
		try{
			Criteria role = sess.createCriteria(Role.class);
			role.add(Restrictions.ne("delFlg", "1"));
			List<Role> roleList = role.list(); 
			return roleList;
		}finally{
			if(sess!=null){
				HibernateUtil.closeSession();
			}
		}
	}
	
	
	public List<AuthorityBean> searchAuthorityList(String roleId)throws Exception{
		List<AuthorityBean> authorityList = new ArrayList<AuthorityBean>();
		Session sess = HibernateUtil.getSession();
		try{
			Criteria menu = sess.createCriteria(Menu.class);
			menu.add(Restrictions.eq("rank", "2"));
			menu.add(Restrictions.ne("delFlg", "1"));
			menu.addOrder(Order.asc("orderNo"));
			List<Menu> menuList = menu.list();
			for(int i=0;i<menuList.size();i++){
				Menu menuObject = menuList.get(i);
				AuthorityBean authorityBean = new AuthorityBean();
				// 画面ID
				authorityBean.setMenuId(menuObject.getId());
				// 画面名
				authorityBean.setMenuName(menuObject.getMenuName());
				authorityList.add(authorityBean);
			}
			
			// 取得该角色所拥有的权限
			List<Menu> menuListByCode = new ArrayList<Menu>();
			List<Authority> authorityListByCode = new ArrayList<Authority>();
			Query query = sess.createQuery(hql);
			query.setParameter("roleId", roleId);
			List result = query.list();
			if(result!=null){
				for(int i=0;i<result.size();i++){
					Object[] object = (Object[])result.get(i);
					Authority authorityByCode = (Authority)object[1];
					Menu menuByCode = (Menu)object[2];
					
					menuListByCode.add(menuByCode);
					authorityListByCode.add(authorityByCode);
				}
			}
			
			// menuValue设置
			for(int i=0;i<authorityList.size();i++){
				AuthorityBean authorityBean = authorityList.get(i);
				boolean authorityFlg =false;
				String authorityValue = "";
				for(int j=0;j<menuListByCode.size();j++){
					Menu menuByCode = menuListByCode.get(j);
					Authority authorityByCode = authorityListByCode.get(j);
					if(authorityBean.getMenuId().equals(menuByCode.getId())){
						authorityFlg = true;
						authorityValue = authorityByCode.getAuthorityLevel();
						
					}
				}
				if(authorityFlg){
					//authorityBean.setMenuValue("1");
					authorityBean.setMenuValue(authorityValue);
				}else{
					authorityBean.setMenuValue("0");
				}
			}
			return authorityList;
		}finally{
			if(sess!=null){
				HibernateUtil.closeSession();
			}
		}
	}
	
	
	public void updateAuthority(String roleId,List<AuthorityBean> authorityList,String loginUser)throws Exception{
		//1级菜单ID
		List<String> parentMenuIdList = new ArrayList<String>();
		Session sess = HibernateUtil.getSession();
		Transaction tx = sess.beginTransaction();
		try{	
			//删除该角色的所有权限
			Role roleDel = new Role();
			roleDel.setId(roleId);
			Query query = sess.createQuery(delhql);
			query.setParameter("roleId", roleDel);
			query.executeUpdate();
			//更新添加拥有权限
			//2级菜单权限更新
			for(int i=0;i<authorityList.size();i++){
				AuthorityBean authoritybean = authorityList.get(i);
				// 判断权限【可用】
				if(!"0".equals(authoritybean.getMenuValue())){
					Authority authorityNew = new Authority();
					Role role = new Role();
					role.setId(roleId);
					Menu menu = new Menu();
					menu.setId(authoritybean.getMenuId());
					authorityNew.setMenu(menu);
					authorityNew.setRole(role);
					authorityNew.setAuthorityLevel(authoritybean.getMenuValue());
					authorityNew.setCreateId(loginUser);
					authorityNew.setCreateTime(SystemUtil.getSystemTime());
					authorityNew.setUpdateId(loginUser);
					authorityNew.setUpdateTime(SystemUtil.getSystemTime());
					sess.save(authorityNew);
				}
			}
			
			//1级菜单ID取得
			for(int i=0;i<authorityList.size();i++){
				AuthorityBean authoritybean = authorityList.get(i);
				if(Integer.parseInt(authoritybean.getMenuValue()) >0){
				//if("1".equals(authoritybean.getMenuValue())){
					Menu menu = (Menu)getById(sess,Menu.class, authoritybean.getMenuId());
					addParentMenuId(menu.getParentMenuId(),parentMenuIdList);
				}
			}
			
			//1级菜单权限更新
			for(int i=0;i<parentMenuIdList.size();i++){
				String parentId = parentMenuIdList.get(i);
				Authority authorityNew = new Authority();
				Role role = new Role();
				role.setId(roleId);
				Menu menu = new Menu();
				menu.setId(parentId);
				authorityNew.setMenu(menu);
				authorityNew.setRole(role);
				authorityNew.setAuthorityLevel("1");
				authorityNew.setCreateId(loginUser);
				authorityNew.setCreateTime(SystemUtil.getSystemTime());
				authorityNew.setUpdateId(loginUser);
				authorityNew.setUpdateTime(SystemUtil.getSystemTime());
				sess.save(authorityNew);
			}
			tx.commit();
		}catch(Exception ex){
			tx.rollback();
			throw ex;
		}finally{
			HibernateUtil.closeSession();	
		}
	}
	
	private void addParentMenuId(String parentMenuId,List<String> parentMenuIdList){
		boolean checkFlg = true;
		if(parentMenuIdList.size()>0){
			for(int i =0;i<parentMenuIdList.size();i++){
				String testValue = parentMenuIdList.get(i);
				if(testValue.equals(parentMenuId)){
					checkFlg = false;
				}
			}
			if (checkFlg){
				parentMenuIdList.add(parentMenuId);
			}
		}else{
			parentMenuIdList.add(parentMenuId);
		}

	}
}
