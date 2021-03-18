package cn.ewb.cq.framework.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import cn.ewb.cq.com.BaseModel;
import cn.ewb.cq.com.HibernateUtil;
import cn.ewb.cq.domain.Menu;
import cn.ewb.cq.framework.bean.TreeNodeBean;

public class ShowMenuModel extends BaseModel{
	
	private static String hql = "select distinct M from UserInfo as UI,UserRole as UR,Role as R,Authority as A,Menu as M " + 
								"where " +
								"UI.id = UR.userInfo and " + 
								"UR.role = R.id and " + 
								"R.id = A.role and " + 
								"A.menu = M.id and " + 
								"R.delFlg <> '1' and "+
								"M.delFlg <> '1' and "+
								"A.authorityLevel > '0' and "+
								"UI.userCode = :userCode " + 
								"order by M.orderNo";
	
	public List<TreeNodeBean> readMenuTree(String userCode) throws Exception{
		List<Menu> menuList = new ArrayList<Menu>();
		List<TreeNodeBean> menuTree = new ArrayList<TreeNodeBean>();
		Session sess = HibernateUtil.getSession();
		try{
			Query query = sess.createQuery(hql);
			query.setParameter("userCode", userCode);
			List result = query.list();
			if(result!=null){
				for(int i=0;i<result.size();i++){
					//Object[] object = (Object[])result.get(i);
					//Menu menu = (Menu)object[0];
					Menu menu=(Menu)result.get(i);
					menuList.add(menu);
				}
			}
			// 菜单处理
			for(int i=0;i<menuList.size();i++){
				Menu menu  = menuList.get(i);
				// 读取所有父节点
				if("1".equals(menu.getRank())){
					TreeNodeBean treeNode  = new TreeNodeBean();
					treeNode.setMenuId(menu.getId());
					treeNode.setMenuName(menu.getMenuName());
					treeNode.setUrl(menu.getUrl());
					menuTree.add(treeNode);
					menuList.remove(i);
					i--;
				}
			}
				
			for(int i=0;i<menuTree.size();i++){
				TreeNodeBean treeNode = menuTree.get(i);
				String parentId = treeNode.getMenuId();
				for(int j=0;j<menuList.size();j++){
					Menu menu  = menuList.get(j);
					if(menu.getParentMenuId().equals(parentId)){
						TreeNodeBean treeNodeChildren  = new TreeNodeBean();
						treeNodeChildren.setMenuId(menu.getId());
						treeNodeChildren.setMenuName(menu.getMenuName());
						treeNodeChildren.setUrl(menu.getUrl());
						treeNode.getNodeList().add(treeNodeChildren);
						menuList.remove(j);
						j--;
					}
				}			
			}
			
			return menuTree;
		}finally{
			if(sess!=null){
				HibernateUtil.closeSession();
			}
		}

	}
	
}
