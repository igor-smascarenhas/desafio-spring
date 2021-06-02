package com.digitalhouse.desafiospring.controllers;

import com.digitalhouse.desafiospring.dtos.ProductDTO;
import com.digitalhouse.desafiospring.entities.Product;
import com.digitalhouse.desafiospring.services.ProductService;
import javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/{productId}", method = RequestMethod.GET)
    public ResponseEntity<ProductDTO> findById(@PathVariable("productId") Long id) {
        ProductDTO productDTO = null;

        try {
            productDTO = productService.findById(id);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok().body(productDTO);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity findById(@RequestBody Product product) {
        productService.save(product);
        return ResponseEntity.ok().build();
    }

}
