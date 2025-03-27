package com.fsse2502.fsse_project.repository;

import com.fsse2502.fsse_project.data.cartItem.entity.CartItemEntity;
import com.fsse2502.fsse_project.data.product.entity.ProductEntity;
import com.fsse2502.fsse_project.data.user.entity.UserEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository  extends CrudRepository<CartItemEntity, Integer> {
//    List<CartItemEntity> findByUser(UserEntity userEntity);

    @Query("SELECT c FROM CartItemEntity c WHERE c.user = :userEntity")
    List<CartItemEntity> findByUser(@Param("userEntity") UserEntity userEntity);

//    Optional<CartItemEntity> findByUserAndProduct(UserEntity user, ProductEntity product);
    @Query("SELECT c FROM CartItemEntity c WHERE c.user = :user AND c.product = :product")
    Optional<CartItemEntity> findByUserAndProduct(@Param("user") UserEntity user,
                                                  @Param("product") ProductEntity product);

//    Integer deleteByUser_EmailAndProduct_pid(String userEmail, Integer pid);
    @Modifying
    @Query("DELETE FROM CartItemEntity c WHERE c.user.email = :email AND c.product.pid = :pid")
    Integer deleteByUser_EmailAndProduct_pid(@Param("email") String email,
                                             @Param("pid") Integer pid);
//   void deleteByUser(UserEntity user);

    @Modifying
    @Query("DELETE FROM CartItemEntity c WHERE c.user = :user")
    void deleteByUser(@Param("user") UserEntity user);
}
