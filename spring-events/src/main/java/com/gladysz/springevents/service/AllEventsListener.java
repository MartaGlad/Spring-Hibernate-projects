package com.gladysz.springevents.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class AllEventsListener implements ApplicationListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(AllEventsListener.class);


    @Override
    public void onApplicationEvent(ApplicationEvent event) {

        LOGGER.info("Spring event: {} \nSource: {}",
                event.getClass().getName(), event.getSource().getClass().getName());

        System.out.println("----------------------------------------------------------");
    }
}
