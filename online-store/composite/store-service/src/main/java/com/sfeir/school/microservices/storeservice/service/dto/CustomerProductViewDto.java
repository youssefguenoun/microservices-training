package com.sfeir.school.microservices.storeservice.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Created by youssefguenoun on 21/06/2017.
 */
@Data
@NoArgsConstructor
public class CustomerProductViewDto {
    private String category;

    private String name;

    private String ref;

    private Double unitPrice;

    private Integer quantity;
}
