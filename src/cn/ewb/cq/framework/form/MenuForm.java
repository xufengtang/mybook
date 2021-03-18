package cn.ewb.cq.framework.form;

import java.util.List;
import org.apache.struts.action.ActionForm;
import cn.ewb.cq.domain.Menu;
import cn.ewb.cq.framework.bean.MenuBean;

public class MenuForm extends ActionForm {
	private String editId;
	private String orderNo;
	private String menuName;
	private String rank;
	private String parentId;
	private String url;
	private List<Menu> parentMenuList;
	private List<MenuBean> menuList;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Menu> getParentMenuList() {
		return parentMenuList;
	}

	public void setParentMenuList(List<Menu> parentMenuList) {
		this.parentMenuList = parentMenuList;
	}

	public List<MenuBean> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<MenuBean> menuList) {
		this.menuList = menuList;
	}

	public String getEditId() {
		return editId;
	}

	public void setEditId(String editId) {
		this.editId = editId;
	}

}
