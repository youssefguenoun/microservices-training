package com.sfeir.school.microservices.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sfeir.school.microservices.service.dto.ProductDto;

@FeignClient("product-service")
public interface ProductClient {

	@GetMapping("/products/{productId}")
    ProductDto getProduct(@PathVariable int productId);
}
