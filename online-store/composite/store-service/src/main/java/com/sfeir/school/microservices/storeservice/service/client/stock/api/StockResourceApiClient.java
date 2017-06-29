package com.sfeir.school.microservices.storeservice.service.client.stock.api;

import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(name="${stock-service.name:stock}", url="${stock-service.url:http://localhost:52003}")
public interface StockResourceApiClient extends StockResourceApi {
}