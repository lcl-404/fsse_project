package com.fsse2502.fsse_project.data.user.dto.response;

import com.fsse2502.fsse_project.data.user.domainObject.response.UserResponseData;
import com.fsse2502.fsse_project.data.user.entity.UserEntity;

public class UserResponseDto {
    private Integer uid;

    private String email;

    private String firebaseUid;

    public UserResponseDto(UserResponseData responseData) {
        this.uid = responseData.getUid();
        this.email = responseData.getEmail();
        this.firebaseUid = responseData.getFirebaseUid();
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirebaseUid() {
        return firebaseUid;
    }

    public void setFirebaseUid(String firebaseUid) {
        this.firebaseUid = firebaseUid;
    }
}
