package com.fsse2502.fsse_project.service;

import com.fsse2502.fsse_project.data.cartItem.entity.CartItemEntity;
import com.fsse2502.fsse_project.data.transaction.entity.TransactionEntity;

import java.util.List;

public interface TransactionProductService {
    void saveTransactionProducts(TransactionEntity transactionEntity, List<CartItemEntity> cartItemEntities);
}
