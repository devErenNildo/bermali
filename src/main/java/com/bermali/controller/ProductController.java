package com.bermali.controller;

import com.bermali.domain.product.ProductRequestDTO;
import com.bermali.domain.product.Product;
import com.bermali.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestBody ProductRequestDTO productRequest){

        Product product = new Product(
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

    @GetMapping("/delete")
    public ResponseEntity<String> deleteProduct(@RequestParam UUID id){
        return ResponseEntity.ok(productService.deleteProduct(id));
    }
}
