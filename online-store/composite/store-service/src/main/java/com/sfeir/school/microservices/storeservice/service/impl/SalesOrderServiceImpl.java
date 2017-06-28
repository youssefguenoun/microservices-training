package com.sfeir.school.microservices.storeservice.service.impl;

import com.sfeir.school.microservices.storeservice.service.ProductNotFoundException;
import com.sfeir.school.microservices.storeservice.service.SalesOrderService;
import com.sfeir.school.microservices.storeservice.service.dto.CustomerOrderInputDTO;
import com.sfeir.school.microservices.storeservice.service.dto.CustomerOrderViewDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by youssefguenoun on 21/06/2017.
 */
@Service
public class SalesOrderServiceImpl implements SalesOrderService {

    @Override
    public CustomerOrderViewDto findOne(Long id) {
        return null;
    }

    @Override
    public Page<CustomerOrderViewDto> findAll(Pageable pageable, String client) {
        return null;
    }

    @Override
    public CustomerOrderViewDto save(CustomerOrderInputDTO customerOrderInputDTO) throws ProductNotFoundException {
        return null;
    }
}
