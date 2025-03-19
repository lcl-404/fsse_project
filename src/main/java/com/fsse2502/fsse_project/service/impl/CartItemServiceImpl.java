package com.fsse2502.fsse_project.service.impl;

import com.fsse2502.fsse_project.data.cartItem.domainObject.response.CartItemResponseData;
import com.fsse2502.fsse_project.data.cartItem.entity.CartItemEntity;
import com.fsse2502.fsse_project.data.product.entity.ProductEntity;
import com.fsse2502.fsse_project.data.user.domainObject.request.FireBaseUserData;
import com.fsse2502.fsse_project.data.user.entity.UserEntity;
import com.fsse2502.fsse_project.exception.cartItem.ProductNotInCartException;
import com.fsse2502.fsse_project.exception.product.ProductOutOfStockException;
import com.fsse2502.fsse_project.repository.CartItemRepository;
import com.fsse2502.fsse_project.service.CartItemService;
import com.fsse2502.fsse_project.service.ProductService;
import com.fsse2502.fsse_project.service.UserService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public void putCartItem(FireBaseUserData fireBaseUserData, Integer pid, Integer quantity){
        try{
            ProductEntity productEntity = productService.getEntityByPid(pid);
            UserEntity userEntity = userService.getEntityByEmail(fireBaseUserData);

            if (productHasStock(productEntity, quantity)) {
//                UserEntity userEntity = userService.getEntityByEmail(fireBaseUserData);
//                if(cartHasExistingItem(userEntity, productEntity).isPresent()){
//                    CartItemEntity existingCartItemEntity = cartHasExistingItem(userEntity,productEntity).get();
//                    Integer newQuantity = existingCartItemEntity.getQuantity() + quantity;
//                    if(productHasStock(productEntity, newQuantity)){
//                        existingCartItemEntity.setQuantity(newQuantity);
//                        cartItemRepository.save(existingCartItemEntity);
//                    }
//                } else {
//                    CartItemEntity cartItemEntity = new CartItemEntity(userEntity, productEntity, quantity);
//                    cartItemRepository.save(cartItemEntity);
//                }
                Optional <CartItemEntity> existingCartItem = cartHasExistingItem(userEntity, productEntity);
                existingCartItem.ifPresentOrElse(
                        cartItem -> {
                            Integer newQuantity = cartItem.getQuantity() + quantity;
                            cartItem.setQuantity(newQuantity);
                            cartItemRepository.save(cartItem);
                        },
                        ()-> cartItemRepository.save(new CartItemEntity(userEntity, productEntity, quantity))
                );


            }
        } catch (Exception e) {
            log.warn("Add item(s) in cart failed: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<CartItemResponseData> getUserCart(FireBaseUserData fireBaseUserData){
        UserEntity userEntity = userService.getEntityByEmail(fireBaseUserData);
        List <CartItemEntity> userCart = cartItemRepository.findByUser(userEntity);
        List <CartItemResponseData> cartItemResponseDataList = new ArrayList<>();
        for (CartItemEntity cartItem : userCart){
            cartItemResponseDataList.add(new CartItemResponseData(cartItem));
        }
        return cartItemResponseDataList;
    }

    @Override
    public void updateCartQuantity(FireBaseUserData fireBaseUserData, Integer pid, Integer quantity){
        UserEntity userEntity = userService.getEntityByEmail(fireBaseUserData);
        ProductEntity productEntity = productService.getEntityByPid(pid);
        try {
            if (cartHasExistingItem(userEntity, productEntity).isEmpty()) {
                throw new ProductNotInCartException(pid);
            }

            CartItemEntity existingProductEntity = cartHasExistingItem(userEntity, productEntity).get();
            existingProductEntity.setQuantity(quantity);
            cartItemRepository.save(existingProductEntity);

        } catch (Exception e){
            log.warn("Update cart item quantity failed: " + e.getMessage());
            throw e;
        }

    }

    @Transactional
    @Override
    public void deleteCartItem(FireBaseUserData fireBaseUserData, Integer pid){
        UserEntity userEntity = userService.getEntityByEmail(fireBaseUserData);
        ProductEntity productEntity = productService.getEntityByPid(pid);

        try{
            if (cartHasExistingItem(userEntity, productEntity).isEmpty()){
                throw new ProductNotInCartException(pid);
            }
            cartItemRepository.removeCartItemEntitiesByProduct(productEntity);
        }catch (Exception e){
            log.warn("Delete cart item failed: " + e.getMessage());
            throw e;
        }
    }

    public Optional<CartItemEntity> cartHasExistingItem(UserEntity user, ProductEntity product){
        return (cartItemRepository.findByUserAndProduct(user, product));
    }

    public boolean productHasStock(ProductEntity productEntity, Integer quantity){
        if(productEntity.getStock() > 0 &&
            productEntity.getStock() >= quantity){
            return true;
        }
        throw new ProductOutOfStockException(productEntity.getId());
    }
}
