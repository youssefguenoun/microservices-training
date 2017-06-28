package com.sfeir.school.microservices.productservice.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by youssefguenoun on 19/06/2017.
 */
@Data
@NoArgsConstructor
public class ProductViewDto {

    private Long id;

    @NotNull
    private String ref;

    private String name;

    private String category;

    private BigDecimal unitPrice;
}
