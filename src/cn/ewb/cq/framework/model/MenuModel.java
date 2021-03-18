package cn.ewb.cq.framework.model;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import cn.ewb.cq.com.BaseModel;
import cn.ewb.cq.com.HibernateUtil;
import cn.ewb.cq.com.StringUtil;
import cn.ewb.cq.com.SystemUtil;
import cn.ewb.cq.domain.Menu;
import cn.ewb.cq.framework.bean.MenuBean;
import cn.ewb.cq.framework.form.MenuForm;

public class MenuModel extends BaseModel {

	public List<Menu> searchParentMenu()throws Exception{
		Session sess = HibernateUtil.getSession();
		try{
			Criteria parentMenu = sess.createCriteria(Menu.class);
			parentMenu.add(Restrictions.eq("rank", "1"));
			parentMenu.add(Restrictions.ne("delFlg", "1"));
			List<Menu> parentMenuList = parentMenu.list();
			return parentMenuList;
		}finally{
			if(sess!=null){
				HibernateUtil.closeSession();
			}
		}
	}
	
	public List<MenuBean> searchMenu()throws Exception{
		List<MenuBean> MenuBeanList = new ArrayList<MenuBean>();
		Session sess = HibernateUtil.getSession();
		try{
			Criteria menu = sess.createCriteria(Menu.class);
			menu.add(Restrictions.ne("delFlg", "1"));
			menu.addOrder(Order.asc("orderNo"));
			List<Menu> menuList = menu.list();
			//menu To menuBean(父级名称取得)
			for(int i=0;i<menuList.size();i++){
				Menu menuObject = menuList.get(i);
				MenuBean menuBean = new MenuBean();
				// ID
				menuBean.setMenuId(menuObject.getId());
				// 菜单名
				menuBean.setMenuName(menuObject.getMenuName());
				// 顺序号
				menuBean.setOrderNo(menuObject.getOrderNo());
				// 父节点ID
				menuBean.setParentId(menuObject.getParentMenuId());
				// 级别
				menuBean.setRank(menuObject.getRank());
				// 父级不为空时，取父级名称
				if(StringUtil.isNotNull(menuObject.getParentMenuId())){
					Menu parentMenu = (Menu)getById(sess,Menu.class, menuObject.getParentMenuId());
					menuBean.setParentName(parentMenu.getMenuName());
				}
				// URL
				menuBean.setUrl(menuObject.getUrl());
				MenuBeanList.add(menuBean);
			}
			return MenuBeanList;
		}finally{
			if(sess!=null){
				HibernateUtil.closeSession();
			}
		}
	}
	
	public void addMenu(Session sess,MenuForm menuForm ,String loginUser)throws Exception{
		String orderNo = menuForm.getOrderNo();
		String menuName = menuForm.getMenuName();
		String rank = menuForm.getRank();
		String parentId= menuForm.getParentId();
		String url = menuForm.getUrl();
		
		Menu menu = new Menu();
		menu.setOrderNo(Integer.parseInt(orderNo));
		menu.setMenuName(menuName);
		menu.setRank(rank);
		menu.setDelFlg("0");
		//2级菜单时,需要父级ID
		if("2".equals(rank)){
			menu.setParentMenuId(parentId);
			menu.setUrl(url);
		}
		menu.setCreateId(loginUser);
		menu.setCreateTime(SystemUtil.getSystemTime());
		add(sess,menu);
	}
	
	public boolean checkHasChildren(Session sess,String menuId)throws Exception{
		boolean ret = false;
		//Session sess = HibernateUtil.getSession();
		try{
			Criteria menu = sess.createCriteria(Menu.class);
			menu.add(Restrictions.eq("parentMenuId", menuId));
			List<Menu> menuList = menu.list();
			if(menuList!=null&&menuList.size()>0){
				ret = true;
			}
			return ret;
		}finally{
//			if(sess!=null){
//				HibernateUtil.closeSession();
//			}
		}

	}
	
	public void updateMenu(Session sess,String menuId,MenuForm menuForm,String loginUser)throws Exception{
		String orderNo = menuForm.getOrderNo();
		String menuName = menuForm.getMenuName();
		String rank = menuForm.getRank();
		String parentId= menuForm.getParentId();
		String url = menuForm.getUrl();
		Menu menu = (Menu)getById(sess,Menu.class, menuId);
		menu.setOrderNo(Integer.parseInt(orderNo));
		menu.setMenuName(menuName);
		menu.setRank(rank);
		//2级菜单时,需要父级ID
		if("2".equals(rank)){
			menu.setParentMenuId(parentId);
			menu.setUrl(url);
		}else{
			menu.setParentMenuId(null);
			menu.setUrl(null);
		}
		menu.setUpdateId(loginUser);
		menu.setUpdateTime(SystemUtil.getSystemTime());
		update(sess,menu);
	}
	
	public void delMenu(Session sess,String menuId,String loginUser)throws Exception{
		Menu menu = (Menu)getById(sess,Menu.class, menuId);
		menu.setDelFlg("1");
		menu.setUpdateId(loginUser);
		menu.setUpdateTime(SystemUtil.getSystemTime());
		update(sess,menu);
	}
}
