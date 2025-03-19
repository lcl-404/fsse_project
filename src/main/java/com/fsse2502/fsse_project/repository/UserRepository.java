package com.fsse2502.fsse_project.repository;

import com.fsse2502.fsse_project.data.user.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    Optional<UserEntity> findByEmail (String email);
}
