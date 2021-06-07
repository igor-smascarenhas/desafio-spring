package com.digitalhouse.desafiospring.dtos;

import com.digitalhouse.desafiospring.entities.Post;

public class PromoPostDTO extends PostDTO {

    private Boolean hasPromo;
    private Double discount;

    public PromoPostDTO(Post post) {
        super(post);
        this.hasPromo = post.getHasPromo();
        this.discount = post.getDiscount();
    }
    public Boolean getHasPromo() {
        return hasPromo;
    }

    public void setHasPromo(Boolean hasPromo) {
        this.hasPromo = hasPromo;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}
