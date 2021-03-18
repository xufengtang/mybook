package cn.ewb.cq.flow.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

import cn.ewb.cq.domain.PubDict;

public class MovieInfoEditForm extends ActionForm {

	private String editId;
	private String movieType;
	private String movieName;
	private String movieUrl;
	private String orderNum;
	private List<PubDict> movieTypeList;
	private String movieTypeSearch;
	private int currentPage = 1;
	

	public String getEditId() {
		return editId;
	}

	public void setEditId(String editId) {
		this.editId = editId;
	}

	public String getMovieType() {
		return movieType;
	}

	public void setMovieType(String movieType) {
		this.movieType = movieType;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getMovieUrl() {
		return movieUrl;
	}

	public void setMovieUrl(String movieUrl) {
		this.movieUrl = movieUrl;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public List<PubDict> getMovieTypeList() {
		return movieTypeList;
	}

	public void setMovieTypeList(List<PubDict> movieTypeList) {
		this.movieTypeList = movieTypeList;
	}

	public String getMovieTypeSearch() {
		return movieTypeSearch;
	}

	public void setMovieTypeSearch(String movieTypeSearch) {
		this.movieTypeSearch = movieTypeSearch;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	

}
