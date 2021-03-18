package cn.ewb.cq.framework.form;

import java.util.List;

import org.apache.struts.action.ActionForm;
import cn.ewb.cq.domain.Post;

public class PostForm extends ActionForm {

	private List<Post> postList;
	private String postCode;
	private String postName;
	private String editId;

	public List<Post> getPostList() {
		return postList;
	}

	public void setPostList(List<Post> postList) {
		this.postList = postList;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public String getEditId() {
		return editId;
	}

	public void setEditId(String editId) {
		this.editId = editId;
	}
}
