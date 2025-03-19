package com.fsse2502.fsse_project.exception.product;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProductOutOfStockException extends RuntimeException{
    public ProductOutOfStockException(Integer pid){
        super ("Product out of stock. ProductID: " + pid);
    }

}
