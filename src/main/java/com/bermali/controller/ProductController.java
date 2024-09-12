package com.bermali.controller;

import com.bermali.domain.product.DtoProductRequest;
import com.bermali.domain.product.Product;
import com.bermali.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

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
}
