package com.liu.limit.constants;

import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.RateLimiter;
import com.liu.limit.factory.LimitBeanFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;

public class LimitHolder {
    public static final ConcurrentHashMap<String, LoadingCache<Long, AtomicLong>> REQUESTS_PER_UNIT_MAP = new ConcurrentHashMap<String, LoadingCache<Long, AtomicLong>>();
    public static final ConcurrentHashMap<String, RateLimiter> RATE_LIMIT_MAP = new ConcurrentHashMap<String, RateLimiter>();
    public static final ConcurrentHashMap<String, Semaphore> SEMAPHORE_LIMIT_MAP = new ConcurrentHashMap<String, Semaphore>();
    public static final ConcurrentHashMap<LimitType, LimitBeanFactory> LIMIT_FACTORY_MAP = new ConcurrentHashMap<LimitType, LimitBeanFactory>();
}
