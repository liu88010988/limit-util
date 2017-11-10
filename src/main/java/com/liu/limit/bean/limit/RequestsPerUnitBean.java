package com.liu.limit.bean.limit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.liu.limit.annotation.LimitConfig;
import com.liu.limit.constants.LimitHolder;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class RequestsPerUnitBean extends AbstractLimitBean {
    public RequestsPerUnitBean(String limitKey, LimitConfig config) {
        super(limitKey, config);
    }

    public boolean tryAcquire() throws Exception {
        LoadingCache<Long, AtomicLong> cache = LimitHolder.REQUESTS_PER_UNIT_MAP.get(limitKey);
        if (cache == null) {
            cache = CacheBuilder.newBuilder()
                    .expireAfterWrite(config.expireSeconds(), TimeUnit.SECONDS)
                    .build(new CacheLoader<Long, AtomicLong>() {
                        @Override
                        public AtomicLong load(Long seconds) throws Exception {
                            return new AtomicLong(0);
                        }
                    });
            LimitHolder.REQUESTS_PER_UNIT_MAP.putIfAbsent(limitKey, cache);
        }
        return LimitHolder.REQUESTS_PER_UNIT_MAP.get(limitKey).get(System.currentTimeMillis() / 1000).incrementAndGet() <= config.limit();
    }

    public void release() {

    }
}
