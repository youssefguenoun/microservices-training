package com.sfeir.school.microservices.storeservice.service.client.sales.api;

import com.sfeir.school.microservices.storeservice.service.client.sales.model.OrderInputDTO;
import com.sfeir.school.microservices.storeservice.service.client.sales.model.OrderViewDTO;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-06-24T00:11:05.026+02:00")

@Api(value = "OrderResource", description = "the OrderResource API")
public interface OrderResourceApi {

    @ApiOperation(value = "createOrder", notes = "", response = OrderViewDTO.class, tags={ "order-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = OrderViewDTO.class) })
    @RequestMapping(value = "/orders",
        produces = "application/json",
        consumes = "application/json",
        method = RequestMethod.POST)
    ResponseEntity<OrderViewDTO> createOrderUsingPOST(@ApiParam(value = "order", required = true) @RequestBody OrderInputDTO order);


    @ApiOperation(value = "getAllOrders", notes = "", response = OrderViewDTO.class, responseContainer = "List", tags={ "order-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = OrderViewDTO.class) })
    @RequestMapping(value = "/orders",
        produces = "application/json",
        consumes = "application/json",
        method = RequestMethod.GET)
    ResponseEntity<List<OrderViewDTO>> getAllOrdersUsingGET(@ApiParam(value = "client") @RequestParam(value = "client", required = false) String client,
                                                            @ApiParam(value = "Page index (0-based)") @RequestParam(value = "page", required = false) Integer page,
                                                            @ApiParam(value = "Item per page") @RequestParam(value = "size", required = false) Integer size,
                                                            @ApiParam(value = "Apply sort") @RequestParam(value = "sort", required = false) List<String> sort);


    @ApiOperation(value = "getOrder", notes = "", response = OrderViewDTO.class, tags={ "order-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = OrderViewDTO.class) })
    @RequestMapping(value = "/orders/{id}",
        produces = "application/json",
        consumes = "application/json",
        method = RequestMethod.GET)
    ResponseEntity<OrderViewDTO> getOrderUsingGET(@ApiParam(value = "id", required = true) @PathVariable("id") Long id);

}
