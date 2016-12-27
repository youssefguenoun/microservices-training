package com.sfeir.school.microservices.domain;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author youssefguenoun
 */
@Data
@NoArgsConstructor
public class ProductAggregated {
    private int productId;
    private String name;
    private int weight;
    private List<RecommendationSummary> recommendations;
    private List<ReviewSummary> reviews;
}