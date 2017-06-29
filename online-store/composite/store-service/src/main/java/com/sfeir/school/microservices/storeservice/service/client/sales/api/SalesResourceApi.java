package com.sfeir.school.microservices.storeservice.service.client.sales.api;

import com.sfeir.school.microservices.storeservice.service.client.sales.model.OrderViewDTO;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-06-24T00:11:05.026+02:00")

@Api(value = "SalesResource", description = "the SalesResource API")
public interface SalesResourceApi {

    @ApiOperation(value = "getAllSales", notes = "", response = OrderViewDTO.class, responseContainer = "List", tags={ "sales-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = OrderViewDTO.class) })
    @RequestMapping(value = "/sales",
        produces = "application/json",
        consumes = "application/json",
        method = RequestMethod.GET)
    ResponseEntity<List<OrderViewDTO>> getAllSalesUsingGET(
            @ApiParam(value = "Page index (0-based)") @RequestParam(value = "page", required = false) Integer page,
            @ApiParam(value = "Item per page") @RequestParam(value = "size", required = false) Integer size,
            @ApiParam(value = "Apply sort") @RequestParam(value = "sort", required = false) List<String> sort,
            @ApiParam(value = "product") @RequestParam(value = "product", required = false) String product);

}
