package com.sfeir.school.microservices.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecommendationDto {
    private int productId;
    private int recommendationId;
    private String author;
    private int rate;
    private String content;
}
