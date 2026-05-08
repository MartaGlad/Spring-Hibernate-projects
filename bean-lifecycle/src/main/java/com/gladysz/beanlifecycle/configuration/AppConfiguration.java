package com.gladysz.beanlifecycle.configuration;

import com.gladysz.beanlifecycle.bean.MrBean;
import com.gladysz.beanlifecycle.bean.monitor.BeanMonitor;
import com.gladysz.beanlifecycle.bean.LibraryManager;
import com.gladysz.beanlifecycle.bean.monitor.LoggingBeanMonitor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfiguration {

    @Bean
    public LibraryManager libraryManager() {

        return new LibraryManager();
    }


    @Bean
    public BeanMonitor beanMonitor() {

        return new BeanMonitor();
    }


    @Bean
    public LoggingBeanMonitor loggingBeanMonitor() {

        return new LoggingBeanMonitor();
    }


    @Bean
    @Scope("prototype")
    public MrBean mrBean() {

        return new MrBean();
    }
}
