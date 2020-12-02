package com.zf.annotaion;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
/**
 * @author zhufang
 * @date 2020/11/30 1:39 下午
 */
public @interface DataSourceSelector {
    String MASTER = "master";
    String SLAVE = "slave";

    String value() default MASTER;
}
