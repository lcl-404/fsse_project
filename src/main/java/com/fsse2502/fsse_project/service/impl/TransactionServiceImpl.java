package com.fsse2502.fsse_project.service.impl;

import com.fsse2502.fsse_project.data.cartItem.entity.CartItemEntity;
import com.fsse2502.fsse_project.data.transaction.domainObject.response.TransactionResponseData;
import com.fsse2502.fsse_project.data.transaction.entity.TransactionEntity;
import com.fsse2502.fsse_project.data.user.domainObject.request.FireBaseUserData;
import com.fsse2502.fsse_project.data.user.entity.UserEntity;
import com.fsse2502.fsse_project.enumuration.TransactionStatus;
import com.fsse2502.fsse_project.exception.cartItem.UserCartEmptyException;
import com.fsse2502.fsse_project.exception.transaction.TransactionCompletedException;
import com.fsse2502.fsse_project.exception.transaction.TransactionNotFoundException;
import com.fsse2502.fsse_project.exception.transaction.TransactionNotProcessingException;
import com.fsse2502.fsse_project.exception.transaction.TransactionProcessingException;
import com.fsse2502.fsse_project.repository.TransactionRepository;
import com.fsse2502.fsse_project.service.*;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final Logger log = LoggerFactory.getLogger(TransactionServiceImpl.class);

    private final UserService userService;
    private final TransactionRepository transactionRepository;
    private final TransactionProductService transactionProductService;
    private final CartItemService cartItemService;
    private final ProductService productService;

    public TransactionServiceImpl(UserService userService, TransactionRepository transactionRepository, TransactionProductService transactionProductService, CartItemService cartItemService, ProductService productService) {
        this.userService = userService;
        this.transactionRepository = transactionRepository;
        this.transactionProductService = transactionProductService;
        this.cartItemService = cartItemService;
        this.productService = productService;
    }

    @Override
    @Transactional
    public TransactionResponseData createNewTransaction(FireBaseUserData fireBaseUserData){
        try {
            UserEntity userEntity = userService.getEntityByEmail(fireBaseUserData);
            List<CartItemEntity> cartItemEntityList = cartItemService.getUserCartByUser(userEntity);
            if (cartItemEntityList.isEmpty()) {
                throw new UserCartEmptyException(userEntity.getUid());
            }
            TransactionEntity transactionEntity = new TransactionEntity(
                    userEntity,
                    calculateTotal(cartItemEntityList));
            transactionRepository.save(transactionEntity);
            transactionProductService.saveTransactionProducts(transactionEntity, cartItemEntityList);
            return new TransactionResponseData(transactionEntity);

        }catch (Exception e){
            log.warn("Create new transaction failed: " + e.getMessage());
            throw e;
        }

    }

    @Override
    public TransactionResponseData getTransaction(FireBaseUserData fireBaseUserData, Integer tid){
        try{
            UserEntity userEntity = userService.getEntityByEmail(fireBaseUserData);
            TransactionEntity transactionEntity = findEntityByTid(tid);
            validateUser(userEntity.getEmail(), transactionEntity);
            return new TransactionResponseData(transactionEntity);

        } catch (Exception e){

            log.warn("Get transaction failed: " + e.getMessage());
            throw e;
        }

    }

    @Transactional
    @Override
    public void updateTransactionStatus(FireBaseUserData fireBaseUserData, Integer tid){
        try{
            UserEntity userEntity = userService.getEntityByEmail(fireBaseUserData);
            TransactionEntity transactionEntity = findEntityByTid(tid);
            validateUser(userEntity.getEmail(), transactionEntity);
            if(transactionEntity.getStatus().equals(TransactionStatus.PROCESSING)){
                throw new TransactionProcessingException(tid);
            }
            if (transactionEntity.getStatus().equals(TransactionStatus.SUCCESS)){
                throw new TransactionCompletedException(tid);
            }
            transactionEntity.setStatus(TransactionStatus.PROCESSING);

        } catch (Exception e){
            log.warn("Update transaction failed " + e.getMessage());
            throw e;
        }
    }

    @Transactional
    @Override
    public TransactionResponseData finishTransaction(FireBaseUserData fireBaseUserData, Integer tid){
        try{
            UserEntity userEntity = userService.getEntityByEmail(fireBaseUserData);
            TransactionEntity transactionEntity = findEntityByTid(tid);
            validateUser(userEntity.getEmail(), transactionEntity);
            if(transactionEntity.getStatus().equals(TransactionStatus.PREPARE)){
                throw new TransactionNotProcessingException(tid);
            } else if (transactionEntity.getStatus().equals(TransactionStatus.SUCCESS)){
                throw new TransactionCompletedException(tid);
            }
            List<CartItemEntity> cartItemEntityList = cartItemService.getUserCartByUser(userEntity);
            for (CartItemEntity cartItemEntity : cartItemEntityList){
                productService.deductProductStock(cartItemEntity);
            }

            cartItemService.emptyCart(userEntity);
            transactionEntity.setStatus(TransactionStatus.SUCCESS);
            return new TransactionResponseData(transactionEntity);

        }catch (Exception e){
            log.warn("Pay transaction failed: " + e.getMessage());
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

    public TransactionEntity findEntityByTid(Integer tid){
        return transactionRepository.findByTid(tid).orElseThrow(
                ()-> new TransactionNotFoundException(tid)
        );
    }

    public void validateUser(String email, TransactionEntity transactionEntity){
        if(!email.equals(transactionEntity.getUser().getEmail())){
            throw new RuntimeException();
        }
    }

}
