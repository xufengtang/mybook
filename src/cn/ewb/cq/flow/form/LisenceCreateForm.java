package cn.ewb.cq.flow.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

import cn.ewb.cq.flow.bean.LisenceInfoBean;

public class LisenceCreateForm extends ActionForm {

	private String userStatusSearch;
	private String openIdSearch;
	private String lisenceCode;
	private String successFlg = "fail";
	private int currentPage = 1;
	private int totalPage = 1;
	private String goPage;

	private List<LisenceInfoBean> lisenceInfoList;

	public String getLisenceCode() {
		return lisenceCode;
	}

	public void setLisenceCode(String lisenceCode) {
		this.lisenceCode = lisenceCode;
	}

	public String getSuccessFlg() {
		return successFlg;
	}

	public void setSuccessFlg(String successFlg) {
		this.successFlg = successFlg;
	}

	public String getUserStatusSearch() {
		return userStatusSearch;
	}

	public void setUserStatusSearch(String userStatusSearch) {
		this.userStatusSearch = userStatusSearch;
	}

	public String getOpenIdSearch() {
		return openIdSearch;
	}

	public void setOpenIdSearch(String openIdSearch) {
		this.openIdSearch = openIdSearch;
	}

	public List<LisenceInfoBean> getLisenceInfoList() {
		return lisenceInfoList;
	}

	public void setLisenceInfoList(List<LisenceInfoBean> lisenceInfoList) {
		this.lisenceInfoList = lisenceInfoList;
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

}
