package com.sfeir.school.microservices.salesservice.web.rest;

import com.sfeir.school.microservices.salesservice.service.SalesOrderService;
import com.sfeir.school.microservices.salesservice.service.dto.OrderInputDTO;
import com.sfeir.school.microservices.salesservice.service.dto.OrderViewDTO;
import com.sfeir.school.microservices.util.web.rest.HeaderUtil;
import com.sfeir.school.microservices.util.web.rest.PaginationUtil;
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

    private final String applicationName = "sales-service";

    @Autowired
    private SalesOrderService salesOrderService;

    @Autowired
    private ServerProperties serverProperties;

    /**
     * POST  /orders : Create a new saleOrder.
     *
     * @param order the OrderInputDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new OrderViewDTO, or with status 400 (Bad Request) if the saleOrder has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    //@PreAuthorize("#oauth2.hasScope('api.sgapi-showcase-sales.create_order')")
    @PostMapping
    public ResponseEntity<OrderViewDTO> createOrder(
            @Valid @RequestBody OrderInputDTO order) throws URISyntaxException {
        log.debug("REST request to save SaleOrder : {}", order);

        OrderViewDTO result = salesOrderService.save(order);
        return ResponseEntity.created(new URI(serverProperties.getContextPath() + "/orders/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert("salesOrder", result.getId().toString(), applicationName))
                .body(result);
    }


    /**
     * GET  /orders : get all the saleOrders.
     *
     * @param client   <code>String</code> the customer mail
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of saleOrders in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping
    public ResponseEntity<List<OrderViewDTO>> getAllOrders(
            @RequestParam(required = false) String client, Pageable pageable)
            throws URISyntaxException {
        log.debug("REST request to get a page of SalesOrder");
        Page<OrderViewDTO> page = salesOrderService.findAll(client, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, serverProperties.getContextPath() + "/orders");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /orders/:id : get the "id" saleOrder.
     *
     * @param id the id of the OrderViewDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the OrderViewDTO, or with status 404 (Not Found)
     */
    @GetMapping("/{id}")
    public ResponseEntity<OrderViewDTO> getOrder(
            @PathVariable Long id) {
        log.debug("REST request to get SalesOrder : {}", id);
        OrderViewDTO OrderViewDTO = salesOrderService.findOne(id);
        return Optional.ofNullable(OrderViewDTO)
                .map(result -> new ResponseEntity<>(
                        result,
                        HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
