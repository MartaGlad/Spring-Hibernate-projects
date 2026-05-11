package com.gladysz.springevents.service;

import com.gladysz.springevents.event.CalculatorEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class CalculatorListener implements ApplicationListener<CalculatorEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CalculatorListener.class);

    @Override
    public void onApplicationEvent(CalculatorEvent event) {
        LOGGER.info("\nCalculator operation: {} \nNumber1: {} \nNumber2: {} \nResult: {}",
                event.getOperation(), event.getNumebr1(), event.getNumebr2(), event.getResult());
    }
}
