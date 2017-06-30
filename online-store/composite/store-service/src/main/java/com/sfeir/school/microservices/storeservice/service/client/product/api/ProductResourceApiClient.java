package com.sfeir.school.microservices.storeservice.service.client.product.api;

import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(name="${product-service.name}")
public interface ProductResourceApiClient extends ProductResourceApi {
}
