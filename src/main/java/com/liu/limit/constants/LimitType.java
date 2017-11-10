package com.liu.limit.constants;

import com.liu.limit.factory.LimitBeanFactory;
import com.liu.limit.factory.RateLimitBeanFactory;
import com.liu.limit.factory.RequestsPerUnitLimitBeanFactory;
import com.liu.limit.factory.SemaphoreLimitBeanFactory;

public enum LimitType {
    RATE_LIMIT(new RateLimitBeanFactory()),
    REQUESTS_PER_UNIT(new RequestsPerUnitLimitBeanFactory()),
    SEMAPHORE(new SemaphoreLimitBeanFactory());

    private LimitBeanFactory factory;

    LimitType(LimitBeanFactory factory) {
        this.factory = factory;
    }

    public LimitBeanFactory getFactory() {
        return factory;
    }
}
