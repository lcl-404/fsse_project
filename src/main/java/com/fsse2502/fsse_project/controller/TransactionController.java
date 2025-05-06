package com.fsse2502.fsse_project.controller;

import com.fsse2502.fsse_project.data.transaction.dto.response.TransactionResponseDto;
import com.fsse2502.fsse_project.data.user.domainObject.request.FireBaseUserData;
import com.fsse2502.fsse_project.service.TransactionService;
import com.fsse2502.fsse_project.util.JwtUtil;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/prepare")
    public TransactionResponseDto createTransaction(JwtAuthenticationToken token){
        FireBaseUserData fireBaseUserData = JwtUtil.toFirebaseUserData(token);
        return new TransactionResponseDto(
                transactionService.createNewTransaction(fireBaseUserData)
        );
    }

    @GetMapping("/{tid}")
    public TransactionResponseDto getTransaction(JwtAuthenticationToken token, @PathVariable Integer tid){
        FireBaseUserData fireBaseUserData = JwtUtil.toFirebaseUserData(token);
        return new TransactionResponseDto(
                transactionService.getTransaction(fireBaseUserData, tid)
        );
    }

    @PatchMapping("/{tid}/payment")
    @ResponseStatus(HttpStatus.NO_CONTENT)

    public void patchTransactionStatus(JwtAuthenticationToken token, @PathVariable Integer tid){
        FireBaseUserData fireBaseUserData = JwtUtil.toFirebaseUserData(token);
        transactionService.updateTransactionStatus(fireBaseUserData, tid);
    }

    @PatchMapping("/{tid}/success")
    public TransactionResponseDto finishTransaction(JwtAuthenticationToken token, @PathVariable Integer tid){
        FireBaseUserData fireBaseUserData = JwtUtil.toFirebaseUserData(token);
        return new TransactionResponseDto(
                transactionService.finishTransaction(fireBaseUserData, tid)
        );
    }
}
