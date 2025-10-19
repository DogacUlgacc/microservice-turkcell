package com.turkcell.product_service.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @GetMapping
    public String getProducts() {
        return "getmapping";
    }

    @GetMapping("/{id}")
    public String getProductById(@PathVariable int id) {
        return "getmapping " + id;
    }

    @PutMapping
    public String updateProduct() {
        return "putmapping ";
    }

    @PostMapping
    public String createProduct() {
        return "Postmapping";
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id) {
        return "deletemapping " + id;
    }
}
