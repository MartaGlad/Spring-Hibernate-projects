package com.gladysz.springevents.service;

import com.gladysz.springevents.event.ProductRegisteredEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ProductImageManagerListener implements ApplicationListener<ProductRegisteredEvent> {

    @Override
    public void onApplicationEvent(ProductRegisteredEvent event) {

        System.out.println("Processing image of " + event.getProductName());
        System.out.println("The graphics is " + event.getOtherData());
    }
}
