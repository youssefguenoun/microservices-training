package com.sfeir.school.microservices.storeservice.service;

import com.sfeir.school.microservices.storeservice.service.dto.CustomerProductViewDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by youssefguenoun on 21/06/2017.
 */
public interface ProductService {

    Page<CustomerProductViewDto> findAll(Pageable pageable, String category, String name);

    CustomerProductViewDto findByRef(String ref);

}
