package com.liu.limit.bean.limit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.liu.limit.annotation.LimitConfig;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class RequestsPerUnitLimitBean extends AbstractLimitBean {

    private static final ConcurrentHashMap<String, LoadingCache<Long, AtomicLong>> REQUESTS_PER_UNIT_MAP = new ConcurrentHashMap<String, LoadingCache<Long, AtomicLong>>();

    public RequestsPerUnitLimitBean(String limitKey, LimitConfig config) {
        super(limitKey, config);
    }

    public boolean tryAcquire() throws Exception {
        LoadingCache<Long, AtomicLong> cache = REQUESTS_PER_UNIT_MAP.get(limitKey);
        if (cache == null) {
            cache = CacheBuilder.newBuilder()
                    .expireAfterWrite(config.expireSeconds(), TimeUnit.SECONDS)
                    .build(new CacheLoader<Long, AtomicLong>() {
                        @Override
                        public AtomicLong load(Long seconds) throws Exception {
                            return new AtomicLong(0);
                        }
                    });
            REQUESTS_PER_UNIT_MAP.putIfAbsent(limitKey, cache);
        }
        return REQUESTS_PER_UNIT_MAP.get(limitKey).get(System.currentTimeMillis() / 1000).incrementAndGet() <= config.limit();
    }
}
