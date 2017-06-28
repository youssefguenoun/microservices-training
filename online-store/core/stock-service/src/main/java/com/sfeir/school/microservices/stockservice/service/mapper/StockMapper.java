package com.sfeir.school.microservices.stockservice.service.mapper;

import com.sfeir.school.microservices.stockservice.domain.Stock;
import com.sfeir.school.microservices.stockservice.service.dto.StockInputDTO;
import com.sfeir.school.microservices.stockservice.service.dto.StockViewDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * Created by youssefguenoun on 21/06/2017.
 */
@Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StockMapper {

    StockInputDTO stockToStockInputDTO(Stock stock);

    List<StockInputDTO> stocksToStockInputDTOs(List<Stock> stocks);

    Stock stockInputDTOToStock(StockInputDTO stockInputDTO);

    List<Stock> stockInputDTOsToStocks(List<StockInputDTO> stockInputDTOS);

    StockViewDTO stockToStockViewDTO(Stock stock);

    List<StockViewDTO> stocksToStockViewDTOs(List<Stock> stocks);

    Stock stockViewDTOToStock(StockViewDTO stockViewDTO);

    List<Stock> stockViewDTOsToStocks(List<StockViewDTO> stockViewDTOS);

}
