package com.fsse2502.fsse_project.exception;

public class UserCartEmptyException extends RuntimeException {
    public UserCartEmptyException(Integer uid) {
        super("User's cart is empty: " + uid);
    }
}
