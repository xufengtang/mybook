package cn.ewb.cq.framework.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

public class ShowMenuForm extends ActionForm {

	private List treeList = null;
	
	public List getTreeList() {
		return treeList;
	}
	
	public void setTreeList(List treeList) {
		this.treeList = treeList;
	}
}
