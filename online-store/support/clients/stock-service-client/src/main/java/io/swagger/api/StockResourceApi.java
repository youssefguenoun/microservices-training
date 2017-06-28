package io.swagger.api;

import io.swagger.model.StockInputDTO;
import io.swagger.model.StockViewDTO;

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
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-06-23T20:31:03.250+02:00")

@Api(value = "StockResource", description = "the StockResource API")
public interface StockResourceApi {

    @ApiOperation(value = "createStock", notes = "", response = StockViewDTO.class, tags={ "stock-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = StockViewDTO.class) })
    @RequestMapping(value = "/stocks",
        produces = "*/*",
        consumes = "application/json",
        method = RequestMethod.POST)
    ResponseEntity<StockViewDTO> createStockUsingPOST(@ApiParam(value = "stockInput" ,required=true ) @RequestBody StockInputDTO stockInput);


    @ApiOperation(value = "decreaseStock", notes = "", response = Void.class, tags={ "stock-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = Void.class) })
    @RequestMapping(value = "/stocks/{productRef}/do-decrease",
        produces = "*/*",
        consumes = "application/json",
        method = RequestMethod.POST)
    ResponseEntity<Void> decreaseStockUsingPOST(@ApiParam(value = "productRef",required=true ) @PathVariable("productRef") String productRef,
         @NotNull @ApiParam(value = "quantityDelta", required = true) @RequestParam(value = "quantityDelta", required = true) Integer quantityDelta);


    @ApiOperation(value = "getStock", notes = "", response = StockViewDTO.class, tags={ "stock-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = StockViewDTO.class) })
    @RequestMapping(value = "/stocks/{productRef}",
        produces = "*/*",
        consumes = "application/json",
        method = RequestMethod.GET)
    ResponseEntity<StockViewDTO> getStockUsingGET(@ApiParam(value = "productRef",required=true ) @PathVariable("productRef") String productRef);


    @ApiOperation(value = "increaseStock", notes = "", response = Void.class, tags={ "stock-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = Void.class) })
    @RequestMapping(value = "/stocks/{productRef}/do-increase",
        produces = "*/*",
        consumes = "application/json",
        method = RequestMethod.POST)
    ResponseEntity<Void> increaseStockUsingPOST(@ApiParam(value = "productRef",required=true ) @PathVariable("productRef") String productRef,
         @NotNull @ApiParam(value = "quantityDelta", required = true) @RequestParam(value = "quantityDelta", required = true) Integer quantityDelta);

}
