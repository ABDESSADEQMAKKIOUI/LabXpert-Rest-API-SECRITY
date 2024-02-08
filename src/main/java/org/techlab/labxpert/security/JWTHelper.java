package org.techlab.labxpert.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javax.crypto.Cipher.SECRET_KEY;
import static org.techlab.labxpert.security.JWTUtil.*;

@Component
public class JWTHelper {
    Algorithm algorithm = Algorithm.HMAC256(String.valueOf(SECRET_KEY));

    public String generateAccessToken(String email, List<String> roles){
        return JWT.create()
                .withSubject(email)
                .withExpiresAt(new Date(System.currentTimeMillis()+EXPIRE_ACCESS_TOKEN))
                .withIssuer(ISSUER)
                .withClaim("roles",roles)
                .sign(algorithm);
    }

    public String generateRefreshToken(String email){
        return  JWT.create().withSubject(email)
                .withExpiresAt(new Date(System.currentTimeMillis()+EXPIRE_REFRESH_TOKEN))
                .withIssuer(ISSUER)
                .sign(algorithm);

    }

    public String extractTokenFromHeaderIfExists(String authorizationHeader){
        if (authorizationHeader != null && authorizationHeader.startsWith(BEARER_PREFIX)){
            return authorizationHeader.substring(BEARER_PREFIX.length());
        }
        return null;
    }

    public Map<String,String> getTokentsMap(String jwtAccessToken , String jwtRefreshToken) {
        Map<String,String> idToken = new HashMap<>();
        idToken.put("accessToken" ,jwtAccessToken);
        idToken.put("refreshToken" ,jwtRefreshToken);
        return idToken;
    }
}
