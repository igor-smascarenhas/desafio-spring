package com.digitalhouse.desafiospring.services;

import com.digitalhouse.desafiospring.dtos.ProductDTO;
import com.digitalhouse.desafiospring.entities.Product;
import com.digitalhouse.desafiospring.repositories.ProductRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDTO findById(Long id) throws NotFoundException {
        Optional<Product> productOptional = productRepository.findById(id);

        if(!productOptional.isPresent()) {
            throw new NotFoundException("Product with id: " + id);
        }

        return new ProductDTO(productOptional.get());

    }

    public void save(Product product) {
       this.productRepository.save(product);
    }
    
}
