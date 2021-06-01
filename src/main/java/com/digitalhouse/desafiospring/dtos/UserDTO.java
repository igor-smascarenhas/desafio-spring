package com.digitalhouse.desafiospring.dtos;

import com.digitalhouse.desafiospring.entities.Customer;
import com.digitalhouse.desafiospring.entities.Seller;

public class UserDTO {

    protected Long userId;
    protected String username;

    public UserDTO() {
    }

    public UserDTO(Customer customer) {
        this.userId = customer.getId();
        this.username = customer.getUsername();
    }

    public UserDTO(Seller seller) {
        this.userId = seller.getId();
        this.username = seller.getUsername();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
