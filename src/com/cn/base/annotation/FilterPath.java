package cn.base.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE,ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Repeatable(FilterPaths.class)
public @interface FilterPath {
    String  value();
}