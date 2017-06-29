package com.sfeir.school.microservices.storeservice.service.impl;

import com.sfeir.school.microservices.storeservice.service.ProductNotFoundException;
import com.sfeir.school.microservices.storeservice.service.SalesOrderService;
import com.sfeir.school.microservices.storeservice.service.client.product.api.ProductResourceApiClient;
import com.sfeir.school.microservices.storeservice.service.client.product.model.ProductViewDto;
import com.sfeir.school.microservices.storeservice.service.client.sales.api.OrderResourceApi;
import com.sfeir.school.microservices.storeservice.service.client.sales.api.OrderResourceApiClient;
import com.sfeir.school.microservices.storeservice.service.client.sales.api.SalesResourceApiClient;
import com.sfeir.school.microservices.storeservice.service.client.sales.model.OrderInputDTO;
import com.sfeir.school.microservices.storeservice.service.client.sales.model.OrderViewDTO;
import com.sfeir.school.microservices.storeservice.service.dto.CustomerOrderInputDTO;
import com.sfeir.school.microservices.storeservice.service.dto.CustomerOrderViewDto;
import com.sfeir.school.microservices.storeservice.service.mapper.OrderInputDtoMapper;
import com.sfeir.school.microservices.storeservice.service.mapper.OrderViewDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by youssefguenoun on 21/06/2017.
 */
@Service
public class SalesOrderServiceImpl implements SalesOrderService {

    @Autowired
    private SalesResourceApiClient salesResourceApiClient;

    @Autowired
    private OrderResourceApiClient orderResourceApiClient;

    @Autowired
    private ProductResourceApiClient productResourceApiClient;

    @Autowired
    private OrderViewDtoMapper orderViewDtoMapper;

    @Autowired
    private OrderInputDtoMapper orderInputDtoMapper;


    @Override
    public CustomerOrderViewDto findOne(Long id) {
        ResponseEntity<OrderViewDTO> responseEntity = orderResourceApiClient.getOrderUsingGET(id);
        return orderViewDtoMapper.orderViewDtoToCustomerOrderViewDto(responseEntity.getBody());
    }

    @Override
    public Page<CustomerOrderViewDto> findAll(Pageable pageable, String client) {
        ResponseEntity<List<OrderViewDTO>> responseEntity = salesResourceApiClient.getAllSalesUsingGET(pageable.getPageNumber(), pageable.getPageSize(), buildSortOrderAsList(pageable), client);

        return new PageImpl<CustomerOrderViewDto>(orderViewDtoMapper.orderViewDtosToCustomerOrderViewDtos(responseEntity.getBody()));
    }

    private List<String> buildSortOrderAsList(Pageable pageable) {
        if (pageable.getSort() == null) {
            return Collections.EMPTY_LIST;
        }
        List<String> result = new ArrayList<>();
        pageable.getSort().spliterator().forEachRemaining(order -> result.add(order.getProperty() + ", " + order.getDirection().toString().toLowerCase()));
        return result;
    }

    @Override
    public CustomerOrderViewDto save(CustomerOrderInputDTO customerOrderInputDTO) throws ProductNotFoundException {

        // At first we get the price of the product corresponding to ProductRef
        ResponseEntity<?> responseEntity = productResourceApiClient.getProductByRefUsingGET(customerOrderInputDTO.getProductRef());
        if (responseEntity.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
            throw new ProductNotFoundException();
        }

        ProductViewDto availableProduct = (ProductViewDto) responseEntity.getBody();
        customerOrderInputDTO.setUnitPrice(availableProduct.getUnitPrice().doubleValue());

        OrderInputDTO orderInputDTO = orderInputDtoMapper.customerOrderInputDTOToOrderInputDto(customerOrderInputDTO);

        ResponseEntity<OrderViewDTO> orderViewDTOResponseEntity = orderResourceApiClient.createOrderUsingPOST(orderInputDTO);

        return orderViewDtoMapper.orderViewDtoToCustomerOrderViewDto(orderViewDTOResponseEntity.getBody());
    }
}
