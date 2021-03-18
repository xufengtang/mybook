package cn.ewb.cq.framework.form;

import org.apache.struts.action.ActionForm;

public class PwdForm extends ActionForm {

	private String oldPwd;
	private String newPwd;
	private String surePwd;

	public String getOldPwd() {
		return oldPwd;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public String getSurePwd() {
		return surePwd;
	}

	public void setSurePwd(String surePwd) {
		this.surePwd = surePwd;
	}
}
