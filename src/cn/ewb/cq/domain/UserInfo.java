package cn.ewb.cq.domain;

import java.util.Date;
import java.util.List;

public class UserInfo {

	private String id;
	private String userCode;
	private String userName;
	private String telephone;
	private String delFlg;
	private String createId;
	private Date createTime;
	private String updateId;
	private Date updateTime;
	private Department department;
	private Post post;
	private List<UserRole> userRole;
	private List<UserDepart> userDepart;
	private Pwd passwd;

	//private List<PubNoticeUser> pubNoticeUser;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getDelFlg() {
		return delFlg;
	}

	public void setDelFlg(String delFlg) {
		this.delFlg = delFlg;
	}

	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdateId() {
		return updateId;
	}

	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public List<UserRole> getUserRole() {
		return userRole;
	}

	public void setUserRole(List<UserRole> userRole) {
		this.userRole = userRole;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public List<UserDepart> getUserDepart() {
		return userDepart;
	}

	public void setUserDepart(List<UserDepart> userDepart) {
		this.userDepart = userDepart;
	}

	public Pwd getPasswd() {
		return passwd;
	}

	public void setPasswd(Pwd passwd) {
		this.passwd = passwd;
	}


	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	
	
	
//	public List<PubNoticeUser> getPubNoticeUser() {
//		return pubNoticeUser;
//	}
//
//	public void setPubNoticeUser(List<PubNoticeUser> pubNoticeUser) {
//		this.pubNoticeUser = pubNoticeUser;
//	}
}
