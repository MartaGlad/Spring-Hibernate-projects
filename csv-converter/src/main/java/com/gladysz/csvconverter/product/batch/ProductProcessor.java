package com.gladysz.csvconverter.product.batch;

import com.gladysz.csvconverter.product.domain.Product;
import org.jspecify.annotations.Nullable;
import org.springframework.batch.infrastructure.item.ItemProcessor;


public class ProductProcessor implements ItemProcessor<Product, Product> {

    @Override
    public @Nullable Product process(Product item) {

        double newPrice = Math.round(item.getPrice() * 1.1 * 100) / 100.0;

        return new Product(item.getId(), item.getQuantity(), newPrice);
    }
}
