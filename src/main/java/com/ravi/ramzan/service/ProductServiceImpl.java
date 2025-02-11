package com.ravi.ramzan.service;

import com.ravi.ramzan.modal.Product;
import com.ravi.ramzan.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepo productRepo;

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product getProductById(Integer productId) {
        return productRepo.findById(productId).orElse(null);
    }

    @Override
    public ResponseEntity<Product> saveProduct(Product product) {
        Product newProduct = productRepo.save(product);
        return ResponseEntity.ok().body(newProduct);
    }

    @Override
    public ResponseEntity<Product> updateProduct(Product product) {
        return productRepo.findById(product.getId())
                .map(existingProduct -> {
                    existingProduct.setName(product.getName());
                    existingProduct.setDescription(product.getDescription());
                    existingProduct.setPrice(product.getPrice());
                    existingProduct.setUrl(product.getUrl());
                    return ResponseEntity.ok(productRepo.save(existingProduct));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<?> removeProduct(Integer productId) {
        Product product = productRepo.findById(productId).orElseThrow();
        productRepo.delete(product);
        return ResponseEntity.ok().body("Product deleted Successfully");
    }
}
