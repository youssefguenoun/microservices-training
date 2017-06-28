package io.swagger.api;

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

@Api(value = "SalesResource", description = "the SalesResource API")
public interface SalesResourceApi {

    @ApiOperation(value = "getAllSales", notes = "", response = OrderViewDTO.class, responseContainer = "List", tags={ "sales-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = OrderViewDTO.class) })
    @RequestMapping(value = "/sales",
        produces = "*/*",
        consumes = "application/json",
        method = RequestMethod.GET)
    ResponseEntity<List<OrderViewDTO>> getAllSalesUsingGET( @ApiParam(value = "product") @RequestParam(value = "product", required = false) String product);

}
