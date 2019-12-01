/**
 * SOAStandardPage.java
 * @author huangwei
 * @since 2016-1-27
 *  描述：
 */
package com.dailuobo.api.domain.soa;

import java.io.Serializable;
import java.util.List;

/**
 * SOAStandardPage.java
 * @author huangwei
 * @since 2016-1-27
 *  描述：
 *  标准分页对象
 */
public class SOAStandardPage<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2967230209516037088L;

	/** 当前页 */
	private Integer currentPage;
	
	/** 总页数 */
	private Integer pageCount;
	
	/** 当前页对象列表 */
	private List<T> list;

	/**
	 * @return the currentPage
	 */
	public Integer getCurrentPage() {
		return currentPage;
	}

	/**
	 * @param currentPage the currentPage to set
	 */
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * @return the pageCount
	 */
	public Integer getPageCount() {
		return pageCount;
	}

	/**
	 * @param pageCount the pageCount to set
	 */
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	/**
	 * @return the list
	 */
	public List<T> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<T> list) {
		this.list = list;
	}
	
}
