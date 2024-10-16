package com.bermali.services;

import com.bermali.domain.product.Product;
import com.bermali.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public List<Product> findAllProducts(){
        return productRepository.findAll();
    }

    public String deleteProduct(UUID productId){
        productRepository.deleteById(productId);
        return "Product deleted successfully";
    }
}
