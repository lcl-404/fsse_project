package com.fsse2502.fsse_project.repository;

import com.fsse2502.fsse_project.data.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {
    @Query("SELECT p FROM ProductEntity p WHERE p.category = :category")
    List<ProductEntity> findByCategory(@Param("category") String category);
    // Optional<ProductEntity> findByPid (Integer pid);
}
