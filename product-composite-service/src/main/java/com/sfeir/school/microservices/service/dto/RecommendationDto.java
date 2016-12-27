package com.sfeir.school.microservices.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RecommendationDto {
    private int productId;
    private int recommendationId;
    private String author;
    private int rate;
    private String content;
}
