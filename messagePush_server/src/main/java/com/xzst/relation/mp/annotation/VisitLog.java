package com.xzst.relation.mp.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//注解使用在方法，类上
@Target({ElementType.METHOD,ElementType.TYPE})
//运行期使用
@Retention(value = RetentionPolicy.RUNTIME)
public @interface VisitLog {

    //方法和类 上使用
    String value() default "";
    //描述，方法和类上都可以使用
    String desc() default "无描述";
}
