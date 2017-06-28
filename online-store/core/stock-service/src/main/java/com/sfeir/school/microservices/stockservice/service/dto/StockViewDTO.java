package com.sfeir.school.microservices.stockservice.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by youssefguenoun on 21/06/2017.
 */
@Data
@NoArgsConstructor
public class StockViewDTO {

    private Long id;

    private String productRef;

    private Integer quantity;
}
