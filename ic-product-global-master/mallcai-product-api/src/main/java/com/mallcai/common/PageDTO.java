package com.mallcai.common;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

public class PageDTO implements Serializable {
    private static final long serialVersionUID = 1699765420067189370L;
    private final static int MAX_PAGE_SIZE = 20;
    private final static int MIN_CURRENT_PAGE = 1;
    private int pageSize;
    private int totalNumber;
    private int currentPage;

    public PageDTO(int pageSize, int totalNumber, int currentPage) {
        this.setTotalNumber(totalNumber);
        this.setPageSize(pageSize);
        this.setCurrentPage(currentPage);
    }

    public PageDTO() {

    }

    public int getPageCount() {
        return this.totalNumber == 0 ? 0 : (this.totalNumber + this.pageSize - 1) / this.pageSize;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public int getCurrentPage() {
        return currentPage == 0 ? MIN_CURRENT_PAGE : currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public int getPageSize() {
        return pageSize == 0 ? MAX_PAGE_SIZE : pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
