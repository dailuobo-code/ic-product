package com.mallcai.domain.dataobject.category;

import javax.annotation.Resource;
import java.io.Serializable;

public class FcatRelatedItemCntDO implements Serializable {

    private static final long serialVersionUID = -6490617709498314461L;

    private Long frontCategoryId;
    private Integer cnt;

    public Long getFrontCategoryId() {
        return frontCategoryId;
    }

    public void setFrontCategoryId(Long frontCategoryId) {
        this.frontCategoryId = frontCategoryId;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }
}
