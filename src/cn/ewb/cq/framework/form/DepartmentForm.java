package cn.ewb.cq.framework.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

import cn.ewb.cq.domain.Department;

public class DepartmentForm extends ActionForm {

	private List<Department> departList;
	private String departmentCode;
	private String departmentName;
	private String bankCode;
	private String editId;

	public List<Department> getDepartList() {
		return departList;
	}

	public void setDepartList(List<Department> departList) {
		this.departList = departList;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getEditId() {
		return editId;
	}

	public void setEditId(String editId) {
		this.editId = editId;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	
	
}
