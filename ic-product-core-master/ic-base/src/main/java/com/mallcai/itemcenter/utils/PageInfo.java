package com.mallcai.itemcenter.utils;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Maps;

import java.util.Map;

import static com.google.common.base.Strings.isNullOrEmpty;

/**
 * PageInfo
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/22 21:47<br/>
 */
public class PageInfo {
    public static final String LIMIT = "limit";
    public static final String OFFSET = "offset";

    private Integer offset;
    private Integer limit;

    public PageInfo() {
    }

    public static PageInfo of(Integer pageNo, Integer size) {
        return new PageInfo(pageNo, size);
    }

    /**
     * 场景举例：APP列表删除或者新增item时，不需reload所有数据，只需load该item后的数据
     *
     * @param lastId 变更ID, 默认0
     * @param size   数量，默认20
     * @return
     */
    public static PageInfo fromLastId(Integer lastId, Integer size) {
        PageInfo pageInfo = new PageInfo();
        lastId = MoreObjects.firstNonNull(lastId, 0);
        size = MoreObjects.firstNonNull(size, 20);
        pageInfo.offset = lastId > 0 ? lastId : 0;
        pageInfo.limit = size > 0 ? size : 20;
        return pageInfo;
    }

    public PageInfo(Integer pageNo, Integer size) {
        pageNo = MoreObjects.firstNonNull(pageNo, 1);
        size = MoreObjects.firstNonNull(size, 20);
        limit = size > 0 ? size : 20;
        offset = (pageNo - 1) * size;
        offset = offset > 0 ? offset : 0;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Map<String, Object> toMap() {
        return toMap(null, null);
    }

    public Map<String, Object> toMap(String key1, String key2) {
        Map param = Maps.newHashMapWithExpectedSize(2);
        param.put(isNullOrEmpty(key1) ? OFFSET : key1, offset);
        param.put(isNullOrEmpty(key2) ? LIMIT : key2, limit);

        return param;
    }

    public void into(Map<String, Object> param) {
        into(param, null, null);
    }

    public void into(Map<String, Object> param, String offsetKey, String limitKey) {
        param.put(isNullOrEmpty(offsetKey) ? OFFSET : offsetKey, offset);
        param.put(isNullOrEmpty(limitKey) ? LIMIT : limitKey, limit);
    }

}
