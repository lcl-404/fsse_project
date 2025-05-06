package com.fsse2502.fsse_project.data.product.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fsse2502.fsse_project.data.product.domainObject.response.ProductResponseData;

import java.math.BigDecimal;
import java.security.SecureRandom;

public class GetAllProductResponseDto {
    private Integer pid;
    private String name;
    private String imageUrl;
    private BigDecimal price;
    @JsonProperty("hasStock")
    private boolean getStock;
    private String category;

    public GetAllProductResponseDto(ProductResponseData responseData) {
        this.pid = responseData.getPid();
        this.name = responseData.getName();
        this.imageUrl = responseData.getImageUrl();
        this.price = responseData.getPrice();
        this.getStock = (responseData.getStock()>0);
        this.category = responseData.getCategory();
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer id) {
        this.pid = id;
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

    public boolean getGetStock() {
        return getStock;
    }

    public void setGetStock(boolean getStock) {
        this.getStock = getStock;
    }

    public boolean isGetStock() {
        return getStock;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
