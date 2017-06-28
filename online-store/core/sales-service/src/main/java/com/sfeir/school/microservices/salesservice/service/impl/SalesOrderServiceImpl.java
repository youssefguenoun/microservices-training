package com.sfeir.school.microservices.salesservice.service.impl;

import com.sfeir.school.microservices.salesservice.domain.SalesOrder;
import com.sfeir.school.microservices.salesservice.repository.SalesOrderRepository;
import com.sfeir.school.microservices.salesservice.repository.specifications.SalesOrderSearchFilter;
import com.sfeir.school.microservices.salesservice.repository.specifications.SalesOrderSpecificationBuilder;
import com.sfeir.school.microservices.salesservice.service.SalesOrderService;
import com.sfeir.school.microservices.salesservice.service.dto.OrderInputDTO;
import com.sfeir.school.microservices.salesservice.service.dto.OrderViewDTO;
import com.sfeir.school.microservices.salesservice.service.mapper.SalesOrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * Created by youssefguenoun on 20/06/2017.
 */
@Service
@Transactional
public class SalesOrderServiceImpl implements SalesOrderService {

    private final Logger logger = LoggerFactory.getLogger(SalesOrderServiceImpl.class);

    @Autowired
    private SalesOrderRepository salesOrderRepository;

    @Autowired
    private SalesOrderMapper salesOrderMapper;

    @Override
    public OrderViewDTO save(OrderInputDTO orderInputDTO) {

        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<OrderViewDTO> findAll(Pageable pageable) {
        logger.debug("Request to get all SalesOrder");
        Page<SalesOrder> result = salesOrderRepository.findAll(pageable);
        return result.map(salesOrder -> salesOrderMapper.salesOrderToOrderViewDTO(salesOrder));
    }

    @Override
    @Transactional(readOnly = true)
    public OrderViewDTO findOne(Long id) {
        logger.debug("Request to get SalesOrder : {}", id);
        SalesOrder salesOrder = salesOrderRepository.findOne(id);

        return salesOrderMapper.salesOrderToOrderViewDTO(salesOrder);
    }

    @Override
    public void delete(Long id) {
        logger.debug("Request to delete SalesOrder : {}", id);
        salesOrderRepository.delete(id);
    }

    @Override
    public Page<OrderViewDTO> findAll(String client, Pageable pageable) {
        logger.debug("Request to get all SalesOrder by client : {}", client);
        Page<SalesOrder> result = null;
        if (StringUtils.hasText(client)) {
            result = salesOrderRepository.findByCustomer(client, pageable);
        } else {
            result = salesOrderRepository.findAll(pageable);
        }
        return result.map(salesOrder -> salesOrderMapper.salesOrderToOrderViewDTO(salesOrder));
    }

    @Override
    public Page<OrderViewDTO> findByFilterSpecification(SalesOrderSearchFilter salesOrderSearchFilter, Pageable pageable) {
        SalesOrderSpecificationBuilder salesOrderSpecificationBuilder = new SalesOrderSpecificationBuilder(salesOrderSearchFilter);
        Specification<SalesOrder> specification = salesOrderSpecificationBuilder.buildSalesOrderSpecification();
        Page<SalesOrder> result = null;
        if (null != specification) {
            result = salesOrderRepository.findAll(specification, pageable);
        } else {
            result = salesOrderRepository.findAll(pageable);
        }

        return result.map(salesOrder -> salesOrderMapper.salesOrderToOrderViewDTO(salesOrder));
    }
}
