package com.fsse2502.fsse_project.repository;

import com.fsse2502.fsse_project.data.transaction.entity.TransactionEntity;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository <TransactionEntity, Integer> {

}
