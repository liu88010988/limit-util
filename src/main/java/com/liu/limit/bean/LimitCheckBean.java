package com.liu.limit.bean;

import com.liu.limit.annotation.LimitConfig;
import com.liu.limit.bean.limit.AbstractLimitBean;
import com.liu.limit.factory.LimitBeanFactory;

public class LimitCheckBean {

    private AbstractLimitBean limitBean;

    public LimitCheckBean(String limitKey, LimitConfig config) {
        LimitBeanFactory factory = config.limitType().getFactory();
        if (factory != null) {
            limitBean = factory.build(limitKey, config);
        }
    }

    public boolean tryAcquire() throws Exception {
        return limitBean == null || limitBean.tryAcquire();
    }

    public void release() {
        if (limitBean != null) {
            limitBean.release();
        }
    }
}
