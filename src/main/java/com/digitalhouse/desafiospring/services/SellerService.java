package com.digitalhouse.desafiospring.services;

import com.digitalhouse.desafiospring.dtos.SellerDTO;
import com.digitalhouse.desafiospring.dtos.UserDTO;
import com.digitalhouse.desafiospring.entities.Customer;
import com.digitalhouse.desafiospring.entities.Seller;
import com.digitalhouse.desafiospring.repositories.CustomerRepository;
import com.digitalhouse.desafiospring.repositories.SellerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SellerService {

    private SellerRepository sellerRepository;
    private CustomerRepository customerRepository;

    public SellerService(SellerRepository sellerRepository, CustomerRepository customerRepository) {
        this.sellerRepository = sellerRepository;
        this.customerRepository = customerRepository;
    }

    public List<SellerDTO> findAll() {
        List<Seller> sellers = sellerRepository.findAll();
        List<SellerDTO> sellerDTOS = sellers.stream().map(seller -> new SellerDTO(seller)).collect(Collectors.toList());

        return sellerDTOS;
    }

    public SellerDTO find(Long sellerId) {
        Seller seller = sellerRepository.findById(sellerId).get();

        return new SellerDTO(seller);
    }

    public SellerDTO getSellerCount(Long sellerId) {
        Seller seller = sellerRepository.findById(sellerId).get();

        SellerDTO sellerDTO = new SellerDTO(seller);
        sellerDTO.setFollowers(null);

        return sellerDTO;
    }

    public SellerDTO getSellerFollowers(Long sellerId) {
        Seller seller = sellerRepository.findById(sellerId).get();

        SellerDTO sellerDTO = new SellerDTO(seller);
        sellerDTO.setFollowersCount(null);

        return sellerDTO;
    }


}
