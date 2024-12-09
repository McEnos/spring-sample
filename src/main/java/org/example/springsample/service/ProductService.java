package org.example.springsample.service;

import org.example.springsample.dto.ProductDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto);

    ProductDto updateProduct(ProductDto productDto, Long id);

    boolean deleteProduct(Long id);

    ProductDto getProduct(Long id);

    List<ProductDto> getProducts(Pageable pageable);

    ProductDto getProductByCode(Long code);
}
