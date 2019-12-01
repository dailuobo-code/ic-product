package com.mallcai.bs.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

/**
 * 服务层抽象基类
 * <p>
 * Created by Cowboy on 2016/4/14.
 */
public abstract class BaseService {
    protected Logger logger;

    @PostConstruct
    public void init() {
        logger = LoggerFactory.getLogger(this.getClass());
    }


}
