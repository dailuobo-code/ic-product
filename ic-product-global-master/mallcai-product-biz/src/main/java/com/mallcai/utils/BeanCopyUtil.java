package com.mallcai.utils;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 通用的beancopy
 * 支持单beancopy，bean列表copy
 * @param <S> 源类型
 * @param <T> 目标类型
 */
public class BeanCopyUtil<S,T> {
    public static<S,T> List<T> copyList(List<S> sources, Class<T> target) {
        if (sources == null) {
            return null;
        }
        try {
            List<T> infoTos = new ArrayList<>();
            T infoTo = null;
            for (S info : sources) {

                infoTo = target.newInstance();
                BeanUtils.copyProperties(info, infoTo);
                infoTos.add(infoTo);
            }
            return infoTos;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static<S,T> T copyBean(S sources, Class<T> target) {
        if (sources == null) {
            return null;
        }
        T infoTo = null;
        try {
            infoTo = target.newInstance();
            BeanUtils.copyProperties(sources, infoTo);
            return infoTo;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
