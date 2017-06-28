package io.swagger.api;

import io.swagger.model.OrderInputDTO;
import io.swagger.model.OrderViewDTO;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-06-24T00:11:05.026+02:00")

@Api(value = "OrderResource", description = "the OrderResource API")
public interface OrderResourceApi {

    @ApiOperation(value = "createOrder", notes = "", response = OrderViewDTO.class, tags={ "order-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = OrderViewDTO.class) })
    @RequestMapping(value = "/orders",
        produces = "*/*",
        consumes = "application/json",
        method = RequestMethod.POST)
    ResponseEntity<OrderViewDTO> createOrderUsingPOST(@ApiParam(value = "order" ,required=true ) @RequestBody OrderInputDTO order);


    @ApiOperation(value = "getAllOrders", notes = "", response = OrderViewDTO.class, responseContainer = "List", tags={ "order-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = OrderViewDTO.class) })
    @RequestMapping(value = "/orders",
        produces = "*/*",
        consumes = "application/json",
        method = RequestMethod.GET)
    ResponseEntity<List<OrderViewDTO>> getAllOrdersUsingGET( @ApiParam(value = "client") @RequestParam(value = "client", required = false) String client);


    @ApiOperation(value = "getOrder", notes = "", response = OrderViewDTO.class, tags={ "order-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = OrderViewDTO.class) })
    @RequestMapping(value = "/orders/{id}",
        produces = "*/*",
        consumes = "application/json",
        method = RequestMethod.GET)
    ResponseEntity<OrderViewDTO> getOrderUsingGET(@ApiParam(value = "id",required=true ) @PathVariable("id") Long id);

}
