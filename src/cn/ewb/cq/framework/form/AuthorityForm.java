package cn.ewb.cq.framework.form;

import java.util.ArrayList;
import java.util.List;
import org.apache.struts.action.ActionForm;

import cn.ewb.cq.com.AutoArrayList;
import cn.ewb.cq.domain.Role;
import cn.ewb.cq.framework.bean.AuthorityBean;

public class AuthorityForm extends ActionForm {
	private List<Role> roleList;
	private String selectRole;
	private List<AuthorityBean> authorityList = new AutoArrayList(AuthorityBean.class);

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public String getSelectRole() {
		return selectRole;
	}

	public void setSelectRole(String selectRole) {
		this.selectRole = selectRole;
	}

	public List<AuthorityBean> getAuthorityList() {
		return authorityList;
	}

	public void setAuthorityList(List<AuthorityBean> authorityList) {
		this.authorityList = authorityList;
	}
}
