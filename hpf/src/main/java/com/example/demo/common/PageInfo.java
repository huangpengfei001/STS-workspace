package com.example.demo.common;

import java.util.List;

public class PageInfo {

	private int pageSize;
	private int pageNum;
	private int totalPage;
	private int totalSize;

	// 数据列表，实际项目中可根据需要确认是否添加这个属性
	private List<?> data;

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		if (this.totalSize % this.pageSize != 0) {
			this.totalPage = (int) (this.totalSize / this.pageSize + 1);
		} else {
			this.totalPage = (int) (this.totalSize / this.pageSize);
		}
		if (totalPage == 0)
			totalPage = 1;
		this.totalSize = totalSize;
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

}
