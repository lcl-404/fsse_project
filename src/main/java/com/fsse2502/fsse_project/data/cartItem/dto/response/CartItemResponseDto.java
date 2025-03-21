package com.fsse2502.fsse_project.data.cartItem.dto.response;

import com.fsse2502.fsse_project.data.cartItem.domainObject.response.CartItemResponseData;

import java.math.BigDecimal;

public class CartItemResponseDto {
    private Integer pid;
    private String name;
    private String imageUrl;
    private BigDecimal price;
    private Integer cartQuantity;
    private Integer stock;

    public CartItemResponseDto(CartItemResponseData responseData) {
        this.pid = responseData.getProduct().getId();
        this.name = responseData.getProduct().getName();
        this.imageUrl = responseData.getProduct().getImageUrl();
        this.price = responseData.getProduct().getPrice();
        this.cartQuantity = responseData.getQuantity();
        this.stock = responseData.getProduct().getStock();

    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getCartQuantity() {
        return cartQuantity;
    }

    public void setCartQuantity(Integer cartQuantity) {
        this.cartQuantity = cartQuantity;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
