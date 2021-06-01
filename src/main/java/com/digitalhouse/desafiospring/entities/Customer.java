package com.digitalhouse.desafiospring.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer extends User {

    @ManyToMany(mappedBy = "followers")
    private List<Seller> following = new ArrayList<>();

    public Customer() {
    }

    public Customer(Long id, String username) {
        super(id, username);
    }

    public List<Seller> getFollowing() {
        return following;
    }

    public void follow(Seller seller) {
        this.following.add(seller);
    }
}
