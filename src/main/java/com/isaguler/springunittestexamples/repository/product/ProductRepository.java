package com.isaguler.springunittestexamples.repository.product;

import com.isaguler.springunittestexamples.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product getProduct(Long id);
    Product saveProduct(Product product);
}
