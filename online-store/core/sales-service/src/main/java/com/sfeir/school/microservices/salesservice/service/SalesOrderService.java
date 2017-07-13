package com.sfeir.school.microservices.salesservice.service;

import com.sfeir.school.microservices.salesservice.repository.specifications.SalesOrderSearchFilter;
import com.sfeir.school.microservices.salesservice.service.dto.OrderInputDTO;
import com.sfeir.school.microservices.salesservice.service.dto.OrderViewDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by youssefguenoun on 20/06/2017.
 */
public interface SalesOrderService {

    OrderViewDTO save (OrderInputDTO orderInputDTO);

    Page<OrderViewDTO> findAll(Pageable pageable);

    OrderViewDTO findOne(Long id);

    void delete(Long id);

    Page<OrderViewDTO> findAll(String client, Pageable pageable);

    Page<OrderViewDTO> findByFilterSpecification(SalesOrderSearchFilter salesOrderSearchFilter, Pageable pageable);
}
