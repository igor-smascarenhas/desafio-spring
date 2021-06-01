package com.digitalhouse.desafiospring.controllers;

import com.digitalhouse.desafiospring.dtos.CustomerDTO;
import com.digitalhouse.desafiospring.services.CustomerService;
import javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public ResponseEntity<List<CustomerDTO>> findAll() {
        List<CustomerDTO> customers = customerService.findAll();
        return ResponseEntity.ok().body(customers);
    }

    @RequestMapping(value = "/{userId}/follow/{userIdToFollow}", method = RequestMethod.POST)
    public ResponseEntity follow(@PathVariable("userId") Long userId, @PathVariable("userIdToFollow") Long userIdToFollow) {
        try {
            customerService.followSeller(userId, userIdToFollow);
            return ResponseEntity.ok().build();
        } catch (NotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/{userId}/following/list", method = RequestMethod.GET)
    public ResponseEntity<CustomerDTO> getCustomerFollowingList(@PathVariable("userId") Long userId) {
        CustomerDTO customerDTO = null;

        try {
            customerDTO = customerService.findById(userId);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(customerDTO);
    }

}
