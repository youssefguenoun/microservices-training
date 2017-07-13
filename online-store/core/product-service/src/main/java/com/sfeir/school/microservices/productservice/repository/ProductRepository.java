package com.sfeir.school.microservices.productservice.repository;

import com.sfeir.school.microservices.productservice.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by youssefguenoun on 16/06/2017.
 */
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    Product findByRef(String ref);

    Page<Product> findByCategory(String category, Pageable pageable);

    Page<Product> findByName(String name, Pageable pageable);

    Page<Product> findByNameAndCategory(String name,String category, Pageable pageable);
}
