package com.isaguler.springunittestexamples.service.product;

import com.isaguler.springunittestexamples.model.product.Product;
import com.isaguler.springunittestexamples.repository.product.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void getProductsThatExist() {
        when(productRepository.getProduct(1L))
                .thenReturn(new Product(1L, "product-1", new BigDecimal(100)));

        List<Product> products = productService.getProducts(1L);

        assertAll("products",
                () -> assertEquals(1, products.size()),
                () -> assertEquals(1L, products.get(0).getId()),
                () -> assertEquals("product-1", products.get(0).getName()),
                () -> assertEquals(BigDecimal.valueOf(100L), products.get(0).getPrice()));

        verify(productRepository).getProduct(1L);
    }

    @Test
    void getProductsThatNotExist() {
        when(productRepository.getProduct(anyLong())).thenThrow(new IllegalArgumentException("Product does not exist"));

        Exception e = assertThrows(IllegalArgumentException.class, () -> productService.getProducts(1L, 2L, 3L));
        assertEquals(e.getMessage(), "Product does not exist");

        verify(productRepository).getProduct(anyLong());
    }

    @Test
    void saveProduct() {
        Product product1 = new Product(1L, "product-1", new BigDecimal(10));
        Product product2 = new Product(2L, "product-2", new BigDecimal(20));

        when(productRepository.saveProduct(product1)).thenReturn(product1);
        when(productRepository.saveProduct(product2)).thenReturn(product2);

        List<Product> products = productService.saveProducts(product1, product2);

        assertEquals(product1, products.get(0));
        assertEquals(product2, products.get(1));

        verify(productRepository, times(2)).saveProduct(any(Product.class));
    }

}