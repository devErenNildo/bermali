package com.bermali.controller;

import com.bermali.domain.product.DtoProductRequest;
import com.bermali.domain.product.Product;
import com.bermali.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestBody DtoProductRequest productRequest){

        Product product = new Product(
                productRequest.getProductId(),
                productRequest.getName(),
                productRequest.getImage(),
                productRequest.getProductUrl()
        );

        return ResponseEntity.ok(productService.saveProduct(product));
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.ok(productService.findAllProducts());
    }
}
