package com.digitalhouse.desafiospring.dtos;

import com.digitalhouse.desafiospring.entities.Customer;
import com.digitalhouse.desafiospring.entities.Seller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerDTO extends UserDTO {

    List<UserDTO> following = new ArrayList<>();

    public CustomerDTO(Customer customer) {
        this.userId = customer.getId();
        this.username = customer.getUsername();

        this.following = customer.getFollowing().stream().map(seller -> new UserDTO(seller)).collect(Collectors.toList());

    }

    public List<UserDTO> getFollowing() {
        return following;
    }

    public void setFollowing(List<UserDTO> following) {
        this.following = following;
    }
}
