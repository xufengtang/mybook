package cn.ewb.cq.flow.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

import cn.ewb.cq.domain.MovieInfo;
import cn.ewb.cq.domain.PubDict;


public class MovieInfoSearchForm extends ActionForm {
	
	private String movieTypeSearch;
	private List<MovieInfo> movieInfoList;
	private List<PubDict> movieTypeList;
	private int currentPage = 1;
	private int totalPage = 1;
	private String goPage;

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

	public List<MovieInfo> getMovieInfoList() {
		return movieInfoList;
	}

	public void setMovieInfoList(List<MovieInfo> movieInfoList) {
		this.movieInfoList = movieInfoList;
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
