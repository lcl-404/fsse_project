package com.fsse2502.fsse_project.controller;

import com.fsse2502.fsse_project.data.cartItem.domainObject.response.CartItemResponseData;
import com.fsse2502.fsse_project.data.cartItem.dto.response.GetUserCartResponseDto;
import com.fsse2502.fsse_project.data.user.domainObject.request.FireBaseUserData;
import com.fsse2502.fsse_project.service.CartItemService;
import com.fsse2502.fsse_project.util.JwtUtil;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartItemController {

    private final CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @PutMapping("/{pid}/{quantity}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putCartItem (JwtAuthenticationToken token,
                             @PathVariable Integer pid,
                             @Positive @PathVariable Integer quantity){
        FireBaseUserData fireBaseUserData = JwtUtil.toFirebaseUserData(token);

        cartItemService.putCartItem(fireBaseUserData, pid, quantity);
    }

    @GetMapping()
    public List<GetUserCartResponseDto> getUserCart(JwtAuthenticationToken token){
        FireBaseUserData fireBaseUserData = JwtUtil.toFirebaseUserData(token);
        List<CartItemResponseData> cartItemResponseData = cartItemService.getUserCart(fireBaseUserData);
        List<GetUserCartResponseDto> userCartResponseDtoList = new ArrayList<>();
        for (CartItemResponseData responseData : cartItemResponseData) {
            userCartResponseDtoList.add(new GetUserCartResponseDto(responseData));
        }

    return userCartResponseDtoList;
    }

    @PatchMapping("/{pid}/{quantity}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCartQuantity (JwtAuthenticationToken token, @PathVariable Integer pid, @PathVariable @Positive Integer quantity){
        FireBaseUserData fireBaseUserData = JwtUtil.toFirebaseUserData(token);
        cartItemService.updateCartQuantity(fireBaseUserData, pid, quantity);
    }

    @DeleteMapping("/{pid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCartItem(JwtAuthenticationToken token, @PathVariable Integer pid){
        FireBaseUserData fireBaseUserData = JwtUtil.toFirebaseUserData(token);
        cartItemService.deleteCartItem(fireBaseUserData, pid);
    }
}
