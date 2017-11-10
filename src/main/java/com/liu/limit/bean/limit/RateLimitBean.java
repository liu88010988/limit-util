package com.liu.limit.bean.limit;

import com.google.common.util.concurrent.RateLimiter;
import com.liu.limit.annotation.LimitConfig;
import com.liu.limit.constants.LimitHolder;

public class RateLimitBean extends AbstractLimitBean {

    public RateLimitBean(String limitKey, LimitConfig config) {
        super(limitKey, config);
    }

    public boolean tryAcquire() throws Exception {
        RateLimiter limiter = LimitHolder.RATE_LIMIT_MAP.get(limitKey);
        if (limiter == null) {
            limiter = RateLimiter.create(config.limit());
            LimitHolder.RATE_LIMIT_MAP.putIfAbsent(limitKey, limiter);
        }
        return LimitHolder.RATE_LIMIT_MAP.get(limitKey).tryAcquire(config.acquireTimeout(), config.timeUnit());
    }

    public void release() {

    }
}
