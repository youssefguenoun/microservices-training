package com.sfeir.school.microservices.salesservice.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

/**
 * Created by youssefguenoun on 20/06/2017.
 */
@Data
@NoArgsConstructor
public class OrderViewDTO {

    private Long id;

    private ZonedDateTime orderDate;

    @NotNull
    private String customer;

    @NotNull
    private String productRef;

    private Integer quantity;

    private BigDecimal unitPrice;
}
