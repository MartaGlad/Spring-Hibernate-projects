package com.gladysz.springevents.service;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class AllEventsListener implements ApplicationListener {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("Spring event: " + event.getClass().getName());
        System.out.println("Source: " + event.getSource().getClass().getName());
        System.out.println("----------------------------------------------------------");
    }
}
