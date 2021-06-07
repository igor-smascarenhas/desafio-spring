package com.digitalhouse.desafiospring.dtos;

import com.digitalhouse.desafiospring.entities.Post;

import java.util.Date;

public class PostDTO implements Comparable<PostDTO> {

    private Long id;
    private Long userId;
    private Date date;
    private ProductDTO detail;
    private Integer category;
    private Double price;

    public PostDTO() {
    }

    public PostDTO(Post post) {
        this.id = post.getId();
        this.userId = post.getUserId();
        this.date = post.getDate();
        this.detail = new ProductDTO(post.getDetail());
        this.category = post.getCategory();
        this.price = post.getPrice();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ProductDTO getDetail() {
        return detail;
    }

    public void setDetail(ProductDTO detail) {
        this.detail = detail;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public int compareTo(PostDTO o) {
        return date.compareTo(o.getDate());
    }
}
