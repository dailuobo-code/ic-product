package com.mallcai.itemcenter.dto;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Paging
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/15 12:46<br/>
 */
public class Paging<T> implements Serializable {

    private static final long serialVersionUID = 755183539178076901L;

    private Long total;

    private List<T> data;

    public Paging() {
    }

    public Paging(Long total, List<T> data) {
        this.data = data;
        this.total = total;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Boolean isEmpty() {
        return Objects.equals(0L, total) || data == null || data.isEmpty();
    }

    @SuppressWarnings("all")
    public static <T> Paging<T> empty(Class<T> clazz) {
        List<T> emptyList = Collections.emptyList();
        return new Paging<T>(0L, emptyList);
    }

    public static <T> Paging<T> empty() {
        return new Paging<T>(0L, Collections.<T>emptyList());
    }

    public static <T> Paging<T> of(Long total, List<T> data) {
        return new Paging<T>(total, data);
    }


    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Paging)) return false;
        Paging other = (Paging) o;
        if (!Objects.equals(this.total, other.total)) return false;
        if (!Objects.equals(this.data, other.data)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = (result * PRIME) + (this.total == null ? 0 : this.total.hashCode());
        result = (result * PRIME) + (this.data == null ? 0 : this.data.hashCode());
        return result;
    }
}
