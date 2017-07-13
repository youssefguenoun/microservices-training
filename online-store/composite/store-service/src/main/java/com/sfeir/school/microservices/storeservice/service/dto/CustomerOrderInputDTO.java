package com.sfeir.school.microservices.storeservice.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

/**
 * Created by youssefguenoun on 21/06/2017.
 */
@Data
@NoArgsConstructor
public class CustomerOrderInputDTO {

    private String customer;

    private ZonedDateTime orderDate;

    private String productRef;

    private Integer quantity;

    @JsonIgnore
    private Double unitPrice;

}
