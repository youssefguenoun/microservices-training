package com.sfeir.school.microservices.storeservice.service.client.sales.api;

import com.sfeir.school.microservices.storeservice.service.client.sales.conf.FeignFormatterRegistrer;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(name="${sales-service.name}", configuration = FeignFormatterRegistrer.class)
public interface OrderResourceApiClient extends OrderResourceApi {
}
