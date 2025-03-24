package com.fsse2502.fsse_project.service;

import com.fsse2502.fsse_project.data.transaction.domainObject.response.TransactionResponseData;
import com.fsse2502.fsse_project.data.user.domainObject.request.FireBaseUserData;
import jakarta.transaction.Transactional;

import java.util.List;

public interface TransactionService {
    TransactionResponseData createNewTransaction(FireBaseUserData fireBaseUserData);

    TransactionResponseData getTransaction(FireBaseUserData fireBaseUserData, Integer tid);

    void updateTransactionStatus(FireBaseUserData fireBaseUserData, Integer tid);

    @Transactional
    TransactionResponseData finishTransaction(FireBaseUserData fireBaseUserData, Integer tid);
}
