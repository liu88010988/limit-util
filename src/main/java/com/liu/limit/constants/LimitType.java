package com.liu.limit.constants;

import com.liu.limit.factory.RateLimitBeanFactory;
import com.liu.limit.factory.RequestsPerUnitBeanFactory;
import com.liu.limit.factory.SemaphoreBeanFactory;

public enum LimitType {
    RATE_LIMIT {
        protected void putFactory() {
            LimitHolder.LIMIT_FACTORY_MAP.put(this, new RateLimitBeanFactory());
        }
    },
    REQUESTS_PER_UNIT {
        protected void putFactory() {
            LimitHolder.LIMIT_FACTORY_MAP.put(this, new RequestsPerUnitBeanFactory());
        }
    },
    SEMAPHORE {
        protected void putFactory() {
            LimitHolder.LIMIT_FACTORY_MAP.put(this, new SemaphoreBeanFactory());
        }
    };

    LimitType() {
        putFactory();
    }

    protected abstract void putFactory();

}
