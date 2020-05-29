package cn.cqu.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 记录系统日志的自定义注解
 */
//作用在方法上
@Target(value = ElementType.METHOD)
//保留策略:运行时
@Retention(RetentionPolicy.RUNTIME)
public @interface MyLog {
    //操作名称
     String actionName();

}
