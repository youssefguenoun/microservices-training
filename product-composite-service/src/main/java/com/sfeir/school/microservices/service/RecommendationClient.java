package com.sfeir.school.microservices.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sfeir.school.microservices.service.dto.RecommendationDto;

@FeignClient("recommendation-service")
public interface RecommendationClient {

	@RequestMapping(value="/recommendations", method=RequestMethod.GET)
    List<RecommendationDto> getRecommendations(@RequestParam(value = "productId",  required = true) int productId);
}
