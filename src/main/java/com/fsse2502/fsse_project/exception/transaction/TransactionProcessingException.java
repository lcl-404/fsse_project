package com.fsse2502.fsse_project.exception.transaction;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TransactionProcessingException extends RuntimeException {
    public TransactionProcessingException(Integer tid) {
        super("Transaction is already processing, T-ID: " + tid);
    }
}
