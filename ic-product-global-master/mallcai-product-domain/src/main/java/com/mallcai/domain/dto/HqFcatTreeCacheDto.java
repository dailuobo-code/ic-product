package com.mallcai.domain.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HqFcatTreeCacheDto implements Serializable {

    private static final long serialVersionUID = 1378765716294682795L;

    private Long cid;
    private String cname;
    private Integer order;
    private Long pcid;

    private List<HqFcatTreeCacheDto> child = new ArrayList<>();

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public List<HqFcatTreeCacheDto> getChild() {
        return child;
    }

    public Long getPcid() {
        return pcid;
    }

    public void setPcid(Long pcid) {
        this.pcid = pcid;
    }
}
