package com.sfeir.school.microservices.stockservice.service.impl;

import com.sfeir.school.microservices.stockservice.domain.Stock;
import com.sfeir.school.microservices.stockservice.repository.StockRepository;
import com.sfeir.school.microservices.stockservice.service.ProductNotFoundException;
import com.sfeir.school.microservices.stockservice.service.StockService;
import com.sfeir.school.microservices.stockservice.service.dto.StockInputDTO;
import com.sfeir.school.microservices.stockservice.service.dto.StockViewDTO;
import com.sfeir.school.microservices.stockservice.service.mapper.StockMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by youssefguenoun on 21/06/2017.
 */
@Service
@Transactional
public class StockServiceImpl implements StockService {

    private final Logger logger = LoggerFactory.getLogger(StockServiceImpl.class);

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockMapper stockMapper;

    @Override
    public StockViewDTO save(StockInputDTO stockInputDTO) {
        logger.debug("Request to save Stock : {}", stockInputDTO);
        Stock stock = stockMapper.stockInputDTOToStock(stockInputDTO);
        stock = stockRepository.save(stock);

        return stockMapper.stockToStockViewDTO(stock);
    }

    @Override
    public StockViewDTO findOneByProductRef(String productRef) {
        logger.debug("Request to get Stock within reference: {}", productRef);
        Stock stock = stockRepository.findOneByProductRef(productRef);

        return stockMapper.stockToStockViewDTO(stock);
    }

    @Override
    public StockViewDTO decreaseQuantity(String productRef, Integer quantityDelta) throws ProductNotFoundException {
        logger.debug("Decreasing the Stock within the reference: {}, by {}", productRef, quantityDelta);
        Stock stock = stockRepository.findOneByProductRef(productRef);

        if (null != stock) {
            stock.decreaseQuantity(quantityDelta);
            return stockMapper.stockToStockViewDTO(stock);
        } else {
            throw new ProductNotFoundException();
        }
    }

    @Override
    public StockViewDTO increaseQuantity(String productRef, Integer quantityDelta) throws ProductNotFoundException {
        logger.debug("Increasing the Stock within the reference: {}, by {}", productRef, quantityDelta);
        Stock stock = stockRepository.findOneByProductRef(productRef);

        if (null != stock) {
            stock.increaseQuantity(quantityDelta);
            return stockMapper.stockToStockViewDTO(stock);
        } else {
            throw new ProductNotFoundException();
        }
    }
}
