package com.fsse2502.fsse_project.exception.transaction;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TransactionNotFoundException extends RuntimeException {
    public TransactionNotFoundException(Integer tid) {
        super("Transaction not found, T-ID: " + tid);
    }
}
