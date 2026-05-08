package com.gladysz.beanlifecycle.bean.monitor;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class BeanMonitor implements BeanPostProcessor {

    @Override
    public @Nullable Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        System.out.println("Before initialization of bean: " + beanName);

        return bean;
    }


    @Override
    public @Nullable Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        System.out.println("After initialization of bean: " + beanName);

        return bean;
    }
}
