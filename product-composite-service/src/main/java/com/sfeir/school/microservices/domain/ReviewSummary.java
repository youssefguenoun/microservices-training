package com.sfeir.school.microservices.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author youssefguenoun
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewSummary {

    private int reviewId;
    private String author;
    private String subject;
    
}