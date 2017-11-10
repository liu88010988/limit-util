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

    double limit();

    long expireSeconds() default 1;

    long acquireTimeout() default 0;

    TimeUnit timeUnit() default TimeUnit.SECONDS;
}
