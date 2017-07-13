package com.sfeir.school.microservices.storeservice.service.impl;

import com.sfeir.school.microservices.storeservice.service.ProductService;
import com.sfeir.school.microservices.storeservice.service.client.product.api.ProductResourceApiClient;
import com.sfeir.school.microservices.storeservice.service.client.product.model.ProductViewDto;
import com.sfeir.school.microservices.storeservice.service.client.stock.api.StockResourceApiClient;
import com.sfeir.school.microservices.storeservice.service.client.stock.model.StockViewDTO;
import com.sfeir.school.microservices.storeservice.service.dto.CustomerProductViewDto;
import com.sfeir.school.microservices.storeservice.service.mapper.ProductViewDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by youssefguenoun on 21/06/2017.
 */
@Service
public class ProductServiceImpl implements ProductService {
    
    @Autowired
    private ProductResourceApiClient productResourceApiClient;
    
    @Autowired
    private StockResourceApiClient stockResourceApiClient;
    
    @Autowired
    private ProductViewDtoMapper productViewDtoMapper;
    
    @Override
    public Page<CustomerProductViewDto> findAll(Pageable pageable, String category, String name) {

        ResponseEntity<List<ProductViewDto>> responseEntity = productResourceApiClient
                .getAllProductsUsingGET(pageable.getPageNumber(),pageable.getPageSize(),category, name, buildSortOrderAsList(pageable));

        List<CustomerProductViewDto> customerProductViewDtos = productViewDtoMapper.productViewDtosToCustomerProductViewDtos(responseEntity.getBody());
        customerProductViewDtos.forEach(customerProductViewDto -> customerProductViewDto.setQuantity(readQuantity(customerProductViewDto)));

        String xTotalCountAsString = responseEntity.getHeaders().getFirst("X-Total-Count");
        int xTotalCount = pageable.getPageSize();
        if(StringUtils.hasText(xTotalCountAsString)){
            xTotalCount = Integer.parseInt(xTotalCountAsString);
        }

        return new PageImpl<CustomerProductViewDto>(customerProductViewDtos,pageable,xTotalCount);
    }

    private Integer readQuantity(CustomerProductViewDto customerProductViewDto) {
        ResponseEntity<StockViewDTO> stockViewDTOResponseEntity = stockResourceApiClient.getStockUsingGET(customerProductViewDto.getRef());
        if(stockViewDTOResponseEntity.getStatusCode().is2xxSuccessful()){
            return stockViewDTOResponseEntity.getBody().getQuantity();
        }
        return 0;
    }

    private List<String> buildSortOrderAsList(Pageable pageable) {
        if(pageable.getSort() == null){
            return Collections.emptyList();
        }

        List<String> result = new ArrayList<>();
        pageable.getSort().spliterator().forEachRemaining(order -> result.add(order.getProperty() + ", "+order.getDirection().toString().toLowerCase()));
        return  result;
    }

    @Override
    public CustomerProductViewDto findByRef(String ref) {
        ResponseEntity<ProductViewDto> productViewDtoResponseEntity = productResourceApiClient.getProductByRefUsingGET(ref);
        CustomerProductViewDto customerProductViewDto = productViewDtoMapper.productViewDtoToCustomerProductViewDto(productViewDtoResponseEntity.getBody());
        customerProductViewDto.setQuantity((stockResourceApiClient.getStockUsingGET(ref).getBody().getQuantity()));

        return customerProductViewDto;
    }
}
