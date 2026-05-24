package com.gladysz.jms.domain;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

public class Order implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String productName;
    private int quantity;
    private BigDecimal price;
    private String customerEmail;

    public Order() {
    }


    public Order(Long id, String productName, int quantity, BigDecimal price, String customerEmail) {
        this.id = id;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.customerEmail = customerEmail;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getProductName() {
        return productName;
    }


    public void setProductName(String productName) {
        this.productName = productName;
    }


    public int getQuantity() {
        return quantity;
    }


    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public BigDecimal getPrice() {
        return price;
    }


    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    public String getCustomerEmail() {
        return customerEmail;
    }


    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", customerEmail='" + customerEmail + '\'' +
                '}';
    }
}
