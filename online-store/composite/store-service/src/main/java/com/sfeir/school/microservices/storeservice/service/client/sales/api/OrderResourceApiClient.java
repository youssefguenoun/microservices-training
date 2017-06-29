package com.sfeir.school.microservices.storeservice.service.client.sales.api;

import com.sfeir.school.microservices.storeservice.service.client.sales.conf.FeignFormatterRegistrer;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(name="${sales-service.name:sales}", url="${sales-service.url:http://localhost:52002}", configuration = FeignFormatterRegistrer.class)
public interface OrderResourceApiClient extends OrderResourceApi {
}