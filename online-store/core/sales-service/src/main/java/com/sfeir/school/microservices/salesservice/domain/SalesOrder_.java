package com.sfeir.school.microservices.salesservice.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

/**
 * Created by youssefguenoun on 20/06/2017.
 */
@StaticMetamodel(SalesOrder.class)
public class SalesOrder_ {

    public static volatile SingularAttribute<SalesOrder, Long> id;
    public static volatile SingularAttribute<SalesOrder, ZonedDateTime> orderDate;
    public static volatile SingularAttribute<SalesOrder, String> customer;
    public static volatile SingularAttribute<SalesOrder, String> productRef;
    public static volatile SingularAttribute<SalesOrder, Integer> quantity;
    public static volatile SingularAttribute<SalesOrder, BigDecimal> unitPrice;

}
