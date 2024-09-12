package com.bermali.services;

import com.bermali.domain.product.Product;
import com.bermali.repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }
}
