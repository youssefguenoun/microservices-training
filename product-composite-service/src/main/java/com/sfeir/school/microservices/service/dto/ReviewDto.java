package com.sfeir.school.microservices.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReviewDto {
    private int productId;
    private int reviewId;
    private String author;
    private String subject;
    private String content;
}