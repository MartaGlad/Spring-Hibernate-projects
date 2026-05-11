package com.gladysz.springevents.controller;

import com.gladysz.springevents.domain.ProductDto;
import com.gladysz.springevents.event.ProductRegisteredEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/products")
public class ProductController {

    private final ApplicationEventPublisher publisher;

    public ProductController(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @PostMapping(path = "createProduct")
    public void createProduct(@RequestBody ProductDto productDto) {

        System.out.println("Register product: " + productDto.getProductName());

        publisher.publishEvent(
                new ProductRegisteredEvent(
                        this,
                        productDto.getProductName(),
                        productDto.getOtherData()
                )
        );
    }
}
