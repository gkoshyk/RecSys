package org.utd.bd.recsys.bean.response;

import java.util.List;

import org.utd.bd.recsys.bean.Artist;

public class StandardResponse<T> {

	private Integer erroCode; 
	private String errorMsg;
	private int pageNo;
	private int totalPages;
	private int totalRecords, pgStartRecord, pgEndRecord;
	private List<T> data;

	public Integer getErroCode() {
		return erroCode;
	}

	public void setErroCode(Integer erroCode) {
		this.erroCode = erroCode;
	}

	public String getErrorMsg() {
		
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public int getPgStartRecord() {
		return pgStartRecord;
	}

	public void setPgStartRecord(int pgStartRecord) {
		this.pgStartRecord = pgStartRecord;
	}

	public int getPgEndRecord() {
		return pgEndRecord;
	}

	public void setPgEndRecord(int pgEndRecord) {
		this.pgEndRecord = pgEndRecord;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

}
