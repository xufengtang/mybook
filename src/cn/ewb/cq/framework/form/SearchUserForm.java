package cn.ewb.cq.framework.form;

import java.util.List;

import org.apache.struts.action.ActionForm;
import cn.ewb.cq.domain.Department;
import cn.ewb.cq.framework.bean.UserBean;

public class SearchUserForm extends ActionForm {
	private String userCodeSearch;
	private String userNameSearch;
	private String departIdSearch;
	private List<Department> departmentList = null;
	private List<UserBean> userList = null;
	private int currentPage = 1;
	private int totalPage = 1;
	private String goPage;

	public String getUserCodeSearch() {
		return userCodeSearch;
	}

	public void setUserCodeSearch(String userCodeSearch) {
		this.userCodeSearch = userCodeSearch;
	}

	public String getUserNameSearch() {
		return userNameSearch;
	}

	public void setUserNameSearch(String userNameSearch) {
		this.userNameSearch = userNameSearch;
	}

	public String getDepartIdSearch() {
		return departIdSearch;
	}

	public void setDepartIdSearch(String departIdSearch) {
		this.departIdSearch = departIdSearch;
	}

	public List<Department> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<Department> departmentList) {
		this.departmentList = departmentList;
	}

	public List<UserBean> getUserList() {
		return userList;
	}

	public void setUserList(List<UserBean> userList) {
		this.userList = userList;
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
}
