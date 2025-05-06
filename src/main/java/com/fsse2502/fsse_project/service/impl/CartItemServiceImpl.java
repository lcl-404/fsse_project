package com.fsse2502.fsse_project.service.impl;

import com.fsse2502.fsse_project.data.cartItem.domainObject.response.CartItemResponseData;
import com.fsse2502.fsse_project.data.cartItem.entity.CartItemEntity;
import com.fsse2502.fsse_project.data.product.entity.ProductEntity;
import com.fsse2502.fsse_project.data.user.domainObject.request.FireBaseUserData;
import com.fsse2502.fsse_project.data.user.entity.UserEntity;
import com.fsse2502.fsse_project.exception.cartItem.ProductNotInCartException;
import com.fsse2502.fsse_project.repository.CartItemRepository;
import com.fsse2502.fsse_project.service.CartItemService;
import com.fsse2502.fsse_project.service.ProductService;
import com.fsse2502.fsse_project.service.UserService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {
    private final Logger log = LoggerFactory.getLogger(CartItemServiceImpl.class);

    private final UserService userService;
    private final CartItemRepository cartItemRepository;
    private final ProductService productService;

    public CartItemServiceImpl(UserService userService, CartItemRepository cartItemRepository, ProductService productService) {
        this.userService = userService;
        this.cartItemRepository = cartItemRepository;
        this.productService = productService;
    }

    @Transactional
    @Override
    public void putCartItem(FireBaseUserData fireBaseUserData, Integer pid, Integer quantity){
        try{
            ProductEntity productEntity = productService.getEntityByPid(pid);
            UserEntity userEntity = userService.getEntityByEmail(fireBaseUserData);
            log.debug(userEntity.getEmail());
            productService.productHasStock(productEntity, quantity);
            CartItemEntity cartItemEntity = cartItemRepository.findByUserAndProduct(userEntity, productEntity)
                    .orElseGet(()-> new CartItemEntity(userEntity, productEntity, 0));
            cartItemEntity.setQuantity(cartItemEntity.getQuantity()+quantity);
            productService.productHasStock(productEntity, cartItemEntity.getQuantity());
            cartItemRepository.save(cartItemEntity);

        } catch (Exception e) {
            log.warn("Add item(s) in cart failed: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public List<CartItemEntity> getUserCartByUser(UserEntity userEntity){
        log.debug(userEntity.getEmail());
        List<CartItemEntity> cartItemEntityList= cartItemRepository.findByUser(userEntity);
        return cartItemEntityList;
    }



    @Override
    public List<CartItemResponseData> getUserCartResponseData(FireBaseUserData fireBaseUserData){
        UserEntity userEntity = userService.getEntityByEmail(fireBaseUserData);
        List <CartItemEntity> cartItemEntityList = cartItemRepository.findByUser(userEntity);
        List <CartItemResponseData> cartItemResponseDataList = new ArrayList<>();
        for (CartItemEntity cartItem : cartItemEntityList){
            cartItemResponseDataList.add(new CartItemResponseData(cartItem));
        }
        return cartItemResponseDataList;
    }

    @Transactional
    @Override
    public void patchCartQuantity(FireBaseUserData fireBaseUserData, Integer pid, Integer quantity){
        try {
            UserEntity userEntity = userService.getEntityByEmail(fireBaseUserData);
            ProductEntity productEntity = productService.getEntityByPid(pid);
            CartItemEntity cartItemEntity = cartHasExistingItem(userEntity, productEntity);
            productService.productHasStock(productEntity, quantity);
            cartItemEntity.setQuantity(quantity);
        } catch (Exception e){
            log.warn("Update cart item quantity failed: " + e.getMessage());
            throw e;
        }

    }

    @Transactional
    @Override
    public void deleteCartItem(FireBaseUserData fireBaseUserData, Integer pid){
        try{
            if (cartItemRepository.deleteByUser_EmailAndProduct_pid(fireBaseUserData.getEmail(), pid) == 0){
                throw new ProductNotInCartException(pid);
            }
        }catch (Exception e){
            log.warn("Delete cart item failed: " + e.getMessage());
            throw e;
        }
    }

    @Transactional
    @Override
    @CacheEvict(value = "cartItems", key = "#user.firebaseUid")
    public void emptyCart(UserEntity user){
        cartItemRepository.deleteByUser(user);
    }

    public CartItemEntity cartHasExistingItem(UserEntity user, ProductEntity product){
        return cartItemRepository.findByUserAndProduct(user, product)
                .orElseThrow(()-> new ProductNotInCartException(product.getPid()));
    }

}
