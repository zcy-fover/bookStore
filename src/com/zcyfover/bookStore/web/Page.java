package com.zcyfover.bookStore.web;

import java.util.List;

public class Page<T> {

	// 当前第几页
	private int pageNo;
	// 当前页的List
	private List<T> list;
	// 每页显示多少条记录
	private int pageSize = 3;
	// 共有多少条记录
	private long totalItemNumber;

	// 对pageNo进行校验
	public int getPageNo() {

		if (pageNo <= 0)
			pageNo = 1;
		if (pageNo > getTotalPageNumber())
			pageNo = getTotalPageNumber();

		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotalItemNumber() {
		return totalItemNumber;
	}

	public void setTotalItemNumber(long totalItemNumber) {
		this.totalItemNumber = totalItemNumber;
	}

	// 对pageNo进行初始化
	public Page(int pageNo) {
		super();
		this.pageNo = pageNo;
	}

	// 获取总页数，需要计算一下，存在不能整除的时候，计算一下
	public int getTotalPageNumber() {

		int totalPageNumber = (int) totalItemNumber / pageSize;
		if (totalItemNumber % pageSize != 0) {
			totalPageNumber++;
		}

		return totalPageNumber;
	}

	// 检查是否有下一页，上一页
	public boolean isHasNext() {

		if (pageNo < getTotalPageNumber()) {
			return true;
		}
		return false;

	}

	public boolean isHasPrev() {

		if (pageNo > 1) {
			return true;
		}

		return false;
	}

	// 返回前一页或上一页
	public int getPrevPage() {

		if (isHasPrev()) {
			return (getPageNo() - 1);
		}

		return getPageNo();
	}

	public int getNextPage() {

		if (isHasNext()) {
			return (getPageNo() + 1);
		}

		return getPageNo();
	}

}
