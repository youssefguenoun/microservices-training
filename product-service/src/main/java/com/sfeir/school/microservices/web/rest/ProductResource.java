package com.sfeir.school.microservices.web.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sfeir.school.microservices.domain.Product;

@RestController
public class ProductResource {

    /**
     * Sample usage: curl $HOST:$PORT/product/1
     *
     * @param productId
     * @return
     */
    @GetMapping("/product/{productId}")
    public Product getProduct(@PathVariable int productId) {

        return new Product(productId, "name", 123);
    }
}
