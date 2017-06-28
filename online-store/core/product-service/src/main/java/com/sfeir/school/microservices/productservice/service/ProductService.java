package com.sfeir.school.microservices.productservice.service;

import com.sfeir.school.microservices.productservice.repository.specifications.ProductNameCategoryFilter;
import com.sfeir.school.microservices.productservice.service.dto.ProductInputDto;
import com.sfeir.school.microservices.productservice.service.dto.ProductUpdateDto;
import com.sfeir.school.microservices.productservice.service.dto.ProductViewDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by youssefguenoun on 19/06/2017.
 */
public interface ProductService {

    ProductViewDto save(ProductInputDto productInputDto) throws ProductAlreadyExistException;

    ProductViewDto findOne(Long id);

    void delete(Long id) throws ProductNotFoundException;

    ProductViewDto findByReference(String ref);

    ProductViewDto update(Long id, ProductUpdateDto productUpdateDto);

    Page<ProductViewDto> findBySpecificationsFilter(ProductNameCategoryFilter productNameCategoryFilter, Pageable pageable);


}
