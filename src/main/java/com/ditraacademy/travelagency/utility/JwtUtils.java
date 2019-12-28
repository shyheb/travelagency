package com.ditraacademy.travelagency.utility;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtils {

    private final String JWT_SECRET = "DITRASECRET";

    public String generateToken(String email){
        String token = Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,JWT_SECRET)
                .compact();
        return token;
    }

    

}
