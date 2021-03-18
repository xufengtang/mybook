package cn.ewb.cq.flow.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

import cn.ewb.cq.flow.bean.UnitBean;

public class UnitSearchForm extends ActionForm {

	private String levelNameSearch;
	private List<UnitBean> unitBeanList;

	public List<UnitBean> getUnitBeanList() {
		return unitBeanList;
	}

	public void setUnitBeanList(List<UnitBean> unitBeanList) {
		this.unitBeanList = unitBeanList;
	}

	public String getLevelNameSearch() {
		return levelNameSearch;
	}

	public void setLevelNameSearch(String levelNameSearch) {
		this.levelNameSearch = levelNameSearch;
	}

}
