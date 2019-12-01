/**
 * LimitPage.java
 * @author huangwei
 * @since 2016-5-4
 *  描述：
 */
package com.dailuobo.api.domain.soa;

import java.io.Serializable;
import java.util.List;

/**
 * LimitPage.java
 * @author huangwei
 * @since 2016-5-4
 *  描述：
 */
public class LimitPage<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4557936629763168589L;
	
	private List<T> data;
	
	/**
	 * 总条数
	 */
	private Integer totalCount;
	
	/**
	 * 总页数
	 */
	private Integer totalPage;
	
	/**
	 * 当前页
	 */
	private Integer currentPage;


	/**
	 * @return the data
	 */
	public List<T> getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(List<T> data) {
		this.data = data;
	}

	/**
	 * @return the totalCount
	 */
	public Integer getTotalCount() {
		return totalCount;
	}

	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * @return the totalPage
	 */
	public Integer getTotalPage() {
		return totalPage;
	}

	/**
	 * @param totalPage the totalPage to set
	 */
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

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

}
