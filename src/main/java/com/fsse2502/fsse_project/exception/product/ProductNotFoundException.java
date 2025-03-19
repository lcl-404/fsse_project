package com.fsse2502.fsse_project.exception.product;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(Integer pid){
        super ("Product not found. ProductID: " + pid);
    }

}
