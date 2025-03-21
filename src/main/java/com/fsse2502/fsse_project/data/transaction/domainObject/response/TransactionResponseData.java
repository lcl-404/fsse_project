package com.fsse2502.fsse_project.data.transaction.domainObject.response;

import com.fsse2502.fsse_project.data.transaction.entity.TransactionEntity;
import com.fsse2502.fsse_project.data.transactionProduct.domainObject.response.TransactionProductResponseData;
import com.fsse2502.fsse_project.data.transactionProduct.entity.TransactionProductEntity;
import com.fsse2502.fsse_project.data.user.entity.UserEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class TransactionResponseData {

    private Integer tid;
    private UserEntity user;
    private LocalDateTime dateTime;
    private String status;
    private BigDecimal total;
    private List<TransactionProductResponseData> transactionProductResponseDataList = new ArrayList<>();

    public TransactionResponseData() {
    }
    public TransactionResponseData(TransactionEntity transaction) {
        this.tid = transaction.getTid();
        this.user = transaction.getUser();
        this.dateTime = transaction.getDateTime();
        this.status = transaction.getStatus();
        this.total = transaction.getTotal();
        this.transactionProductResponseDataList = convertResponseData(transaction);
    }


    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<TransactionProductResponseData> getTransactionProductResponseDataList() {
        return transactionProductResponseDataList;
    }

    public void setTransactionProductResponseDataList(List<TransactionProductResponseData> transactionProductResponseDataList) {
        this.transactionProductResponseDataList = transactionProductResponseDataList;
    }

    public List<TransactionProductResponseData> convertResponseData (TransactionEntity transaction){
        List<TransactionProductEntity> productEntities = transaction.getTransactionProductEntityList();
        List<TransactionProductResponseData> transactionProductResponseData = new ArrayList<>();
        for (TransactionProductEntity productEntity : productEntities){
            transactionProductResponseData.add(new TransactionProductResponseData(productEntity));
        }
        return transactionProductResponseData;
    }
}
