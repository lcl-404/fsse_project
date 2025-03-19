package com.fsse2502.fsse_project.data.product.dto.response;

import com.fsse2502.fsse_project.data.product.domainObject.response.ProductResponseData;

import java.math.BigDecimal;

public class ProductResponseDto {

    private Integer id;
    private String name;
    private String description;
    private String imageUrl;
    private BigDecimal price;
    private Integer stock;

    public ProductResponseDto(ProductResponseData data) {
        this.id = data.getId();
        this.name = data.getName();
        this.description = data.getDescription();
        this.imageUrl = data.getImageUrl();
        this.price = data.getPrice();
        this.stock = data.getStock();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
