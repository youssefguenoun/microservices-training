package com.sfeir.school.microservices.web.rest;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sfeir.school.microservices.domain.ProductAggregated;
import com.sfeir.school.microservices.service.ProductCompositeService;
import com.sfeir.school.microservices.service.dto.ProductDto;
import com.sfeir.school.microservices.service.dto.RecommendationDto;
import com.sfeir.school.microservices.service.dto.ReviewDto;

@RestController
public class ProductCompositeResource {

	@Autowired
	private ProductCompositeService productCompositeService;
	
    @RequestMapping("/")
    public String getProduct() {
        return "{\"timestamp\":\"" + new Date() + "\",\"content\":\"Hello from ProductAPi\"}";
    }
    
    @RequestMapping("/products/{productId}")
    public ResponseEntity<ProductAggregated> getProduct(@PathVariable int productId) {
    	
    	 // 1. First get mandatory product information
        ProductDto productResult = productCompositeService.getProduct(productId);


        // 2. Get optional recommendations
        List<RecommendationDto> recommendations = productCompositeService.getRecommendations(productId);


        // 3. Get optional reviews
        List<ReviewDto> reviewsResult = productCompositeService.getReviews(productId);


        return ResponseEntity.ok(new ProductAggregated(productResult, recommendations, reviewsResult));
    	
    }
}
