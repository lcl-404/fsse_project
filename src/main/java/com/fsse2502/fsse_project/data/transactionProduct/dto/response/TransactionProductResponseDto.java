package com.fsse2502.fsse_project.data.transactionProduct.dto.response;

import com.fsse2502.fsse_project.data.transaction.domainObject.response.TransactionResponseData;
import com.fsse2502.fsse_project.data.transaction.entity.TransactionEntity;
import com.fsse2502.fsse_project.data.transactionProduct.domainObject.response.TransactionProductResponseData;
import com.fsse2502.fsse_project.data.transactionProduct.entity.TransactionProductEntity;

import java.math.BigDecimal;

public class TransactionProductResponseDto {

    private Integer tpid;
    private Integer tid;
    private Integer pid;
    private String name;
    private String description;
    private String imageUrl;
    private BigDecimal price;
    private Integer stock;
    private Integer quantity;

    public TransactionProductResponseDto() {
    }

    public TransactionProductResponseDto(TransactionProductResponseData data) {
        this.tpid =data.getTpid();
        this.pid = data.getPid();
        this.tid = data.getTid();
        this.name = data.getName();
        this.description = data.getDescription();
        this.imageUrl = data.getImageUrl();
        this.price = data.getPrice();
        this.stock = data.getStock();
        this.quantity = data.getQuantity();

    }


    public Integer getTpid() {
        return tpid;
    }

    public void setTpid(Integer tpid) {
        this.tpid = tpid;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
