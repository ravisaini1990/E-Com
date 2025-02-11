package com.ravi.ramzan.service;

import com.ravi.ramzan.modal.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    Product getProductById(Integer productId);

    ResponseEntity<Product> saveProduct(Product product);

    ResponseEntity<Product> updateProduct(Product product);

    ResponseEntity<?> removeProduct(Integer productId);
}
