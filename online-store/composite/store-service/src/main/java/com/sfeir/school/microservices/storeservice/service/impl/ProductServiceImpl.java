package com.sfeir.school.microservices.storeservice.service.impl;

import com.sfeir.school.microservices.storeservice.service.ProductService;
import com.sfeir.school.microservices.storeservice.service.dto.CustomerProductViewDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by youssefguenoun on 21/06/2017.
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public Page<CustomerProductViewDto> findAll(Pageable pageable, String category, String name) {
        return null;
    }

    @Override
    public CustomerProductViewDto findByRef(String ref) {
        return null;
    }
}
