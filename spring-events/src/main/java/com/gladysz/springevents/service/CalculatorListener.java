package com.gladysz.springevents.service;


import com.gladysz.springevents.event.CalculatorEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class CalculatorListener implements ApplicationListener<CalculatorEvent> {

    public static final Logger LOGGER = LoggerFactory.getLogger(CalculatorListener.class);

    @Override
    public void onApplicationEvent(CalculatorEvent event) {
        LOGGER.info("Calculator operation: {}", event.getOperation());
        LOGGER.info("Number1: {}", event.getNumebr1());
        LOGGER.info("Number2: {}", event.getNumebr2());
        LOGGER.info("Result: {}", event.getResult());
    }
}
