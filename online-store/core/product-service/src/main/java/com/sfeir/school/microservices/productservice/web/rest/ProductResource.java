package com.sfeir.school.microservices.productservice.web.rest;

import com.sfeir.school.microservices.productservice.repository.specifications.ProductNameCategoryFilter;
import com.sfeir.school.microservices.productservice.service.ProductAlreadyExistException;
import com.sfeir.school.microservices.productservice.service.ProductNotFoundException;
import com.sfeir.school.microservices.productservice.service.ProductService;
import com.sfeir.school.microservices.productservice.service.dto.ProductInputDto;
import com.sfeir.school.microservices.productservice.service.dto.ProductUpdateDto;
import com.sfeir.school.microservices.productservice.service.dto.ProductViewDto;
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

@RestController
@RequestMapping("/products")
public class ProductResource {

    private final Logger log = LoggerFactory.getLogger(ProductResource.class);

    private final String applicationName = "product-service";

    @Autowired
    private ProductService productService;

    @Autowired
    private ServerProperties serverProperties;

    /**
     * POST  /products : Create a new product.
     *
     * @param productInputDto the <code>productInputDto</code> to create
     * @return the ResponseEntity with status 201 (Created) and with body the new productDto, or with status 400 (Bad Request) if the product has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    //@PreAuthorize("#oauth2.hasScope('api.sgapi-showcase-product.modify')")
    @PostMapping
    public ResponseEntity<ProductViewDto> createProduct(
            @Valid @RequestBody ProductInputDto productInputDto) throws URISyntaxException {
        log.debug("REST request to save Product : {}", productInputDto);
        ProductViewDto result;
        try {
            result = productService.save(productInputDto);
        } catch (ProductAlreadyExistException e) {
            ErrorVM unitPriceNegativeError = new ErrorVM("a product with ref " + productInputDto.getRef() + " already exist");
            unitPriceNegativeError.add(ProductViewDto.class.getName(), "ref", "a product with same reference already exist");
            throw new ApiException(unitPriceNegativeError, HttpStatus.CONFLICT);
        }
        return ResponseEntity.created(new URI(serverProperties.getContextPath() + "/products/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert("product", result.getId().toString(), applicationName))
                .body(result);
    }

    /**
     * PATCH  /products : Updates an existing product.
     *
     * @param productUpdateDto the <code>ProductUpdateDto</code> to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated productUpdateDto,
     * or with status 404 (no content) if id is missing
     * or with status 400 (Bad Request) if the productUpdateDto is not valid,
     * or with status 500 (Internal Server Error) if the productUpdateDto could not be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    //@PreAuthorize("#oauth2.hasScope('api.sgapi-showcase-product.modify')")
    @PatchMapping("/{id}")
    public ResponseEntity<ProductViewDto> updateProduct(
            @PathVariable Long id,
            @RequestBody ProductUpdateDto productUpdateDto) throws URISyntaxException {
        log.debug("REST request to update Product : {}", productUpdateDto);
        ProductViewDto productViewDto;
        productViewDto = productService.update(id, productUpdateDto);
        return Optional.ofNullable(productViewDto)
                .map(result -> new ResponseEntity<>(
                        result,
                        HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * GET  /products : get all the products.
     *
     * @param pageable the pagination information
     * @param category optional category
     * @return the ResponseEntity with status 200 (OK) and the list of products in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping
    public ResponseEntity<List<ProductViewDto>> getAllProducts(Pageable pageable,
                                                               @RequestParam(required = false) String category,
                                                               @RequestParam(required = false) String name)
            throws URISyntaxException {
        log.debug("REST request to get a page of Products");
        Page<ProductViewDto> page;
        final ProductNameCategoryFilter nameCategoryFilter = new ProductNameCategoryFilter(name, category);
        page = productService.findBySpecificationsFilter(nameCategoryFilter, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, serverProperties.getContextPath() + "/products");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /products/:id : get the "id" product.
     *
     * @param id the id of the productDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the productDTO, or with status 404 (Not Found)
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductViewDto> getProduct(@PathVariable Long id) {
        log.debug("REST request to get Product : {}", id);
        ProductViewDto productViewDto = productService.findOne(id);
        return Optional.ofNullable(productViewDto)
                .map(result -> new ResponseEntity<>(
                        result,
                        HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * GET /products/ref/:ref
     *
     * @param productRef the product reference value
     * @return the <code>ResponseEntity</code> with status 200 (OK) and with body the productDTO, or with status 404 (Not Found)
     */
    @GetMapping("/ref/{productRef}")
    public ResponseEntity<ProductViewDto> getProductByRef(@PathVariable String productRef) {
        log.debug("REST request to get Product byt its ref : {}", productRef);
        ProductViewDto productViewDto = productService.findByReference(productRef);
        return Optional.ofNullable(productViewDto)
                .map(result -> new ResponseEntity<>(
                        result,
                        HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /products/:id : delete the "id" product.
     *
     * @param id the id of the productDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    //@PreAuthorize("#oauth2.hasScope('api.sgapi-showcase-product.modify')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        log.debug("REST request to delete Product : {}", id);
        try {
            productService.delete(id);
        } catch (ProductNotFoundException e) {
            ErrorVM noProductError = new ErrorVM("No product found");
            noProductError.add(ProductViewDto.class.getName(), "", "No product found");
            throw new ApiException(noProductError, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert("product", id.toString(), applicationName)).build();
    }

}
