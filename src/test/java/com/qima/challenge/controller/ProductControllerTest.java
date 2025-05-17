package com.qima.challenge.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qima.challenge.dto.ProductDto;
import com.qima.challenge.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testToCreateOneProduct() throws Exception {
        String json = """
                    {
                      "name": "Test Product",
                      "description": "Test Description",
                      "price": 10.00,
                      "code": "CODE123",
                      "stockQuantity": 10,
                      "available": true,
                      "imageUrl": "http://img.com/1.png"
                    }
                """;

        mockMvc.perform(post("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .with(csrf()))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username = "user", roles = {"ADMIN"})
    void testToGetProductById() throws Exception {
        ProductDto dto = new ProductDto(1L, "Test Product", "Description", new BigDecimal("20.00"),
                null, "CODE123", 15, true, "http://img.com/2.png", null, null);

        when(productService.findById(1L)).thenReturn(dto);

        mockMvc.perform(get("/api/v1/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Test Product")))
                .andExpect(jsonPath("$.code", is("CODE123")));
    }
}
