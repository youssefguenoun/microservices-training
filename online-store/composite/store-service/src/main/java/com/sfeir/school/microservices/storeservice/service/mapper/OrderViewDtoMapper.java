package com.sfeir.school.microservices.storeservice.service.mapper;

import com.sfeir.school.microservices.storeservice.service.client.sales.model.OrderViewDTO;
import com.sfeir.school.microservices.storeservice.service.dto.CustomerOrderViewDto;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Created by youssefguenoun on 29/06/2017.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OrderViewDtoMapper {

    CustomerOrderViewDto orderViewDtoToCustomerOrderViewDto(OrderViewDTO orderViewDTO);

    List<CustomerOrderViewDto> orderViewDtosToCustomerOrderViewDtos(List<OrderViewDTO> orderViewDTOS);
}
