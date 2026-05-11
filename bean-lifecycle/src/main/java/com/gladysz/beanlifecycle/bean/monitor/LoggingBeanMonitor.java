package com.gladysz.beanlifecycle.bean.monitor;

import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class LoggingBeanMonitor implements BeanPostProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingBeanMonitor.class);

    @Override
    public @Nullable Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        LOGGER.info("Created bean: name={}, class={}", beanName, bean.getClass().getName());

        return bean;
    }
}
