package com.fsse2502.fsse_project.repository;

import com.fsse2502.fsse_project.data.cartItem.entity.CartItemEntity;
import com.fsse2502.fsse_project.data.product.entity.ProductEntity;
import com.fsse2502.fsse_project.data.user.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository  extends CrudRepository<CartItemEntity, Integer> {
    List<CartItemEntity> findByUser(UserEntity userEntity);

    Optional<CartItemEntity> findByUserAndProduct(UserEntity user, ProductEntity product);
    Integer deleteByUser_EmailAndProduct_pid(String userEmail, Integer pid);

    void deleteByUser(UserEntity user);
}
