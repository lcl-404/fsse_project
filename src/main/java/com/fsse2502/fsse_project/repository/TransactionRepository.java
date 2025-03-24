package com.fsse2502.fsse_project.repository;

import com.fsse2502.fsse_project.data.transaction.entity.TransactionEntity;
import com.fsse2502.fsse_project.data.user.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface TransactionRepository extends CrudRepository <TransactionEntity, Integer> {
    Optional<TransactionEntity> findByTid(Integer tid);
}
