package com.digitalhouse.desafiospring.dtos;

import com.digitalhouse.desafiospring.entities.Seller;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SellerDTO extends UserDTO {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<UserDTO> followers = new ArrayList<>();

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long followersCount;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<UserDTO> following = new ArrayList<>();

    public SellerDTO(Seller seller) {
        this.userId = seller.getId();
        this.username = seller.getUsername();
        this.followers = seller.getFollowers().stream().map(user -> new UserDTO(user.getId(), user.getUsername())).collect(Collectors.toList());
        this.following = seller.getFollowing().stream().map(user -> new UserDTO(user.getId(), user.getUsername())).collect(Collectors.toList());
        this.followersCount = Long.valueOf(followers.size());
    }

    public List<UserDTO> getFollowers() {
        return followers;
    }

    public void setFollowers(List<UserDTO> followers) {
        this.followers = followers;
    }

    public void setFollowersCount(Long followersCount) {
        this.followersCount = followersCount;
    }

    public Long getFollowersCount() {

        return followersCount;

    }



}
