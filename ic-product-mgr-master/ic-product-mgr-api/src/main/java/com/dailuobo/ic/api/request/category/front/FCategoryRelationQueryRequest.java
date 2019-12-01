package com.dailuobo.ic.api.request.category.front;

import com.dailuobo.ic.api.base.CityBaseRequest;
import lombok.*;

import java.math.BigInteger;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FCategoryRelationQueryRequest extends CityBaseRequest {

    private static final long serialVersionUID = 406885135182252758L;

    /**
     * 前台一级或者二级类目Id
     */
    private BigInteger lv1frontCategoryId;

    /**
     * 关联类型
     */
    private Integer relationType;

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



    @Override
    public boolean checkParams() {
        if (cityId == null || lv1frontCategoryId == null) {
            throw new IllegalArgumentException("参数异常，请检查参数列表");
        }
        return true;
    }
}
