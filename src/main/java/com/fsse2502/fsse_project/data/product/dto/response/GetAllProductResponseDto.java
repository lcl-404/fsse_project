package com.fsse2502.fsse_project.data.product.dto.response;

import com.fsse2502.fsse_project.data.product.domainObject.response.ProductResponseData;

import java.math.BigDecimal;

public class GetAllProductResponseDto {
    private Integer id;
    private String name;
    private String imageUrl;
    private BigDecimal price;
    private boolean getStock;

    public GetAllProductResponseDto(ProductResponseData responseData) {
        this.id = responseData.getId();
        this.name = responseData.getName();
        this.imageUrl = responseData.getImageUrl();
        this.price = responseData.getPrice();
        this.getStock = (responseData.getStock()>0);
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
}
