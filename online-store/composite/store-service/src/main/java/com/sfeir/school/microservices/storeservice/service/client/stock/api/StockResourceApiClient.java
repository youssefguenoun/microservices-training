package com.sfeir.school.microservices.storeservice.service.client.stock.api;

import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(name ="stock", url="${stock-service.name}")
public interface StockResourceApiClient extends StockResourceApi {
}