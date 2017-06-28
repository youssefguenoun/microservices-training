package com.sfeir.school.microservices.productservice.repository.specifications;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by youssefguenoun on 19/06/2017.
 */
@Getter
@AllArgsConstructor
public class ProductNameCategoryFilter {

    private String name;

    private String category;
}
