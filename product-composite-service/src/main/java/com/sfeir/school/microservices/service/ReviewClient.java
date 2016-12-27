package com.sfeir.school.microservices.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sfeir.school.microservices.service.dto.ReviewDto;

@FeignClient("review-service")
public interface ReviewClient {

	@GetMapping("/reviews")
	List<ReviewDto> getReviews(@RequestParam(value = "productId", required = true) int productId);
}
