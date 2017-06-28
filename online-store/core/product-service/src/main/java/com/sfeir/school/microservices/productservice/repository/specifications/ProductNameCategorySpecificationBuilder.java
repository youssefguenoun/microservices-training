package com.sfeir.school.microservices.productservice.repository.specifications;

import com.sfeir.school.microservices.productservice.domain.Product;
import com.sfeir.school.microservices.productservice.domain.Product_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import static org.springframework.util.StringUtils.hasText;
import static org.springframework.util.StringUtils.isEmpty;

/**
 * Created by youssefguenoun on 19/06/2017.
 */
public class ProductNameCategorySpecificationBuilder {

    private final ProductNameCategoryFilter productNameCategoryFilter;

    public ProductNameCategorySpecificationBuilder(ProductNameCategoryFilter productNameCategoryFilter){
        this.productNameCategoryFilter = productNameCategoryFilter;
    }

    public Specification<Product> build(){

        if(hasText(productNameCategoryFilter.getCategory()) && isEmpty(productNameCategoryFilter.getName())){
            return Specifications.where(hasCategory());
        } else if(isEmpty(productNameCategoryFilter.getCategory()) && hasText(productNameCategoryFilter.getName())){
            return Specifications.where(hasName());
        } else if(hasText(productNameCategoryFilter.getCategory()) && hasText(productNameCategoryFilter.getName())){
            return Specifications.where(hasName()).and(hasCategory());
        }

        return null;
    }

    private Specification<Product> hasCategory(){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(Product_.category), productNameCategoryFilter.getCategory());
    }

    private Specification<Product> hasName(){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(Product_.name), productNameCategoryFilter.getName());
    }

}
