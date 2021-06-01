package com.digitalhouse.desafiospring.services;

import com.digitalhouse.desafiospring.dtos.CustomerDTO;
import com.digitalhouse.desafiospring.entities.Customer;
import com.digitalhouse.desafiospring.entities.Seller;
import com.digitalhouse.desafiospring.repositories.CustomerRepository;
import com.digitalhouse.desafiospring.repositories.SellerRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;
    private SellerRepository sellerRepository;

    public CustomerService(CustomerRepository customerRepository, SellerRepository sellerRepository) {
        this.customerRepository = customerRepository;
        this.sellerRepository = sellerRepository;
    }

    public List<CustomerDTO> findAll() {
        List<Customer> customers = customerRepository.findAll();

        List<CustomerDTO> customerDTOS = customers.stream().map(customer -> new CustomerDTO(customer)).collect(Collectors.toList());

        return customerDTOS;
    }

    public void followSeller(Long customerId, Long sellerId) throws NotFoundException {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        Optional<Seller> sellerOptional = sellerRepository.findById(sellerId);

        if(!customerOptional.isPresent() || !sellerOptional.isPresent()) {
            throw new NotFoundException("Customer or Seller invalid");
        }

        Customer customer = customerOptional.get();
        Seller seller = sellerOptional.get();

        if(isAlreadyFollowing(customer, sellerId)) {
            return;
        }

        customer.follow(seller);
        seller.addFollower(customer);

        customerRepository.save(customer);
        sellerRepository.save(seller);
    }

    private boolean isAlreadyFollowing(Customer customer, Long sellerId) {
       Optional<Seller> sellerOptional = customer.getFollowing().stream().filter(seller -> seller.getId() == sellerId).findFirst();

        return sellerOptional.isPresent();
    }

}
