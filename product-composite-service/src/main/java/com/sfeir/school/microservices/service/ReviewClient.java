package com.sfeir.school.microservices.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sfeir.school.microservices.service.dto.ReviewDto;

@FeignClient("review-service")
public interface ReviewClient {

	@RequestMapping(value="/reviews", method=RequestMethod.GET)
	List<ReviewDto> getReviews(@RequestParam(value = "productId", required = true) int productId);
}
