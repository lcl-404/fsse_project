package com.fsse2502.fsse_project.service;

import com.fsse2502.fsse_project.data.user.domainObject.request.FireBaseUserData;
import com.fsse2502.fsse_project.data.user.entity.UserEntity;

public interface UserService {
    UserEntity getEntityByEmail (FireBaseUserData fireBaseUserData);
}
