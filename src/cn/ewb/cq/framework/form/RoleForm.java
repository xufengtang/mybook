package cn.ewb.cq.framework.form;

import java.util.List;
import org.apache.struts.action.ActionForm;
import cn.ewb.cq.domain.Role;

public class RoleForm extends ActionForm {

	private List<Role> roleList;
	private String roleName;
	private String editId;

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public String getEditId() {
		return editId;
	}

	public void setEditId(String editId) {
		this.editId = editId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
