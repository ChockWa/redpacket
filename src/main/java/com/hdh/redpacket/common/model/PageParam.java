package com.hdh.redpacket.common.model;

/**
 * 分页参数
 *
 */
public class PageParam {
	
	private static final int DEFAULT_NO = 1;//默认第一页
	
	private static final int DEFAULT_SIZE = 10;//默认每页20条

	private Integer pageNo = DEFAULT_NO;//页码
	
	private Integer pageSize = DEFAULT_SIZE;//每页记录数
	
	public Integer getOffset() {
		return (pageNo-1)*pageSize;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo <= 0 ? DEFAULT_NO:pageNo;
	}

	public Integer getLimit() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize <= 0 ? DEFAULT_SIZE:pageSize;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}
	
}
