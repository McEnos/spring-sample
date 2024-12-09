package org.example.springsample.service;

import lombok.RequiredArgsConstructor;
import org.example.springsample.dto.ProductDto;
import org.example.springsample.entity.Product;
import org.example.springsample.mapper.ProductMapper;
import org.example.springsample.repository.ProductRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = productMapper.toEntity(productDto);
        Product saved = productRepository.save(product);
        return productMapper.toDto(saved);
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto, Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("No product with " + id));
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        productRepository.save(product);
        return productMapper.toDto(product);
    }

    @Override
    public boolean deleteProduct(Long code) {
        productRepository.deleteById(code);
        return true;
    }

    @Override
    public ProductDto getProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("No product with " + id));
        return productMapper.toDto(product);
    }

    @Override
    public List<ProductDto> getProducts(Pageable pageable) {
        List<ProductDto> result = new ArrayList<>(pageable.getPageSize());
        productRepository.findAll(pageable).forEach(product -> result.add(productMapper.toDto(product)));
        return result;
    }

    @Override
    public ProductDto getProductByCode(Long code) {
        Product product = productRepository.findByCode(code).orElseThrow(() -> new RuntimeException("No product with " + code));
        return productMapper.toDto(product);
    }
}
