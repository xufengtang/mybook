package cn.ewb.cq.flow.form;

import java.util.List;
import org.apache.struts.action.ActionForm;

import cn.ewb.cq.domain.ListenInfo;
import cn.ewb.cq.domain.PubDict;

public class ListenInfoSearchForm extends ActionForm {

	private String levelIdSearch;
	private List<ListenInfo> listenInfoList;

	private List<PubDict> levelList;
	private int currentPage = 1;
	private int totalPage = 1;
	private String goPage;


	public String getLevelIdSearch() {
		return levelIdSearch;
	}

	public void setLevelIdSearch(String levelIdSearch) {
		this.levelIdSearch = levelIdSearch;
	}

	public List<PubDict> getLevelList() {
		return levelList;
	}

	public void setLevelList(List<PubDict> levelList) {
		this.levelList = levelList;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public String getGoPage() {
		return goPage;
	}

	public void setGoPage(String goPage) {
		this.goPage = goPage;
	}

	public List<ListenInfo> getListenInfoList() {
		return listenInfoList;
	}

	public void setListenInfoList(List<ListenInfo> listenInfoList) {
		this.listenInfoList = listenInfoList;
	}

}
