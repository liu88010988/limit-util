package com.liu.limit.bean.limit;

import com.liu.limit.annotation.LimitConfig;
import com.liu.limit.constants.LimitHolder;

import java.util.concurrent.Semaphore;

public class SemaphoreBean extends AbstractLimitBean {
    public SemaphoreBean(String limitKey, LimitConfig config) {
        super(limitKey, config);
    }

    public boolean tryAcquire() throws Exception {
        Semaphore semaphore = LimitHolder.SEMAPHORE_LIMIT_MAP.get(limitKey);
        if (semaphore == null) {
            semaphore = new Semaphore(Double.valueOf(config.limit()).intValue());
            LimitHolder.SEMAPHORE_LIMIT_MAP.putIfAbsent(limitKey, semaphore);
        }
        acquired = LimitHolder.SEMAPHORE_LIMIT_MAP.get(limitKey).tryAcquire(config.acquireTimeout(), config.timeUnit());
        return acquired;
    }

    public void release() {
        if (acquired) {
            Semaphore semaphore = LimitHolder.SEMAPHORE_LIMIT_MAP.get(limitKey);
            semaphore.release();
            acquired = false;
        }
    }
}
