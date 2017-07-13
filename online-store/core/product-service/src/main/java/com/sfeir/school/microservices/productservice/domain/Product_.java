package com.sfeir.school.microservices.productservice.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;

/**
 * Created by youssefguenoun on 16/06/2017.
 */
@StaticMetamodel(Product.class)
public class Product_ {

    public static volatile SingularAttribute<Product, Long> id;
    public static volatile SingularAttribute<Product, String> ref;
    public static volatile SingularAttribute<Product, String> name;
    public static volatile SingularAttribute<Product, String> category;
    public static volatile SingularAttribute<Product, BigDecimal> unitPrice;
}
