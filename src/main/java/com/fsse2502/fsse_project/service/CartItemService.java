package com.fsse2502.fsse_project.service;

import com.fsse2502.fsse_project.data.cartItem.domainObject.response.CartItemResponseData;
import com.fsse2502.fsse_project.data.user.domainObject.request.FireBaseUserData;
import jakarta.transaction.Transactional;

import java.util.List;

public interface CartItemService {
    void putCartItem(FireBaseUserData fireBaseUserData, Integer pid, Integer quantity);

    List<CartItemResponseData> getUserCart(FireBaseUserData fireBaseUserData);

    void updateCartQuantity(FireBaseUserData fireBaseUserData, Integer pid, Integer quantity);

    @Transactional
    void deleteCartItem(FireBaseUserData fireBaseUserData, Integer pid);
}
