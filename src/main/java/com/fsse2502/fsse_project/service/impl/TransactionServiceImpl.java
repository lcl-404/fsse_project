package com.fsse2502.fsse_project.service.impl;

import com.fsse2502.fsse_project.data.cartItem.entity.CartItemEntity;
import com.fsse2502.fsse_project.data.transaction.domainObject.response.TransactionResponseData;
import com.fsse2502.fsse_project.data.transaction.entity.TransactionEntity;
import com.fsse2502.fsse_project.data.user.domainObject.request.FireBaseUserData;
import com.fsse2502.fsse_project.data.user.entity.UserEntity;
import com.fsse2502.fsse_project.exception.UserCartEmptyException;
import com.fsse2502.fsse_project.repository.CartItemRepository;
import com.fsse2502.fsse_project.repository.TransactionProductRepository;
import com.fsse2502.fsse_project.repository.TransactionRepository;
import com.fsse2502.fsse_project.service.CartItemService;
import com.fsse2502.fsse_project.service.TransactionProductService;
import com.fsse2502.fsse_project.service.TransactionService;
import com.fsse2502.fsse_project.service.UserService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private static final Logger log = LoggerFactory.getLogger(TransactionServiceImpl.class);

    private final UserService userService;
    private final CartItemRepository cartItemRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionProductService transactionProductService;

    public TransactionServiceImpl(UserService userService, CartItemRepository cartItemRepository, TransactionRepository transactionRepository, TransactionProductService transactionProductService) {
        this.userService = userService;
        this.cartItemRepository = cartItemRepository;
        this.transactionRepository = transactionRepository;
        this.transactionProductService = transactionProductService;
    }

    @Override
    @Transactional
    public TransactionResponseData createNewTransaction(FireBaseUserData fireBaseUserData){
        try {
            UserEntity userEntity = userService.getEntityByEmail(fireBaseUserData);
            List<CartItemEntity> cartItemEntityList = cartItemRepository.findByUser(userEntity);
            if (cartItemEntityList.isEmpty()) {
                throw new UserCartEmptyException(userEntity.getUid());
            }
            BigDecimal total = calculateTotal(cartItemEntityList);
            TransactionEntity transactionEntity = new TransactionEntity(userEntity, LocalDateTime.now(), "PREPARE", total);
            transactionRepository.save(transactionEntity);
            transactionProductService.saveTransactionProducts(transactionEntity, cartItemEntityList);
            return new TransactionResponseData(transactionEntity);

        }catch (Exception e){

            throw e;
        }

    }

    public BigDecimal calculateTotal(List<CartItemEntity> cartItemEntities){
        BigDecimal total = BigDecimal.ZERO;
        for (CartItemEntity cartItem : cartItemEntities){
            BigDecimal subtotal = cartItem.getProduct().getPrice()
                    .multiply(BigDecimal.valueOf(cartItem.getQuantity()));
            total = total.add(subtotal);
        }
        return total;
    }
}
