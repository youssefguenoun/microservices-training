package com.sfeir.school.microservices.storeservice.web.rest;

import com.sfeir.school.microservices.storeservice.service.ProductNotFoundException;
import com.sfeir.school.microservices.storeservice.service.SalesOrderService;
import com.sfeir.school.microservices.storeservice.service.dto.CustomerOrderInputDTO;
import com.sfeir.school.microservices.storeservice.service.dto.CustomerOrderViewDto;
import com.sfeir.school.microservices.util.web.rest.HeaderUtil;
import com.sfeir.school.microservices.util.web.rest.PaginationUtil;
import com.sfeir.school.microservices.util.web.rest.error.ApiException;
import com.sfeir.school.microservices.util.web.rest.error.ErrorVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * Created by youssefguenoun on 21/06/2017.
 */
@RestController
@RequestMapping("/orders")
public class OrderResource {

    private final Logger log = LoggerFactory.getLogger(OrderResource.class);

    private final String applicationName = "customer-service";

    @Autowired
    private SalesOrderService salesOrderService;

    @Autowired
    private ServerProperties serverProperties;

    /**
     * GET  /sale-orders/:id : get the "id" saleOrder.
     *
     * @param id the id of the saleOrderDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the saleOrderDTO, or with status 404 (Not Found)
     */
    @GetMapping("/{id}")
    public ResponseEntity<CustomerOrderViewDto> getSaleOrder(
            @PathVariable Long id) {
        log.debug("REST request to get SaleOrder : {}", id);
        CustomerOrderViewDto customerOrderViewDto = salesOrderService.findOne(id);
        return Optional.ofNullable(customerOrderViewDto)
                .map(result -> new ResponseEntity<>(
                        result,
                        HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    /**
     * GET  /sale-orders : Get all the past orders of the customer.
     *
     * @param pageable the pagination information
     * @param client   optional filtering on the client attribute
     * @return the ResponseEntity with status 200 (OK) and the list of saleOrders in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping
    public ResponseEntity<List<CustomerOrderViewDto>> getAllSaleOrders(
            @RequestParam Pageable pageable,
            @RequestParam String client)
            throws URISyntaxException {
        log.debug("REST request to get a page of SaleOrders");
        Page<CustomerOrderViewDto> page = salesOrderService.findAll(pageable, client);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, serverProperties.getContextPath() + "/orders");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * POST  /orders : Pass an Order.
     *
     * @param customerOrderInputDTO the customerOrderInputDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new customerOrderViewDto, or with status 400 (Bad Request) if the saleOrder has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping
    public ResponseEntity<CustomerOrderViewDto> createSaleOrder(
            @Valid @RequestBody CustomerOrderInputDTO customerOrderInputDTO) throws URISyntaxException {
        log.debug("REST request to save SaleOrder : {}", customerOrderInputDTO);
        CustomerOrderViewDto result;

        try {

            result = salesOrderService.save(customerOrderInputDTO);

        } catch (ProductNotFoundException e) {

            log.debug("No product found for the reference : " + customerOrderInputDTO.getProductRef());

            ErrorVM noProductFoundError = new ErrorVM("No product found for the reference : " + customerOrderInputDTO.getProductRef());
            noProductFoundError.add(CustomerOrderInputDTO.class.getName(), "category", "No product found for reference " + customerOrderInputDTO.getProductRef());
            throw new ApiException(noProductFoundError, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.created(new URI(serverProperties.getContextPath() + "/orders/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert("Order", result.getId().toString(), applicationName))
                .body(result);

    }
}
