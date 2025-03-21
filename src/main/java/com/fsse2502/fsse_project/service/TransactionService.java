package com.fsse2502.fsse_project.service;

import com.fsse2502.fsse_project.data.transaction.domainObject.response.TransactionResponseData;
import com.fsse2502.fsse_project.data.user.domainObject.request.FireBaseUserData;

public interface TransactionService {
    TransactionResponseData createNewTransaction(FireBaseUserData fireBaseUserData);
}
