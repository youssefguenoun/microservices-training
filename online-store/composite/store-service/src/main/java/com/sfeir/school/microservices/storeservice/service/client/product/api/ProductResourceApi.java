package com.sfeir.school.microservices.storeservice.service.client.product.api;

import com.sfeir.school.microservices.storeservice.service.client.product.model.ProductInputDto;
import com.sfeir.school.microservices.storeservice.service.client.product.model.ProductUpdateDto;
import com.sfeir.school.microservices.storeservice.service.client.product.model.ProductViewDto;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-06-24T00:13:12.482+02:00")

@Api(value = "ProductResource", description = "the ProductResource API")
public interface ProductResourceApi {

    @ApiOperation(value = "createProduct", notes = "", response = ProductViewDto.class, tags={ "product-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = ProductViewDto.class) })
    @RequestMapping(value = "/products",
        produces = "*/*",
        consumes = "application/json",
        method = RequestMethod.POST)
    ResponseEntity<ProductViewDto> createProductUsingPOST(@ApiParam(value = "productInputDto", required = true) @RequestBody ProductInputDto productInputDto);


    @ApiOperation(value = "deleteProduct", notes = "", response = Void.class, tags={ "product-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = Void.class) })
    @RequestMapping(value = "/products/{id}",
        produces = "*/*",
        consumes = "application/json",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteProductUsingDELETE(@ApiParam(value = "id", required = true) @PathVariable("id") Long id);


    @ApiOperation(value = "getAllProducts", notes = "", response = ProductViewDto.class, responseContainer = "List", tags={ "product-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = ProductViewDto.class) })
    @RequestMapping(value = "/products",
        produces = "*/*",
        consumes = "application/json",
        method = RequestMethod.GET)
    ResponseEntity<List<ProductViewDto>> getAllProductsUsingGET(
            @ApiParam(value = "Page index (0-based)") @RequestParam(value = "page", required = false) Integer page,
            @ApiParam(value = "Item per page") @RequestParam(value = "size", required = false) Integer size,
            @ApiParam(value = "category") @RequestParam(value = "category", required = false) String category,
            @ApiParam(value = "name") @RequestParam(value = "name", required = false) String name,
            @ApiParam(value = "Apply sort") @RequestParam(value = "sort", required = false) List<String> sort);


    @ApiOperation(value = "getProductByRef", notes = "", response = ProductViewDto.class, tags={ "product-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = ProductViewDto.class) })
    @RequestMapping(value = "/products/ref/{productRef}",
        produces = "*/*",
        consumes = "application/json",
        method = RequestMethod.GET)
    ResponseEntity<ProductViewDto> getProductByRefUsingGET(@ApiParam(value = "productRef", required = true) @PathVariable("productRef") String productRef);


    @ApiOperation(value = "getProduct", notes = "", response = ProductViewDto.class, tags={ "product-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = ProductViewDto.class) })
    @RequestMapping(value = "/products/{id}",
        produces = "*/*",
        consumes = "application/json",
        method = RequestMethod.GET)
    ResponseEntity<ProductViewDto> getProductUsingGET(@ApiParam(value = "id", required = true) @PathVariable("id") Long id);


    @ApiOperation(value = "updateProduct", notes = "", response = ProductViewDto.class, tags={ "product-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = ProductViewDto.class) })
    @RequestMapping(value = "/products/{id}",
        produces = "*/*",
        consumes = "application/json",
        method = RequestMethod.PATCH)
    ResponseEntity<ProductViewDto> updateProductUsingPATCH(@ApiParam(value = "id", required = true) @PathVariable("id") Long id,
                                                           @ApiParam(value = "productUpdateDto", required = true) @RequestBody ProductUpdateDto productUpdateDto);

}
