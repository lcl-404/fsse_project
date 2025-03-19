package com.fsse2502.fsse_project.util;

import com.fsse2502.fsse_project.data.user.domainObject.request.FireBaseUserData;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public class JwtUtil {
    public static FireBaseUserData toFirebaseUserData (JwtAuthenticationToken token){
        return new FireBaseUserData(token);
    }
}
