package com.fsse2502.fsse_project.repository;

import com.fsse2502.fsse_project.data.product.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {
   // Optional<ProductEntity> findByPid (Integer pid);
}
