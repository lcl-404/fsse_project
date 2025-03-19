package com.fsse2502.fsse_project.data.user.entity;

import com.fsse2502.fsse_project.data.user.domainObject.request.FireBaseUserData;
import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uid;

    @Column(nullable = false, unique = true)
    private String email;

    @Column (nullable = false, unique = true)
    private String firebaseUid;

    public UserEntity() {
    }

    public UserEntity(FireBaseUserData fireBaseUserData){
        this.email = fireBaseUserData.getEmail();
        this.firebaseUid = fireBaseUserData.getFirebaseUid();
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
