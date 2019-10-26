package com.company.project.configurer.datasource.aspect;

import com.company.project.configurer.datasource.MultiDataSource;
import com.company.project.configurer.datasource.annotation.DataSource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class DataSourceAspect {

    /**
     * @within匹配类上的注解
     * @annotation匹配方法上的注解
     */
    @Pointcut("@within(com.company.project.configurer.datasource.annotation.DataSource)||@annotation(com.company.project.configurer.datasource.annotation.DataSource)")
    public void pointcut() {
    }

    @Before(value = "pointcut()")
    public void beforeOpt(JoinPoint joinPoint) {
        Object target = joinPoint.getTarget();
        Class<?> clazz = target.getClass();
        Method[] methods = clazz.getMethods();
        DataSource annotation = null;
        for (Method method : methods) {
            if (joinPoint.getSignature().getName().equals(method.getName())) {
                annotation = method.getAnnotation(DataSource.class);
                if (annotation == null) {
                    annotation = joinPoint.getTarget().getClass().getAnnotation(DataSource.class);
                    if (annotation == null) {
                        return;
                    }
                }
            }
        }

        String dataSourceName = annotation.value();
        MultiDataSource.setDataSourceKey(dataSourceName);
        System.out.println("切到" + dataSourceName + "数据库");
    }

    @After(value = "pointcut()")
    public void afterOpt() {
        MultiDataSource.toDefault();
        System.out.println("切回默认数据库");
    }
}
