package com.gladysz.springevents.domain;

public class ProductDto {

    private String productName;
    private String otherData;

    public ProductDto() {}


    public ProductDto(String productName, String otherData) {
        this.productName = productName;
        this.otherData = otherData;
    }


    public String getProductName() {

        return productName;
    }


    public String getOtherData() {

        return otherData;
    }


    public void setProductName(String productName) {

        this.productName = productName;
    }


    public void setOtherData(String otherData) {

        this.otherData = otherData;
    }
}
