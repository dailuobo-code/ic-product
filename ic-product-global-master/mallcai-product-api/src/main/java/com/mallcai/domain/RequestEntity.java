package com.mallcai.domain;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Collection;

public class RequestEntity implements Serializable {
    private static final long serialVersionUID = -1291822123893536038L;

    private final static int MAX_PAGE_SIZE = 20;
    private final static int MIN_CURRENT_PAGE = 1;

    private int pageSize;
    private int currentPage;

    public int getCurrentPage() {
        return currentPage == 0 ? MIN_CURRENT_PAGE : currentPage;
    }

    public int getPageSize() {
        return pageSize == 0 ? MAX_PAGE_SIZE : pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public boolean checkParam() {
        return true;
    }

    public void notNull(Object value, String name) {
        if (value == null) {
            throw new IllegalArgumentException(name + "不能为 NULL");
        }
    }

    public void notEmpty(Collection value, String name) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException(name + "不能为空");
        }
    }

    public void nonBlank(String value, String name) {
        if (StringUtils.isEmpty(value)) {
            throw new IllegalArgumentException(name + "不能为空");
        }
    }

    public void maxSize(String value, int size, String name) {
        if (!StringUtils.isEmpty(value) && value.length() >= size) {
            throw new IllegalArgumentException(name + "长度不能超过" + size);
        }
    }
}
