package com.isaguler.springunittestexamples.service.product;

import com.isaguler.springunittestexamples.model.product.Product;
import com.isaguler.springunittestexamples.repository.product.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts(Long... ids) {
        return Arrays.stream(ids)
                .map(productRepository::getProduct)
                .toList();
    }

    public List<Product> saveProducts(Product... products) {
        return Arrays.stream(products)
                .map(productRepository::saveProduct)
                .toList();
    }


}
