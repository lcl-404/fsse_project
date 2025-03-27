package com.fsse2502.fsse_project.repository;

import com.fsse2502.fsse_project.data.transaction.entity.TransactionEntity;
import com.fsse2502.fsse_project.data.user.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface TransactionRepository extends CrudRepository <TransactionEntity, Integer> {
    //Optional<TransactionEntity> findByTid(Integer tid);
    @Query ("Select t from TransactionEntity t where t.tid = :tid")
    Optional<TransactionEntity> findByTid (@Param("tid") Integer tid);
}
