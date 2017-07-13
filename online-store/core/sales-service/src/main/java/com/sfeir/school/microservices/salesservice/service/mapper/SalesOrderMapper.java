package com.sfeir.school.microservices.salesservice.service.mapper;

import com.sfeir.school.microservices.salesservice.domain.SalesOrder;
import com.sfeir.school.microservices.salesservice.service.dto.OrderInputDTO;
import com.sfeir.school.microservices.salesservice.service.dto.OrderViewDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * Created by youssefguenoun on 20/06/2017.
 */
@Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SalesOrderMapper {

    OrderViewDTO salesOrderToOrderViewDTO(SalesOrder salesOrder);

    List<OrderViewDTO> salesOrdersToOrderViewDTOs(List<SalesOrder> salesOrders);

    SalesOrder orderInputDTOToSalesOrder(OrderInputDTO orderInputDTO);

    List<SalesOrder> orderInputDTOsToSalesOrders(List<OrderInputDTO> orderInputDTOS);

}
