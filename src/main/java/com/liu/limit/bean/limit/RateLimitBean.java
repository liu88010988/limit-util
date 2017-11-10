package com.liu.limit.bean.limit;

import com.google.common.util.concurrent.RateLimiter;
import com.liu.limit.annotation.LimitConfig;

import java.util.concurrent.ConcurrentHashMap;

public class RateLimitBean extends AbstractLimitBean {

    private static final ConcurrentHashMap<String, RateLimiter> RATE_LIMIT_MAP = new ConcurrentHashMap<String, RateLimiter>();

    public RateLimitBean(String limitKey, LimitConfig config) {
        super(limitKey, config);
    }

    public boolean tryAcquire() throws Exception {
        RateLimiter limiter = RATE_LIMIT_MAP.get(limitKey);
        if (limiter == null) {
            limiter = RateLimiter.create(config.limit());
            RATE_LIMIT_MAP.putIfAbsent(limitKey, limiter);
        }
        return RATE_LIMIT_MAP.get(limitKey).tryAcquire(config.acquireTimeout(), config.timeUnit());
    }
}
