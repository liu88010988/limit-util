package com.liu.limit.factory;

import com.liu.limit.annotation.LimitConfig;
import com.liu.limit.bean.limit.AbstractLimitBean;
import com.liu.limit.bean.limit.RequestsPerUnitBean;

public class RequestsPerUnitBeanFactory implements LimitBeanFactory {
    public AbstractLimitBean build(String limitKey, LimitConfig config) {
        return new RequestsPerUnitBean(limitKey, config);
    }
}
