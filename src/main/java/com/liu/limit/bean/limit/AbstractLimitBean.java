package com.liu.limit.bean.limit;

import com.liu.limit.annotation.LimitConfig;

public abstract class AbstractLimitBean {

    protected String limitKey;

    protected LimitConfig config;

    protected boolean acquired;

    protected AbstractLimitBean(String limitKey, LimitConfig config) {
        if (limitKey == null || "".equals(limitKey) || config == null) {
            throw new IllegalArgumentException("init LimitCheckService error illegalArgument");
        }
        this.limitKey = limitKey;
        this.config = config;
    }

    public abstract boolean tryAcquire() throws Exception;

    public abstract void release();
}
