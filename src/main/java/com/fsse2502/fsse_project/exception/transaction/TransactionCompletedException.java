package com.fsse2502.fsse_project.exception.transaction;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TransactionCompletedException extends RuntimeException {
    public TransactionCompletedException(Integer tid) {
        super("Transaction has already been completed, T-ID: " + tid);
    }
}
