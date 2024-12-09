package org.example.springsample.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.springsample.dto.PaginatedResponse;
import org.example.springsample.dto.ProductDto;
import org.example.springsample.entity.Product;
import org.example.springsample.service.ProductService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(controllers = ProductController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class ProductControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProductService productService;

    @Autowired
    ObjectMapper objectMapper;

    private Product product;
    private ProductDto productDto;

    @BeforeEach
    public void setUp() {
        product = Product.builder()
                .name("Test product")
                .description("Test product")
                .code(100L)
                .build();

        productDto = ProductDto.builder()
                .code(100L)
                .name("Test product")
                .description("Test product")
                .build();
    }


    @Test
    void ProductController_createProduct_ReturnsCreatedProduct() throws Exception {
        given(productService.createProduct(ArgumentMatchers.any())).willAnswer((invocationOnMock -> invocationOnMock.getArgument(0)));

        ResultActions response = mockMvc.perform(post("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productDto))
        );

        response.andExpect(MockMvcResultMatchers.status().isCreated())
                // .andExpect(MockMvcResultMatchers.jsonPath("$.code", CoreMatchers.is(productDto.getCode())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(productDto.getName())));
    }

    @Test
    void ProductController_getProducts_ReturnsAllProductDtos() throws Exception {
        ArrayList<ProductDto> response = new ArrayList<>(
                Arrays.asList(ProductDto.builder()
                                .code(100L)
                                .name("Test product")
                                .description("Test product")
                                .build(),
                        ProductDto.builder()
                                .code(200L)
                                .name("Test product")
                                .description("Test product")
                                .build()
                ));
        when(productService.getProducts(PageRequest.of(1, 10))).thenReturn(response);

        mockMvc.perform(get("/api/v1/products").accept(MediaType.APPLICATION_JSON)
                        .param("page", "0")
                        .param("size", "10"))
                .andDo(print())
                // .content(objectMapper.writeValueAsString(response)))
                .andExpect(MockMvcResultMatchers.status().isOk());
        //.andExpect(MockMvcResultMatchers.jsonPath("$."))
        //.andExpect(MockMvcResultMatchers.jsonPath("$.length()", CoreMatchers.is(response.size())))
        // .andExpect(MockMvcResultMatchers.jsonPath("$.[0].name", CoreMatchers.is("Test product")));
    }

    @Test
    void updateProduct() {
    }

    @Test
    void ProductController_deleteProduct_ReturnsBoolean() throws Exception {
        Long proCode = 100L;
        when(productService.deleteProduct(proCode)).thenReturn(true);
        mockMvc.perform(delete("/api/v1/products/100")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getProduct() {

    }

    @Test
    void getProductByCode() {
    }
}