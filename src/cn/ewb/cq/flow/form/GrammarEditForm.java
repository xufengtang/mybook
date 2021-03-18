package cn.ewb.cq.flow.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

import cn.ewb.cq.domain.UnitInfo;

public class GrammarEditForm extends ActionForm{
	
	private String editId;
	private String levelValue;
	private String unitValue;
	private String titleName;
	private String followValue;
	private String explainValue;
	private String example1;
	private String example2;
	private String example3;
	private String example4;
	private String example5;
	private String example6;
	private String content;
	private String orderNum;
	
	private String levelValueSearch;
	private String unitValueSearch;
	private String grammarTitleSearch;
	private int currentPage;
	
	
	private List<UnitInfo> unitList = new ArrayList<UnitInfo>();
	
	
	public String getEditId() {
		return editId;
	}

	public void setEditId(String editId) {
		this.editId = editId;
	}

	public List<UnitInfo> getUnitList() {
		return unitList;
	}

	public void setUnitList(List<UnitInfo> unitList) {
		this.unitList = unitList;
	}

	public String getLevelValue() {
		return levelValue;
	}

	public void setLevelValue(String levelValue) {
		this.levelValue = levelValue;
	}

	public String getUnitValue() {
		return unitValue;
	}

	public void setUnitValue(String unitValue) {
		this.unitValue = unitValue;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	public String getFollowValue() {
		return followValue;
	}

	public void setFollowValue(String followValue) {
		this.followValue = followValue;
	}

	public String getExplainValue() {
		return explainValue;
	}

	public void setExplainValue(String explainValue) {
		this.explainValue = explainValue;
	}

	public String getExample1() {
		return example1;
	}

	public void setExample1(String example1) {
		this.example1 = example1;
	}

	public String getExample2() {
		return example2;
	}

	public void setExample2(String example2) {
		this.example2 = example2;
	}

	public String getExample3() {
		return example3;
	}

	public void setExample3(String example3) {
		this.example3 = example3;
	}

	public String getExample4() {
		return example4;
	}

	public void setExample4(String example4) {
		this.example4 = example4;
	}

	public String getExample5() {
		return example5;
	}

	public void setExample5(String example5) {
		this.example5 = example5;
	}

	public String getExample6() {
		return example6;
	}

	public void setExample6(String example6) {
		this.example6 = example6;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

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
	
	
}
