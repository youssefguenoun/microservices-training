package com.sfeir.school.microservices.salesservice.web.rest;

import com.sfeir.school.microservices.salesservice.repository.specifications.SalesOrderSearchFilter;
import com.sfeir.school.microservices.salesservice.service.SalesOrderService;
import com.sfeir.school.microservices.salesservice.service.dto.OrderViewDTO;
import com.sfeir.school.microservices.util.web.rest.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * Created by youssefguenoun on 21/06/2017.
 */
@RestController
@RequestMapping("/sales")
public class SalesResource {
    private final Logger log = LoggerFactory.getLogger(SalesResource.class);

    @Autowired
    private SalesOrderService salesOrderService;

    @Autowired
    private ServerProperties serverProperties;

    /**
     * GET  /sales : get all the saleOrders.
     *
     * @param product  <code>String</code> the product reference filter
     * @param fromDate <code>ZonedDateTime</code> the start date filter
     * @param toDate   <code>ZonedDateTime</code> the end date filter
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of saleOrders in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping
    public ResponseEntity<List<OrderViewDTO>> getAllSales(
            @RequestParam(name = "product", required = false) String product,
            @RequestParam(name = "from", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime fromDate,
            @RequestParam(name = "to", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime toDate,
            Pageable pageable)
            throws URISyntaxException {
        log.debug("REST request to get a page of SaleOrders by filter");

        SalesOrderSearchFilter saleOrderFilterDTO = new SalesOrderSearchFilter(product, fromDate, toDate);
        Page<OrderViewDTO> page = salesOrderService.findByFilterSpecification(saleOrderFilterDTO, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, serverProperties.getContextPath() + "/sales");

        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
}
