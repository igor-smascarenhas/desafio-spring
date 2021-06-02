package com.digitalhouse.desafiospring.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Seller extends User {

    @ManyToMany
    @JoinTable(name = "tb_seller_follower",
    joinColumns = @JoinColumn(name = "seller_id"),
    inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> followers = new ArrayList<>();

    public Seller() {
    }

    public Seller(Long id, String username) {
        super(id, username);
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void addFollower(User follower) {
        this.followers.add(follower);
    }
}
