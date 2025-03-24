package com.fsse2502.fsse_project.exception.cartItem;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserCartEmptyException extends RuntimeException {
    public UserCartEmptyException(Integer uid) {
        super("User's cart is empty: " + uid);
    }
}
