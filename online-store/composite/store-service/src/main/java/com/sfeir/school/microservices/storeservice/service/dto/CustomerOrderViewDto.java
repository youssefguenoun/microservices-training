package com.sfeir.school.microservices.storeservice.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

/**
 * Created by youssefguenoun on 21/06/2017.
 */
@Data
@NoArgsConstructor
public class CustomerOrderViewDto {
    private Long id;
    private String customer;

    private ZonedDateTime orderDate;

    private String productRef;

    private Integer quantity;

    private Double unitPrice;
}
