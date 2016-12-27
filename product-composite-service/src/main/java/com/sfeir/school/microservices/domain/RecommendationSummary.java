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
public class RecommendationSummary {

    private int recommendationId;
    private String author;
    private int rate;
}