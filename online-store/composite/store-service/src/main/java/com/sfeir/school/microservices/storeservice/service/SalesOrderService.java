package com.sfeir.school.microservices.storeservice.service;

import com.sfeir.school.microservices.storeservice.service.dto.CustomerOrderInputDTO;
import com.sfeir.school.microservices.storeservice.service.dto.CustomerOrderViewDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by youssefguenoun on 21/06/2017.
 */
public interface SalesOrderService {

    CustomerOrderViewDto findOne(Long id);

    Page<CustomerOrderViewDto> findAll(Pageable pageable, String client);

    CustomerOrderViewDto save(CustomerOrderInputDTO customerOrderInputDTO) throws ProductNotFoundException;
}
