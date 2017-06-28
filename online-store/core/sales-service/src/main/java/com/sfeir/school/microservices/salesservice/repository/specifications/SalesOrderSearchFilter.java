package com.sfeir.school.microservices.salesservice.repository.specifications;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.ZonedDateTime;

/**
 * Created by youssefguenoun on 20/06/2017.
 */
@Data
@AllArgsConstructor
public class SalesOrderSearchFilter {

    private String product;
    private ZonedDateTime fromDate;
    private ZonedDateTime toDate;

}
