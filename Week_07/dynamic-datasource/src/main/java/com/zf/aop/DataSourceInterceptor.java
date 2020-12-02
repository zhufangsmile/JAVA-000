package com.zf.aop;

import com.zf.annotaion.DataSourceSelector;
import com.zf.config.DataSourceContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author zhufang
 * @date 2020/11/30 3:53 下午
 */
@Aspect
@Component
public class DataSourceInterceptor {
    @Around("execution(* com.zf.service.*.*(..))")
    public Object interceptor(ProceedingJoinPoint pjp){
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Class declaringType = signature.getDeclaringType();
        Method method = signature.getMethod();
        try {
            if (method.isAnnotationPresent(DataSourceSelector.class)) {
                DataSourceSelector dataSourceSelector = method.getAnnotation(DataSourceSelector.class);
                DataSourceContextHolder.setDataSource(dataSourceSelector.value());
            }else if (declaringType.isAnnotationPresent(DataSourceSelector.class)) {
                DataSourceSelector dataSourceSelector = (DataSourceSelector) declaringType.getAnnotation(DataSourceSelector.class);
                DataSourceContextHolder.setDataSource(dataSourceSelector.value());
            }else {
                DataSourceContextHolder.setDataSource(DataSourceSelector.MASTER);
            }
            System.out.println(DataSourceContextHolder.getDataSource());
            Object result = pjp.proceed();
            return result;
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            DataSourceContextHolder.removeDataSource();
        }
        return null;
    }

}
