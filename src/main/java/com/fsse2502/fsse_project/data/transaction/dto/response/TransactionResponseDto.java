package com.fsse2502.fsse_project.data.transaction.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fsse2502.fsse_project.data.transaction.domainObject.response.TransactionResponseData;
import com.fsse2502.fsse_project.data.transactionProduct.domainObject.response.TransactionProductResponseData;
import com.fsse2502.fsse_project.data.transactionProduct.dto.response.TransactionProductResponseDto;
import com.fsse2502.fsse_project.data.user.entity.UserEntity;
import com.fsse2502.fsse_project.enumuration.TransactionStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class TransactionResponseDto {

    private Integer tid;
    @JsonProperty("buyerUid")
    private Integer buyerUid;
    @JsonFormat(pattern = "yyyyMMdd'T'HH:mm:ss")
    private LocalDateTime dateTime;
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;
    private BigDecimal total;
    @JsonProperty("items")
    private List<TransactionProductResponseDto> transactionProductResponseDtoList = new ArrayList<>();

    public TransactionResponseDto() {
    }
    public TransactionResponseDto(TransactionResponseData transactionResponseData) {
        this.tid = transactionResponseData.getTid();
        this.buyerUid = transactionResponseData.getUser().getUid();
        this.dateTime = transactionResponseData.getDateTime();
        this.status = transactionResponseData.getStatus();
        this.total = transactionResponseData.getTotal();
        this.transactionProductResponseDtoList = convertResponseDto(transactionResponseData);
    }


    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getBuyerUid() {
        return buyerUid;
    }

    public void setBuyerUid(Integer buyerUid) {
        this.buyerUid = buyerUid;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<TransactionProductResponseDto> getTransactionProductResponseDtoList() {
        return transactionProductResponseDtoList;
    }

    public void setTransactionProductResponseDtoList(List<TransactionProductResponseDto> transactionProductResponseDtoList) {
        this.transactionProductResponseDtoList = transactionProductResponseDtoList;
    }

    public List<TransactionProductResponseDto> convertResponseDto (TransactionResponseData transactionResponseData){
        List<TransactionProductResponseData> productResponseData = transactionResponseData.getTransactionProductResponseDataList();
        List<TransactionProductResponseDto> transactionProductResponseDto = new ArrayList<>();
        for (TransactionProductResponseData transactionProductResponseData : productResponseData){
            transactionProductResponseDto.add(new TransactionProductResponseDto(transactionProductResponseData));
        }
        return transactionProductResponseDto;
    }
}
