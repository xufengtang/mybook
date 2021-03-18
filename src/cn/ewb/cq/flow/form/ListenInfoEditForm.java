package cn.ewb.cq.flow.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

import cn.ewb.cq.domain.PubDict;

public class ListenInfoEditForm extends ActionForm {

	private String editId;
	private String levelId;
	private String listenName;
	private String listenUrl;
	private String orderNum;
	private List<PubDict> levelList;
	private String levelIdSearch;
	private int currentPage = 1;

	public String getEditId() {
		return editId;
	}

	public void setEditId(String editId) {
		this.editId = editId;
	}

	public String getLevelId() {
		return levelId;
	}

	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}

	public String getListenName() {
		return listenName;
	}

	public void setListenName(String listenName) {
		this.listenName = listenName;
	}

	public String getListenUrl() {
		return listenUrl;
	}

	public void setListenUrl(String listenUrl) {
		this.listenUrl = listenUrl;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public List<PubDict> getLevelList() {
		return levelList;
	}

	public void setLevelList(List<PubDict> levelList) {
		this.levelList = levelList;
	}

	public String getLevelIdSearch() {
		return levelIdSearch;
	}

	public void setLevelIdSearch(String levelIdSearch) {
		this.levelIdSearch = levelIdSearch;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

}
