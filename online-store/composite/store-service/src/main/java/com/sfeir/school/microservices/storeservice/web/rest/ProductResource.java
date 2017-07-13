package com.sfeir.school.microservices.storeservice.web.rest;

import com.sfeir.school.microservices.storeservice.service.ProductService;
import com.sfeir.school.microservices.storeservice.service.dto.CustomerProductViewDto;
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

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * Created by youssefguenoun on 21/06/2017.
 */
@RestController
@RequestMapping("/products")
public class ProductResource {
    private final Logger log = LoggerFactory.getLogger(ProductResource.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private ServerProperties serverProperties;

    /**
     * GET  /products : get all the products.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of products in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping
    public ResponseEntity<List<CustomerProductViewDto>> getAllProducts(
            Pageable pageable,
            String name,
            @RequestParam(required = false) String category)
            throws URISyntaxException {
        log.debug("REST request to get a page of Products");
        Page<CustomerProductViewDto> page;
        page = productService.findAll(pageable, category, name);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, serverProperties.getContextPath() + "/products");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /products/:ref : get the "ref" product.
     *
     * @param ref the ref of the product to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the productViewDto, or with status 404 (Not Found)
     */
    @GetMapping("/{ref}")
    public ResponseEntity<CustomerProductViewDto> getProduct(
            @PathVariable String ref) {
        log.debug("REST request to get Product with reference  : {}", ref);
        CustomerProductViewDto productViewDto = productService.findByRef(ref);
        return Optional.ofNullable(productViewDto)
                .map(result -> new ResponseEntity<>(
                        result,
                        HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
