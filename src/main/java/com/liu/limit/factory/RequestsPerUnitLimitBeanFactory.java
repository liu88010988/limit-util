package com.liu.limit.factory;

import com.liu.limit.annotation.LimitConfig;
import com.liu.limit.bean.limit.AbstractLimitBean;
import com.liu.limit.bean.limit.RequestsPerUnitLimitBean;

public class RequestsPerUnitLimitBeanFactory implements LimitBeanFactory {
    public AbstractLimitBean build(String limitKey, LimitConfig config) {
        return new RequestsPerUnitLimitBean(limitKey, config);
    }
}
