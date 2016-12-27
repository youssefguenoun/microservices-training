package com.sfeir.school.microservices.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sfeir.school.microservices.service.dto.RecommendationDto;

@FeignClient("recommendation-service")
public interface RecommendationClient {

	@GetMapping("/recommendations")
    List<RecommendationDto> getRecommendations(@RequestParam(value = "productId",  required = true) int productId);
}
