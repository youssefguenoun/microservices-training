package com.sfeir.school.microservices.storeservice.service.client.stock.api;

import com.sfeir.school.microservices.storeservice.service.client.stock.model.StockInputDTO;
import com.sfeir.school.microservices.storeservice.service.client.stock.model.StockViewDTO;
import com.sfeir.school.microservices.util.web.rest.error.ApiException;
import com.sfeir.school.microservices.util.web.rest.error.ErrorConstants;
import com.sfeir.school.microservices.util.web.rest.error.ErrorVM;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * Created by youssefguenoun on 30/06/2017.
 */
@Component
public class StockResourceApiFallback implements StockResourceApiClient {

    @Override
    public ResponseEntity<StockViewDTO> createStockUsingPOST(StockInputDTO stockInput) {
        ErrorVM error = new ErrorVM(ErrorConstants.ERR_SERVICE_UNAVAILABLE);
        throw new ApiException(error, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @Override
    public ResponseEntity<Void> decreaseStockUsingPOST(String productRef, Integer quantityDelta) {
        ErrorVM error = new ErrorVM(ErrorConstants.ERR_SERVICE_UNAVAILABLE);
        throw new ApiException(error, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @Override
    public ResponseEntity<StockViewDTO> getStockUsingGET(String productRef) {
        StockViewDTO stockViewDTO = new StockViewDTO();
        stockViewDTO.setProductRef(productRef);
        stockViewDTO.setQuantity(null);

        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body(stockViewDTO);
    }

    @Override
    public ResponseEntity<Void> increaseStockUsingPOST(String productRef, Integer quantityDelta) {
        ErrorVM error = new ErrorVM(ErrorConstants.ERR_SERVICE_UNAVAILABLE);
        throw new ApiException(error, HttpStatus.SERVICE_UNAVAILABLE);
    }
}
