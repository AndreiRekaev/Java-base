package org.example.service;

import org.example.model.Product;
import org.example.service.ProductServiceImpl;
import org.example.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product testProduct;

    @BeforeEach
    void setUp() {
        testProduct = new Product();
        testProduct.setId(1L);
        testProduct.setName("Laptop");
        testProduct.setPrice(999.99);
        testProduct.setDesc("High-performance laptop");

        productService = new ProductServiceImpl(productRepository);
    }


    @Test
    void save_ShouldReturnSavedProduct() {
        when(productRepository.save(any(Product.class))).thenReturn(testProduct);

        Product savedProduct = productService.save(testProduct);

        verify(productRepository, times(1)).save(testProduct);
        assertEquals(testProduct, savedProduct);
    }

    @Test
    void findById_ShouldReturnProduct() {
        when(productRepository.findById(1L)).thenReturn(testProduct);

        Product foundProduct = productService.findById(1L);

        verify(productRepository, times(1)).findById(1L);
        assertEquals(testProduct, foundProduct);
    }

    @Test
    void findAll_ShouldReturnProductList() {
        List<Product> productList = List.of(testProduct);
        when(productRepository.findAll()).thenReturn(productList);

        List<Product> result = productService.findAll();

        verify(productRepository, times(1)).findAll();
        assertEquals(1, result.size());
        assertEquals(testProduct, result.get(0));
    }

    @Test
    void update_ShouldReturnUpdatedProduct() {
        when(productRepository.update(testProduct)).thenReturn(testProduct);

        Product updatedProduct = productService.update(testProduct);

        verify(productRepository, times(1)).update(testProduct);
        assertEquals(testProduct, updatedProduct);
    }

    @Test
    void delete_ShouldCallRepositoryDelete() {
        doNothing().when(productRepository).delete(1L);

        productService.delete(1L);

        verify(productRepository, times(1)).delete(1L);
    }
}