package cn.ewb.cq.framework.form;

import org.apache.struts.action.ActionForm;

public class LoginForm extends ActionForm {

	private String userCode="";
	private String pwd="";
	
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
}
