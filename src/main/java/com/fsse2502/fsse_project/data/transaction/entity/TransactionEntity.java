package com.fsse2502.fsse_project.data.transaction.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fsse2502.fsse_project.data.transactionProduct.entity.TransactionProductEntity;
import com.fsse2502.fsse_project.data.user.entity.UserEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "transaction")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tid;
    @ManyToOne
    @JoinColumn(name = "buyer_uid")
    private UserEntity user;
    @Column (name = "datetime", nullable = false)
    @JsonFormat(pattern = "yyyyMMdd'T'HH:mm:ss")
    private LocalDateTime dateTime;
    @Column (nullable = false)
    private String status;
    @Column (nullable = false)
    private BigDecimal total;
    @OneToMany(mappedBy = "transaction")
    private List<TransactionProductEntity> transactionProductEntityList = new ArrayList<>();

    public TransactionEntity() {
    }
    public TransactionEntity(UserEntity user, LocalDateTime dateTime, String status, BigDecimal total) {
        this.user = user;
        this.dateTime = dateTime;
        this.status = status;
        this.total = total;
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

    public List<TransactionProductEntity> getTransactionProductEntityList() {
        return transactionProductEntityList;
    }

    public void setTransactionProductEntityList(List<TransactionProductEntity> transactionProductEntityList) {
        this.transactionProductEntityList = transactionProductEntityList;
    }

    public void addTransactionProduct(TransactionProductEntity transactionProduct) {
        transactionProductEntityList.add(transactionProduct);
        transactionProduct.setTransaction(this);
    }
}
