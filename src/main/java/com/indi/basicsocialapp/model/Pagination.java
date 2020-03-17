package com.indi.basicsocialapp.model;

public class Pagination {

	private int pageNumber;
	
	private int limit;

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public Pagination(int pageNumber, int limit) {
		super();
		this.pageNumber = pageNumber;
		this.limit = limit;
	}
	
	public Pagination() {
		super();
	}
	
}
