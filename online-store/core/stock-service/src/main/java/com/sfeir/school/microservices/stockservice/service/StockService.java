package com.sfeir.school.microservices.stockservice.service;

import com.sfeir.school.microservices.stockservice.service.dto.StockInputDTO;
import com.sfeir.school.microservices.stockservice.service.dto.StockViewDTO;

/**
 * Created by youssefguenoun on 21/06/2017.
 */
public interface StockService {

    StockViewDTO save(StockInputDTO stockInputDTO);

    StockViewDTO findOneByProductRef(String productRef);

    StockViewDTO decreaseQuantity(String productRef, Integer quantityDelta) throws ProductNotFoundException;

    StockViewDTO increaseQuantity(String productRef, Integer quantityDelta) throws ProductNotFoundException;
}
