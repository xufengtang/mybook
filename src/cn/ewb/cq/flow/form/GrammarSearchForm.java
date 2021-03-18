package cn.ewb.cq.flow.form;

import java.util.ArrayList;
import java.util.List;
import org.apache.struts.action.ActionForm;
import cn.ewb.cq.domain.UnitInfo;
import cn.ewb.cq.flow.bean.GrammarBean;

public class GrammarSearchForm extends ActionForm {

	private String levelValueSearch;
	private String unitValueSearch;
	private String grammarTitleSearch;
	private String editId;
	private int currentPage = 1;
	private int totalPage = 1;
	private String goPage;
	private List<GrammarBean> grammarBeanList;
	private List<UnitInfo> unitList = new ArrayList<UnitInfo>();

	public String getLevelValueSearch() {
		return levelValueSearch;
	}

	public void setLevelValueSearch(String levelValueSearch) {
		this.levelValueSearch = levelValueSearch;
	}

	public String getUnitValueSearch() {
		return unitValueSearch;
	}

	public void setUnitValueSearch(String unitValueSearch) {
		this.unitValueSearch = unitValueSearch;
	}

	public String getGrammarTitleSearch() {
		return grammarTitleSearch;
	}

	public void setGrammarTitleSearch(String grammarTitleSearch) {
		this.grammarTitleSearch = grammarTitleSearch;
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

	public List<UnitInfo> getUnitList() {
		return unitList;
	}

	public void setUnitList(List<UnitInfo> unitList) {
		this.unitList = unitList;
	}

	public List<GrammarBean> getGrammarBeanList() {
		return grammarBeanList;
	}

	public void setGrammarBeanList(List<GrammarBean> grammarBeanList) {
		this.grammarBeanList = grammarBeanList;
	}

	public String getEditId() {
		return editId;
	}

	public void setEditId(String editId) {
		this.editId = editId;
	}
	
}
