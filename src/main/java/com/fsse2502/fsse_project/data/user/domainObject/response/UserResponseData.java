package com.fsse2502.fsse_project.data.user.domainObject.response;

import com.fsse2502.fsse_project.data.user.entity.UserEntity;
import jakarta.persistence.Column;
import org.apache.catalina.User;

public class UserResponseData {
    private Integer uid;

    private String email;

    private String firebaseUid;

    public UserResponseData(UserEntity userEntity) {
        this.uid = userEntity.getUid();
        this.email = userEntity.getEmail();
        this.firebaseUid = userEntity.getFirebaseUid();
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
