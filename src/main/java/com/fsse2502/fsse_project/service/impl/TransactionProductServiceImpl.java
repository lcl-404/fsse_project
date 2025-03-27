package com.fsse2502.fsse_project.service.impl;

import com.fsse2502.fsse_project.data.cartItem.entity.CartItemEntity;
import com.fsse2502.fsse_project.data.transaction.entity.TransactionEntity;
import com.fsse2502.fsse_project.data.transactionProduct.entity.TransactionProductEntity;
import com.fsse2502.fsse_project.repository.TransactionProductRepository;
import com.fsse2502.fsse_project.service.TransactionProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TransactionProductServiceImpl implements TransactionProductService {

    private final TransactionProductRepository transactionProductRepository;

    public TransactionProductServiceImpl(TransactionProductRepository transactionProductRepository) {
        this.transactionProductRepository = transactionProductRepository;
    }

    @Override
    public void saveTransactionProducts(TransactionEntity transactionEntity, List<CartItemEntity> cartItemEntities) {
//        List<TransactionProductEntity> transactionProductEntities = new ArrayList<>();
//        for (CartItemEntity cartItem : cartItemEntities) {
//            TransactionProductEntity transactionProduct = new TransactionProductEntity(
//                    transactionEntity,
//                    cartItem.getProduct(),
//                    cartItem.getQuantity()
//            );
//            transactionProductEntities.add(transactionProduct);
//
//        }
//        Iterable<TransactionProductEntity> savedIterable = transactionProductRepository.saveAll(transactionProductEntities);
//        List<TransactionProductEntity> savedProducts = new ArrayList<>();
//        for (TransactionProductEntity savedProduct : savedIterable) {
//            savedProducts.add(savedProduct);
//        }
//        transactionEntity.setTransactionProductEntityList(savedProducts);

        List<TransactionProductEntity> transactionProductEntities = cartItemEntities.stream()
                .map(cartItem -> new TransactionProductEntity(
                        transactionEntity,
                        cartItem.getProduct(),
                        cartItem.getQuantity()
                ))
                .collect(Collectors.toList());

//        Iterable<TransactionProductEntity> savedIterable = transactionProductRepository.saveAll(transactionProductEntities);
//        List<TransactionProductEntity> savedProducts = StreamSupport.stream(savedIterable.spliterator(), false)
//                .collect(Collectors.toList());
//        transactionEntity.setTransactionProductEntityList(savedProducts);

        List<TransactionProductEntity> savedProducts = new ArrayList<>();
        transactionProductRepository.saveAll(transactionProductEntities)
                .forEach(savedProducts::add);
        transactionEntity.setTransactionProductEntityList(savedProducts);
    }

}
