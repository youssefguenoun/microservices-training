package io.swagger.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import io.swagger.configuration.ClientConfiguration;

@FeignClient(name="${.name:}", url="${.url:http://localhost:52002}", configuration = ClientConfiguration.class)
public interface SalesResourceApiClient extends SalesResourceApi {
}