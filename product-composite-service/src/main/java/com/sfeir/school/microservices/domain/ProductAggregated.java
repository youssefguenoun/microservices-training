package com.sfeir.school.microservices.domain;

import java.util.List;
import java.util.stream.Collectors;

import com.sfeir.school.microservices.service.dto.ProductDto;
import com.sfeir.school.microservices.service.dto.RecommendationDto;
import com.sfeir.school.microservices.service.dto.ReviewDto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author youssefguenoun
 */
@Data
@NoArgsConstructor
public class ProductAggregated {
	
    public ProductAggregated(ProductDto product, List<RecommendationDto> recommendations2,
			List<ReviewDto> reviewsResult) {
    	// 1. Setup product info
        this.productId = product.getProductId();
        this.name = product.getName();
        this.weight = product.getWeight();

        // 2. Copy summary recommendation info, if available
        if (recommendations2 != null)
            this.recommendations = recommendations2.stream()
                .map(r -> new RecommendationSummary(r.getRecommendationId(), r.getAuthor(), r.getRate()))
                .collect(Collectors.toList());

        // 3. Copy summary review info, if available
        if (reviewsResult != null)
            this.reviews = reviewsResult.stream()
                .map(r -> new ReviewSummary(r.getReviewId(), r.getAuthor(), r.getSubject()))
                .collect(Collectors.toList());
	}
    
	private int productId;
    private String name;
    private int weight;
    private List<RecommendationSummary> recommendations;
    private List<ReviewSummary> reviews;
}