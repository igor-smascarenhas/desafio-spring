package com.digitalhouse.desafiospring.dtos;

import com.digitalhouse.desafiospring.entities.Seller;
import com.digitalhouse.desafiospring.entities.User;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class UserDTO implements Comparable<UserDTO> {

    protected Long userId;
    protected String username;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<UserDTO> following = new ArrayList<>();

    public UserDTO() {
    }

    public UserDTO(Long userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    public UserDTO(Seller user) {
        this.userId = user.getId();
        this.username = user.getUsername();
        this.following = user.getFollowing().stream().map(seller -> new UserDTO(seller.getId(), seller.getUsername())).collect(Collectors.toList());
    }

    public UserDTO(User user) {
        this.userId = user.getId();
        this.username = user.getUsername();
        this.following = user.getFollowing().stream().map(seller -> new UserDTO(seller.getId(), seller.getUsername())).collect(Collectors.toList());
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

    public List<UserDTO> getFollowing() {
        return following;
    }

    public void setFollowing(List<UserDTO> following) {
        this.following = following;
    }

    @Override
    public int compareTo(UserDTO o) {
        return username.compareTo(o.getUsername());
    }
}
