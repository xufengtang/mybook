package cn.ewb.cq.framework.form;

import org.apache.struts.action.ActionForm;

public class UserInfoEditForm extends ActionForm {
	private String editId;
	private String telephone;

	public String getEditId() {
		return editId;
	}

	public void setEditId(String editId) {
		this.editId = editId;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

}
