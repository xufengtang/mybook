package cn.ewb.cq.flow.form;

import org.apache.struts.action.ActionForm;

public class NHKInfoForm extends ActionForm{

	private String editId;
	private String urlName;

	public String getEditId() {
		return editId;
	}

	public void setEditId(String editId) {
		this.editId = editId;
	}

	public String getUrlName() {
		return urlName;
	}

	public void setUrlName(String urlName) {
		this.urlName = urlName;
	}

}
