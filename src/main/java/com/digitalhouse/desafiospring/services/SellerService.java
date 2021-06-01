package com.digitalhouse.desafiospring.services;

import com.digitalhouse.desafiospring.entities.Customer;
import com.digitalhouse.desafiospring.entities.Seller;
import com.digitalhouse.desafiospring.repositories.CustomerRepository;
import com.digitalhouse.desafiospring.repositories.SellerRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SellerService {

    private SellerRepository sellerRepository;
    private CustomerRepository customerRepository;

    public SellerService(SellerRepository sellerRepository, CustomerRepository customerRepository) {
        this.sellerRepository = sellerRepository;
        this.customerRepository = customerRepository;
    }

    public List<Seller> findAll() {
        return sellerRepository.findAll();
    }



}
