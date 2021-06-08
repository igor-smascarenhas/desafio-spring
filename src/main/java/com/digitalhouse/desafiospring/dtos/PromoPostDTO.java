package com.digitalhouse.desafiospring.dtos;

import com.digitalhouse.desafiospring.entities.Post;
import com.fasterxml.jackson.annotation.JsonInclude;

public class PromoPostDTO extends PostDTO {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean hasPromo;

    @JsonInclude(JsonInclude.Include.NON_NULL)
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
