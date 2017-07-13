package com.sfeir.school.microservices.storeservice.service.mapper;

import com.sfeir.school.microservices.storeservice.service.client.product.model.ProductViewDto;
import com.sfeir.school.microservices.storeservice.service.dto.CustomerProductViewDto;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Created by youssefguenoun on 29/06/2017.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProductViewDtoMapper {

    CustomerProductViewDto productViewDtoToCustomerProductViewDto(ProductViewDto productViewDto);

    List<CustomerProductViewDto> productViewDtosToCustomerProductViewDtos(List<ProductViewDto> productViewDtos);
}
