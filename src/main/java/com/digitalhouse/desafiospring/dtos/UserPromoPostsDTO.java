package com.digitalhouse.desafiospring.dtos;

import java.util.List;

public class UserPromoPostsDTO {

    private Long userId;
    private String userName;
    private List<PromoPostDTO> promoPostDTOS;

    public UserPromoPostsDTO(Long userId, String userName, List<PromoPostDTO> promoPostDTOS) {
        this.userId = userId;
        this.userName = userName;
        this.promoPostDTOS = promoPostDTOS;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<PromoPostDTO> getPosts() {
        return promoPostDTOS;
    }

    public void setPromoPostDTOS(List<PromoPostDTO> promoPostDTOS) {
        this.promoPostDTOS = promoPostDTOS;
    }

}
