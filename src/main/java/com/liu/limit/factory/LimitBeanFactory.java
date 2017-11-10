package com.liu.limit.factory;

import com.liu.limit.annotation.LimitConfig;
import com.liu.limit.bean.limit.AbstractLimitBean;

public interface LimitBeanFactory {
    AbstractLimitBean build(String limitKey, LimitConfig config);
}
