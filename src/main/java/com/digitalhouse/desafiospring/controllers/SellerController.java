package com.digitalhouse.desafiospring.controllers;

import com.digitalhouse.desafiospring.entities.Customer;
import com.digitalhouse.desafiospring.entities.Seller;
import com.digitalhouse.desafiospring.services.CustomerService;
import com.digitalhouse.desafiospring.services.SellerService;
import javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class SellerController {

    private SellerService sellerService;

    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @RequestMapping(value = "/sellers", method = RequestMethod.GET)
    public ResponseEntity<List<Seller>> findAll() {
        List<Seller> sellers = sellerService.findAll();
        return ResponseEntity.ok().body(sellers);
    }

}
