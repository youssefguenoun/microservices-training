package com.sfeir.school.microservices.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfeir.school.microservices.service.dto.ProductDto;
import com.sfeir.school.microservices.service.dto.RecommendationDto;
import com.sfeir.school.microservices.service.dto.ReviewDto;

@Service
public class ProductCompositeService {

	@Autowired
	private ProductClient productClient;
	
	@Autowired
	private RecommendationClient recommendationClient;
	
	@Autowired
	private ReviewClient reviewClient;
	
	public ProductDto getProduct(int productId) {
		return productClient.getProduct(productId);
	}
	
	public List<RecommendationDto> getRecommendations(int productId) {
		
		return recommendationClient.getRecommendations(productId);
	}
	
	public List<ReviewDto> getReviews(int productId) {
		
		return reviewClient.getReviews(productId);
	}
}
