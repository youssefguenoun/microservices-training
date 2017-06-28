package com.sfeir.school.microservices.productservice.service.mapper;

import com.sfeir.school.microservices.productservice.domain.Product;
import com.sfeir.school.microservices.productservice.service.dto.ProductInputDto;
import com.sfeir.school.microservices.productservice.service.dto.ProductUpdateDto;
import com.sfeir.school.microservices.productservice.service.dto.ProductViewDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * Created by youssefguenoun on 19/06/2017.
 */
@Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

    ProductViewDto productToProductViewDto(Product product);

    List<ProductViewDto> productsToProductViewDtos(List<Product> products);

    Product productViewDtoToProduct(ProductViewDto productViewDto);

    List<Product> productViewDtosToProduct(List<ProductViewDto> productViewDtos);

    ProductInputDto productToProductInputDto(Product product);

    List<ProductInputDto> productsToProductInputDtos(List<Product> products);

    Product productInputDtoToProduct(ProductInputDto productInputDto);

    List<Product> productInputDtosToProduct(List<ProductInputDto> productInputDtos);

    ProductUpdateDto productToProductUpdateDto(Product product);

    List<ProductUpdateDto> productsToProductUpdateDtos(List<Product> products);

    Product productUpdateDtoToProduct(ProductUpdateDto productUpdateDto);

    List<Product> productUpdateDtosToProduct(List<ProductUpdateDto> productUpdateDtos);
}
