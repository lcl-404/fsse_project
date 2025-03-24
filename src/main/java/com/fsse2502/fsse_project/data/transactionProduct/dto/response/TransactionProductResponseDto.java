package com.fsse2502.fsse_project.data.transactionProduct.dto.response;

import com.fsse2502.fsse_project.data.product.dto.response.ProductResponseDto;
import com.fsse2502.fsse_project.data.transactionProduct.domainObject.response.TransactionProductResponseData;
import java.math.BigDecimal;

public class TransactionProductResponseDto {

    private Integer tpid;
    private ProductResponseDto product; // Use ProductDto
    private Integer quantity;
    private BigDecimal subtotal;



    public TransactionProductResponseDto(TransactionProductResponseData data) {
        this.tpid =data.getTpid();
        this.product = new ProductResponseDto(data);
        this.quantity = data.getQuantity();
        this.subtotal = data.getPrice().multiply(BigDecimal.valueOf(data.getQuantity()));

    }

    public Integer getTpid() {
        return tpid;
    }

    public void setTpid(Integer tpid) {
        this.tpid = tpid;
    }

    public ProductResponseDto getProduct() {
        return product;
    }

    public void setProduct(ProductResponseDto product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
}
