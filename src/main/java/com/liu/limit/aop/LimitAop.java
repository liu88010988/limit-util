package com.liu.limit.aop;

import com.liu.limit.annotation.LimitConfig;
import com.liu.limit.bean.LimitCheckBean;
import com.liu.limit.exception.OutOfLimitException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Aspect
public class LimitAop {

    @Around("@annotation(config)")
    public Object process(ProceedingJoinPoint pjp, LimitConfig config) throws Throwable {
        Signature signature = pjp.getSignature();
        LimitCheckBean checkBean = null;
        String key = String.format("%s.%s.%s", signature.getDeclaringTypeName(), signature.getName(), getArgTypes(pjp.getArgs()));
        try {
            checkBean = new LimitCheckBean(key, config);
            if (checkBean.tryAcquire()) {
                return pjp.proceed();
            }
            throw new OutOfLimitException("out of limit");
        } finally {
            if (checkBean != null) {
                checkBean.release();
            }
        }
    }

    private String getArgTypes(Object[] args) {
        List<String> list = new ArrayList<String>();
        for (Object o : args) {
            list.add(o.getClass().getSimpleName());
        }
        Collections.sort(list);
        return list.toString();
    }
}
