package com.sfeir.school.microservices.stockservice.web.rest;

import com.sfeir.school.microservices.stockservice.service.ProductNotFoundException;
import com.sfeir.school.microservices.stockservice.service.StockService;
import com.sfeir.school.microservices.stockservice.service.dto.StockInputDTO;
import com.sfeir.school.microservices.stockservice.service.dto.StockViewDTO;
import com.sfeir.school.microservices.util.web.rest.HeaderUtil;
import com.sfeir.school.microservices.util.web.rest.error.ApiException;
import com.sfeir.school.microservices.util.web.rest.error.ErrorVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

/**
 * Created by youssefguenoun on 21/06/2017.
 */
@RestController
@RequestMapping("/stocks")
public class StockResource {

    private final Logger log = LoggerFactory.getLogger(StockResource.class);

    private final String applicationName = "stock-service";

    @Autowired
    private StockService stockService;

    @Autowired
    private ServerProperties serverProperties;

    /**
     * POST  /stocks : Create a new stock.
     *
     * @param stockInput the stockDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new StockViewDTO
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    //@PreAuthorize("#oauth2.hasScope('api.sgapi-showcase-stock.modify')")
    @PostMapping
    public ResponseEntity<StockViewDTO> createStock(
            @Valid @RequestBody StockInputDTO stockInput) throws URISyntaxException {
        log.debug("REST request to save Stock : {}", stockInput);

        if (stockInput.getQuantity() < 0) {
            ErrorVM error = new ErrorVM("Product not found in the stock !");
            error.add(StockInputDTO.class.getName(), "quantity", "the quantity of stock should be positive !");

            throw new ApiException(error, HttpStatus.BAD_REQUEST);

        } else {

            StockViewDTO result = stockService.save(stockInput);
            return ResponseEntity.created(new URI(serverProperties.getContextPath() + "/stocks/" + result.getProductRef()))
                    .headers(HeaderUtil.createEntityCreationAlert("stock", result.getProductRef(), applicationName))
                    .body(result);
        }

    }


    /**
     * GET  /stocks/:productRef : get the "productRef" stock.
     *
     * @param productRef the productRef of the stockViewDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the stockViewDTO, or with status 404 (Not Found)
     */
    @GetMapping("/{productRef}")
    public ResponseEntity<StockViewDTO> getStock(
            @PathVariable String productRef) {
        log.debug("REST request to get Stock : {}", productRef);
        StockViewDTO stockViewDTO = stockService.findOneByProductRef(productRef);
        return Optional.ofNullable(stockViewDTO)
                .map(result -> new ResponseEntity<>(
                        result,
                        HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * POST  /stocks/:productRef : decrease the "productRef" stock.
     *
     * @param productRef    <code>String</code> the reference of the stockDTO to decrease
     * @param quantityDelta <code>Integer</code> the delta quantity to subtract from stock quantity within the giving reference in argument
     * @return the ResponseEntity with status 200 (OK)
     */
    //@PreAuthorize("#oauth2.hasScope('api.sgapi-showcase-stock.modify')")
    @PostMapping("/{productRef}/do-decrease")
    public ResponseEntity<Void> decreaseStock(
            @PathVariable String productRef,
            @RequestParam Integer quantityDelta) {
        log.debug("REST request to decrease the Stock : {}", productRef);

        try {

            stockService.decreaseQuantity(productRef, quantityDelta);

        } catch (ProductNotFoundException e) {

            ErrorVM error = new ErrorVM("Product not found in the stock !");
            error.add(StockViewDTO.class.getName(), "productRef", "the stock within the reference :" + productRef + " not exist in database !");

            throw new ApiException(error, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert("stock", productRef, applicationName)).build();
    }


    /**
     * POST  /stocks/:productRef : increase the "productRef" stock if it exists or create a new stock.
     *
     * @param productRef    <code>String</code> the reference of the stockDTO to increase
     * @param quantityDelta <code>Integer</code> the delta quantity to add to the stock quantity within the giving reference in argument
     * @return the ResponseEntity with status 200 (OK)
     */
    //@PreAuthorize("#oauth2.hasScope('api.sgapi-showcase-stock.modify')")
    @PostMapping("/{productRef}/do-increase")
    public ResponseEntity<Void> increaseStock(
            @PathVariable String productRef,
            @RequestParam Integer quantityDelta) {
        log.debug("REST request to increase the Stock : {}", productRef);

        try {

            stockService.increaseQuantity(productRef, quantityDelta);

        } catch (ProductNotFoundException e) {

            ErrorVM notEnoughStockQuantityError = new ErrorVM("Product not found in the stock !");
            notEnoughStockQuantityError.add(StockViewDTO.class.getName(), "productRef", "the stock within the reference :" + productRef + " not exist in database !");

            throw new ApiException(notEnoughStockQuantityError, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert("stock", productRef, applicationName)).build();
    }
}
