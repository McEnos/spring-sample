package org.example.springsample.repository;

import org.assertj.core.api.Assertions;
import org.example.springsample.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    /*Product savedProduct

    @BeforeEach
    void setUp() {
        Product product = Product.builder()
                .code(1L)
                .description("Test Product")
                .name("Test Product")
                .build();
        //Act
        Product savedProduct = productRepository.save(product);
    }*/

    @Test
    public void ProductRepository_save_ReturnProduct() {
        //Arrange
        Product product = Product.builder()
                .code(1L)
                .description("Test Product")
                .name("Test Product")
                .build();
        //Act
        Product savedProduct = productRepository.save(product);
        //Asser
        Assertions.assertThat(savedProduct).isNotNull();
        Assertions.assertThat(savedProduct.getCode()).isEqualTo(product.getCode());
        Assertions.assertThat(savedProduct.getId()).isGreaterThan(0);
    }

    @Test
    public void ProductRepository_FindById_ReturnProduct() {
        Product product = Product.builder()
                .code(1L)
                .description("Test Product")
                .name("Test Product")
                .build();
        productRepository.save(product);

        Optional<Product> optionalProduct = productRepository.findById(product.getId());
        Assertions.assertThat(optionalProduct).isPresent();
        Assertions.assertThat(optionalProduct.get()).isNotNull();
    }

    @Test
    public void ProductRepository_Update_ReturnProduct() {
        //Arrange
        Product product = Product.builder()
                .code(1L)
                .description("Test Product")
                .name("Test Product")
                .build();
        productRepository.save(product);
        Product savedProduct = productRepository.findById(product.getId()).get();

        savedProduct.setDescription("Updated Description");
        savedProduct.setName("Updated Name");
        Product updatedProduct = productRepository.save(savedProduct);
        Assertions.assertThat(updatedProduct).isNotNull();
        Assertions.assertThat(updatedProduct.getDescription()).isNotNull();
        Assertions.assertThat(updatedProduct.getName()).isNotNull();

    }

    @Test
    void ProductRepository_findByCode_ReturnsProduct() {
        //Arrange
        Product product = Product.builder()
                .code(1L)
                .description("Test Product")
                .name("Test Product")
                .build();
        productRepository.save(product);
        //Act
        Optional<Product> productOptional = productRepository.findByCode(product.getCode());
        //Assert
        Assertions.assertThat(productOptional.isPresent()).isTrue();
        Assertions.assertThat(productOptional.get()).isNotNull();
    }

    @Test
    void ProductRepository_deleteByCode_ReturnsTrue() {
        //Arrange
        Product product = Product.builder()
                .code(1L)
                .description("Test Product")
                .name("Test Product")
                .build();
        productRepository.save(product);
        //Act
        productRepository.deleteByCode(product.getCode());
        Optional<Product> productOptional = productRepository.findByCode(product.getCode());
        //Assert
        Assertions.assertThat(productOptional.isPresent()).isFalse();
    }
}