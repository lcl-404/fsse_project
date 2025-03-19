package com.fsse2502.fsse_project.data.user.domainObject.request;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public class FireBaseUserData {

    private String firebaseUid;
    private String email;

    public FireBaseUserData(JwtAuthenticationToken jwtToken) {
        this.firebaseUid = (String) jwtToken.getTokenAttributes().get("user_id");
        this.email = (String) jwtToken.getTokenAttributes().get("email");
    }

    public String getFirebaseUid() {
        return firebaseUid;
    }

    public void setFirebaseUid(String firebaseUid) {
        this.firebaseUid = firebaseUid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}


