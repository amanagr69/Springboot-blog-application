package com.springboot.blog.Security;

import com.springboot.blog.Exception.BlogAPIException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
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
        String jwtToken;
        jwtToken = Jwts.builder().setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(key())
                .compact();
        return jwtToken;
    }
    private Key key()
    {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }
    public String getUsername(String token)
    {
        Claims claims=Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJwt(token).getBody();
        String username;
        username = claims.getSubject();
        return username;
    }
    public Boolean validateToken(String token) throws BlogAPIException {
        try{
            Jwts.parserBuilder().setSigningKey(key()).build().parse(token);
            return true;
        }
        catch (MalformedJwtException e)
        {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Invalid jwt");
        } catch (ExpiredJwtException e)
        {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Jwt expired");
        }
        catch (UnsupportedJwtException e)
        {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Jwt unsupported");
        }
        catch (IllegalArgumentException e)
        {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Jwt string is empty");
        }

    }
}
