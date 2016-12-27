package com.sfeir.school.microservices.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewDto {
    private int productId;
    private int reviewId;
    private String author;
    private String subject;
    private String content;
}