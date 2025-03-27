package com.fsse2502.fsse_project.repository;

import com.fsse2502.fsse_project.data.user.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {
 //   Optional<UserEntity> findByEmail (String email);
    @Query ("SELECT u FROM UserEntity u WHERE u.email = :email")
    Optional<UserEntity> findByEmail (String email);
}
