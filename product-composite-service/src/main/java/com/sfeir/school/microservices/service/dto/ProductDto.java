package com.sfeir.school.microservices.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDto {
	
    private int productId;
    private String name;
    private int weight;
    

}
