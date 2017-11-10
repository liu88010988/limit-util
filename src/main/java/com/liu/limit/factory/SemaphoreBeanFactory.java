package com.liu.limit.factory;

import com.liu.limit.annotation.LimitConfig;
import com.liu.limit.bean.AbstractLimitBean;
import com.liu.limit.bean.SemaphoreBean;

public class SemaphoreBeanFactory implements LimitBeanFactory {

    public AbstractLimitBean build(String limitKey, LimitConfig config) {
        return new SemaphoreBean(limitKey, config);
    }
}
