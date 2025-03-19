package com.fsse2502.fsse_project.service.impl;

import com.fsse2502.fsse_project.data.user.domainObject.request.FireBaseUserData;
import com.fsse2502.fsse_project.data.user.entity.UserEntity;
import com.fsse2502.fsse_project.repository.UserRepository;
import com.fsse2502.fsse_project.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity getEntityByEmail(FireBaseUserData fireBaseUserData) {
//        Optional<UserEntity> optionalUserEntity = userRepository.findByEmail(fireBaseUserData.getEmail());
//        if (optionalUserEntity.isPresent()) {
//            return optionalUserEntity.get();
//        }
//        UserEntity userEntity = new UserEntity(fireBaseUserData);
//        return userRepository.save(userEntity);

        return userRepository.findByEmail((fireBaseUserData.getEmail())).orElseGet(
            ()-> userRepository.save(new UserEntity(fireBaseUserData))
        );
    }
}
