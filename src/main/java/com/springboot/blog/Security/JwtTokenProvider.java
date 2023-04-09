package com.springboot.blog.Security;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider
{

@Value("${app.jwt-secret}")
    private String jwtSecret;
@Value("${app-jwt-expiration-milliseconds}")
    private String jwtExpirationDate;

//generating JWT Token
    public String generateToken(Authentication authentication)
    {
        String username=authentication.getName();
        Date currentDate=new Date();
        Date expireDate=new Date(currentDate.getTime()+jwtExpirationDate);
        return "Hiii";
    }
}
