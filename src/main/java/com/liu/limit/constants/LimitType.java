package com.liu.limit.constants;

import com.liu.limit.factory.LimitBeanFactory;
import com.liu.limit.factory.RateLimitBeanFactory;
import com.liu.limit.factory.RequestsPerUnitBeanFactory;
import com.liu.limit.factory.SemaphoreBeanFactory;

public enum LimitType {
    RATE_LIMIT(new RateLimitBeanFactory()),
    REQUESTS_PER_UNIT(new RequestsPerUnitBeanFactory()),
    SEMAPHORE(new SemaphoreBeanFactory());

    private LimitBeanFactory factory;

    LimitType(LimitBeanFactory factory) {
        this.factory = factory;
    }

    public LimitBeanFactory getFactory() {
        return factory;
    }
}
