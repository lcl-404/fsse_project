package com.fsse2502.fsse_project.exception.transaction;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TransactionNotProcessingException extends RuntimeException {
    public TransactionNotProcessingException(Integer tid) {
        super("Transaction has not been processed, T-ID: " + tid);
    }
}
