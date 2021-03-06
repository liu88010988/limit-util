package com.liu.limit.factory;

import com.liu.limit.annotation.LimitConfig;
import com.liu.limit.bean.limit.AbstractLimitBean;
import com.liu.limit.bean.limit.RateLimitBean;

public class RateLimitBeanFactory implements LimitBeanFactory {

    public AbstractLimitBean build(String limitKey, LimitConfig config) {
        return new RateLimitBean(limitKey, config);
    }
}
