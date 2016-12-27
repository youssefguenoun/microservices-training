package com.sfeir.school.microservices.service.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductDto {
	
    private int productId;
    private String name;
    private int weight;
    

}
