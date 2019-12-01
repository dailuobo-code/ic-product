package com.mallcai.domain.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 类目关联的条目对象
 */
public class HqFcatAssociatedCacheDto implements Serializable {
    private static final long serialVersionUID = 8398165784498861890L;

    private Integer relationType;

    private List<HqFcatAssociatedItemCacheDto> items = new ArrayList<>();

    public static class HqFcatAssociatedItemCacheDto implements Serializable{
        private static final long serialVersionUID = -7123161426554314625L;
        /**
         * 类目ID或商品productNo
         */
        private String id;

        /**
         * 排序
         */
        private Integer order;

        public HqFcatAssociatedItemCacheDto(String id, Integer order) {
            this.id = id;
            this.order = order;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Integer getOrder() {
            return order;
        }

        public void setOrder(Integer order) {
            this.order = order;
        }
    }

    public Integer getRelationType() {
        return relationType;
    }

    public void setRelationType(Integer relationType) {
        this.relationType = relationType;
    }

    public List<HqFcatAssociatedItemCacheDto> getItems() {
        return items;
    }

}
