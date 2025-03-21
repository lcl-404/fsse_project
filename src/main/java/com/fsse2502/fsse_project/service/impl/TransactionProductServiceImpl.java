package com.fsse2502.fsse_project.service.impl;

import com.fsse2502.fsse_project.data.cartItem.entity.CartItemEntity;
import com.fsse2502.fsse_project.data.transaction.entity.TransactionEntity;
import com.fsse2502.fsse_project.data.transactionProduct.entity.TransactionProductEntity;
import com.fsse2502.fsse_project.repository.TransactionProductRepository;
import com.fsse2502.fsse_project.service.TransactionProductService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionProductServiceImpl implements TransactionProductService {

    private final TransactionProductRepository transactionProductRepository;

    public TransactionProductServiceImpl(TransactionProductRepository transactionProductRepository) {
        this.transactionProductRepository = transactionProductRepository;
    }



    @Override
    public void saveTransactionProducts(TransactionEntity transactionEntity, List<CartItemEntity> cartItemEntities){
        List<TransactionProductEntity> transactionProductEntities = new ArrayList<>();
        for (CartItemEntity cartItem : cartItemEntities){
            TransactionProductEntity transactionProduct = new TransactionProductEntity(
                    transactionEntity,
                    cartItem.getProduct(),
                    cartItem.getQuantity()
            );
            transactionProductEntities.add(transactionProduct);
            //transactionEntity.addTransactionProduct(transactionProduct);

        }
        Iterable<TransactionProductEntity> savedIterable = transactionProductRepository.saveAll(transactionProductEntities);
        List<TransactionProductEntity> savedProducts = new ArrayList<>();
        for (TransactionProductEntity savedProduct : savedIterable) {
            savedProducts.add(savedProduct);
        }
        transactionEntity.setTransactionProductEntityList(savedProducts);
    }

}
