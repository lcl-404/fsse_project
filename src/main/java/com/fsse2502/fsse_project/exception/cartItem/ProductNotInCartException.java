package com.fsse2502.fsse_project.exception.cartItem;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotInCartException extends RuntimeException{
    public ProductNotInCartException(Integer pid){
        super ("Product not found in cart. ProductID: " + pid);
    }
}

