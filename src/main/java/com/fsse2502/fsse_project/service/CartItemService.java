package com.fsse2502.fsse_project.service;

import com.fsse2502.fsse_project.data.cartItem.domainObject.response.CartItemResponseData;
import com.fsse2502.fsse_project.data.cartItem.entity.CartItemEntity;
import com.fsse2502.fsse_project.data.product.entity.ProductEntity;
import com.fsse2502.fsse_project.data.user.domainObject.request.FireBaseUserData;
import com.fsse2502.fsse_project.data.user.entity.UserEntity;
import jakarta.transaction.Transactional;

import java.util.List;

public interface CartItemService {
    void putCartItem(FireBaseUserData fireBaseUserData, Integer pid, Integer quantity);

    List<CartItemEntity> getUserCartByUser (UserEntity userEntity);

    List<CartItemResponseData> getUserCartResponseData(FireBaseUserData fireBaseUserData);

    @Transactional
    void patchCartQuantity(FireBaseUserData fireBaseUserData, Integer pid, Integer quantity);

    @Transactional
    void deleteCartItem(FireBaseUserData fireBaseUserData, Integer pid);

    @Transactional
    void emptyCart(UserEntity user);

    Integer getEffectiveStockByUserAndProduct(FireBaseUserData fireBaseUserData, Integer pid);
}
