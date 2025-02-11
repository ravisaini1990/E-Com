package com.ravi.ramzan.controller;

import com.ravi.ramzan.modal.Product;
import com.ravi.ramzan.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/products")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    List<Product> getProduct() {
        return productService.getAllProducts();
    }

    @GetMapping("/product/{productId}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    Product getProductById(@PathVariable int productId) {
        return productService.getProductById(productId);
    }

    @PostMapping("/product/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<?> addNewProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @PutMapping("/product/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @DeleteMapping("/product/remove/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<?> removeProduct(@PathVariable(value = "id") Integer id) {
        productService.removeProduct(id);
        return ResponseEntity.ok().body("Product deleted Successfully");
    }
}
