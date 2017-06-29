package com.sfeir.school.microservices.salesservice.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel
public class SalesViewDTO {

    //@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @ApiModelProperty(dataType = "DateTime")
    private ZonedDateTime orderDate;

    @NotNull
    private String customer;

    @NotNull
    private String productRef;

    private Integer quantity;

    private BigDecimal unitPrice;
}
