package com.liu.limit.bean.limit;

import com.liu.limit.annotation.LimitConfig;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

public class SemaphoreLimitBean extends AbstractLimitBean {
    private static final ConcurrentHashMap<String, Semaphore> SEMAPHORE_LIMIT_MAP = new ConcurrentHashMap<String, Semaphore>();

    public SemaphoreLimitBean(String limitKey, LimitConfig config) {
        super(limitKey, config);
    }

    public boolean tryAcquire() throws Exception {
        Semaphore semaphore = SEMAPHORE_LIMIT_MAP.get(limitKey);
        if (semaphore == null) {
            semaphore = new Semaphore(Double.valueOf(config.limit()).intValue());
            SEMAPHORE_LIMIT_MAP.putIfAbsent(limitKey, semaphore);
        }
        acquired = SEMAPHORE_LIMIT_MAP.get(limitKey).tryAcquire(config.acquireTimeout(), config.timeUnit());
        return acquired;
    }

    public void release() {
        if (acquired) {
            Semaphore semaphore = SEMAPHORE_LIMIT_MAP.get(limitKey);
            semaphore.release();
            acquired = false;
        }
    }
}
