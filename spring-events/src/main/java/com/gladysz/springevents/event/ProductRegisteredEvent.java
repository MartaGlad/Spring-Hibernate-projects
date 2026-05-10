package com.gladysz.springevents.event;

import org.springframework.context.ApplicationEvent;

public class ProductRegisteredEvent extends ApplicationEvent {

    private String productName;
    private String otherData;


    public ProductRegisteredEvent(Object source, String productName, String productData) {
        super(source);
        this.productName = productName;
        this.otherData = productData;
    }


    public String getOtherData() {

        return otherData;
    }


    public String getProductName() {

        return productName;
    }
}
