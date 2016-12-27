package com.sfeir.school.microservices.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sfeir.school.microservices.service.dto.ProductDto;

@FeignClient("product-service")
public interface ProductClient {

	@RequestMapping(value="/products/{productId}", method=RequestMethod.GET)
    ProductDto getProduct(@PathVariable("productId") int productId);
}
