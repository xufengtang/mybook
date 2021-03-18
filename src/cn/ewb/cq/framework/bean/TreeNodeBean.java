package cn.ewb.cq.framework.bean;

import java.util.ArrayList;
import java.util.List;

public class TreeNodeBean {

	private String menuId;
	private String menuName;
	private String url;
	private List<TreeNodeBean> nodeList = new ArrayList<TreeNodeBean>();

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<TreeNodeBean> getNodeList() {
		return nodeList;
	}

	public void setNodeList(List<TreeNodeBean> nodeList) {
		this.nodeList = nodeList;
	}
}
