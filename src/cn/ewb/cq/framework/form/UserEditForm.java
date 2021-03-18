package cn.ewb.cq.framework.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

import cn.ewb.cq.domain.Department;
import cn.ewb.cq.domain.Post;
import cn.ewb.cq.domain.Role;

public class UserEditForm extends ActionForm {
	private String editId;
	private String userCode;
	private String userName;
	private String postId;
	private String[] roleId;
	private String departId;
	private String[] userDepartId;
	private List<Post> postList;
	private List<Department> departmentList;
	private List<Role> roleList;

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String[] getRoleId() {
		return roleId;
	}

	public void setRoleId(String[] roleId) {
		this.roleId = roleId;
	}

	public String getDepartId() {
		return departId;
	}

	public void setDepartId(String departId) {
		this.departId = departId;
	}

	public String[] getUserDepartId() {
		return userDepartId;
	}

	public void setUserDepartId(String[] userDepartId) {
		this.userDepartId = userDepartId;
	}

	public List<Post> getPostList() {
		return postList;
	}

	public void setPostList(List<Post> postList) {
		this.postList = postList;
	}

	public List<Department> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<Department> departmentList) {
		this.departmentList = departmentList;
	}

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
}
