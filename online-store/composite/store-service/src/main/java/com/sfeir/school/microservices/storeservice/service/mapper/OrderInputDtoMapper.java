package com.sfeir.school.microservices.storeservice.service.mapper;

import com.sfeir.school.microservices.storeservice.service.client.sales.model.OrderInputDTO;
import com.sfeir.school.microservices.storeservice.service.dto.CustomerOrderInputDTO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Created by youssefguenoun on 29/06/2017.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OrderInputDtoMapper {

    OrderInputDTO customerOrderInputDTOToOrderInputDto(CustomerOrderInputDTO customerOrderInputDTO);

    List<OrderInputDTO> customerOrderInputDTOsToOrderInputDtos(List<CustomerOrderInputDTO> customerOrderInputDTOS);
}
