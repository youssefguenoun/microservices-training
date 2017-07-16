package com.sfeir.school.microservices.salesservice.service.client.stock.api;

import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(name="${stock-service.name}")
public interface StockResourceApiClient extends StockResourceApi {
}
