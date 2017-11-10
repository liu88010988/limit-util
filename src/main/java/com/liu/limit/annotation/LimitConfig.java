package com.liu.limit.annotation;

import com.liu.limit.constants.LimitType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LimitConfig {

    LimitType limitType();

    /**
     * 流量大小
     */
    double limit();

    /**
     * @see LimitType#REQUESTS_PER_UNIT 失效时间
     */
    long expireSeconds() default 1;

    /**
     * 获取流量超时时间，默认0即为fail-fast
     */
    long acquireTimeout() default 0;

    /**
     * 超时时间单位
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;
}
