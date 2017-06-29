package com.sfeir.school.microservices.storeservice.service.client.product.api;

import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(name="${product-service.name:product}", url="${product-service.url:http://localhost:52001}")
public interface ProductResourceApiClient extends ProductResourceApi {
}