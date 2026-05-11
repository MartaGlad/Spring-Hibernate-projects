package com.gladysz.springevents.service;

import com.gladysz.springevents.event.ProductRegisteredEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ProductImageManagerListener implements ApplicationListener<ProductRegisteredEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductImageManagerListener.class);

    @Override
    public void onApplicationEvent(ProductRegisteredEvent event) {

        LOGGER.info("\nProcessing image of: {} \nThe graphics is: {} ",
                event.getProductName(), event.getOtherData() );
    }
}
