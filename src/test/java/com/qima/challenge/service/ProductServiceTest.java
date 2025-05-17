package com.qima.challenge.service;

import com.qima.challenge.domain.Product;
import com.qima.challenge.dto.ProductDto;
import com.qima.challenge.exception.DuplicateResourceException;
import com.qima.challenge.exception.ResourceNotFoundException;
import com.qima.challenge.repository.ProductRepository;
import com.qima.challenge.specification.ProductSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    private ProductRepository productRepository;
    private ProductSpecification productSpecification;
    private ProductService productService;

    @BeforeEach
    void setup() {
        productRepository = mock(ProductRepository.class);
        productSpecification = new ProductSpecification();
        productService = new ProductService(productRepository, productSpecification);
    }

    @Test
    void testToFindById() {
        Product product = new Product();
        product.setId(1);
        product.setName("Test");
        product.setPrice(new BigDecimal("50.00"));
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        ProductDto dto = productService.findById(1L);

        assertEquals("Test", dto.getName());
        assertEquals(new BigDecimal("50.00"), dto.getPrice());
    }

    @Test
    void FindById_NotFound() {
        when(productRepository.findById(999L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> productService.findById(999L));
    }

    @Test
    void testToCreateOneProduct_DuplicateCode() {
        ProductDto dto = new ProductDto();
        dto.setName("Test Product");
        dto.setPrice(new BigDecimal("100.00"));
        dto.setCode("DUPLICATE");

        when(productRepository.findByCode("DUPLICATE")).thenReturn(Optional.of(new Product()));

        assertThrows(DuplicateResourceException.class, () -> productService.createProduct(dto));
    }

}
