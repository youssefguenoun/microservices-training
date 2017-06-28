package com.sfeir.school.microservices.productservice.service.impl;

import com.sfeir.school.microservices.productservice.domain.Product;
import com.sfeir.school.microservices.productservice.repository.ProductRepository;
import com.sfeir.school.microservices.productservice.repository.specifications.ProductNameCategoryFilter;
import com.sfeir.school.microservices.productservice.repository.specifications.ProductNameCategorySpecificationBuilder;
import com.sfeir.school.microservices.productservice.service.ProductAlreadyExistException;
import com.sfeir.school.microservices.productservice.service.ProductNotFoundException;
import com.sfeir.school.microservices.productservice.service.ProductService;
import com.sfeir.school.microservices.productservice.service.dto.ProductInputDto;
import com.sfeir.school.microservices.productservice.service.dto.ProductUpdateDto;
import com.sfeir.school.microservices.productservice.service.dto.ProductViewDto;
import com.sfeir.school.microservices.productservice.service.mapper.ProductMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by youssefguenoun on 19/06/2017.
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public ProductViewDto save(ProductInputDto productInputDto) throws ProductAlreadyExistException {
        log.info("Request to save Product : {}", productInputDto);
        if(null != productRepository.findByRef(productInputDto.getRef())){
            throw new ProductAlreadyExistException();
        }

        Product product = productMapper.productInputDtoToProduct(productInputDto);
        product = productRepository.save(product);
        return productMapper.productToProductViewDto(product);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductViewDto findOne(Long id) {
        log.info("Request to get Product : {}", id);
        Product product = productRepository.findOne(id);
        return productMapper.productToProductViewDto(product);
    }

    @Override
    public void delete(Long id) throws ProductNotFoundException {
        log.info("Request to delete Product : {}", id);
        if(null == productRepository.findOne(id)){
            throw new ProductNotFoundException();
        }
        productRepository.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductViewDto findByReference(String ref) {
        log.info("Request to find a Product by its ref: {}", ref);
        Product product = productRepository.findByRef(ref);
        return productMapper.productToProductViewDto(product);
    }

    @Override
    public ProductViewDto update(Long id, ProductUpdateDto productUpdateDto) {
        Product existingProduct = productRepository.findOne(id);
        if(null != existingProduct){
            existingProduct.setCategory(productUpdateDto.getCategory());
            existingProduct.setName(productUpdateDto.getName());
            existingProduct.setUnitPrice(productUpdateDto.getUnitPrice());

            return productMapper.productToProductViewDto(existingProduct);
        }

        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductViewDto> findBySpecificationsFilter(ProductNameCategoryFilter productNameCategoryFilter, Pageable pageable) {
        ProductNameCategorySpecificationBuilder productNameCategorySpecificationBuilder = new ProductNameCategorySpecificationBuilder(productNameCategoryFilter);
        Specification<Product> specifications = productNameCategorySpecificationBuilder.build();

        Page<ProductViewDto> result;
        if(null != specifications){
            result = productRepository.findAll(specifications, pageable).map(product -> productMapper.productToProductViewDto(product));
        }else {
            result = productRepository.findAll(pageable).map(product -> productMapper.productToProductViewDto(product));
        }
        return result;
    }
}
