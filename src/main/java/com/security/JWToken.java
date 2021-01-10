package com.security;

import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

import io.jsonwebtoken.*;

import javax.servlet.http.HttpServletRequest;

@Component
public class JWToken {

    @Value("SOTIS")
    private String appname;

    @Value("secret")
    private String secret;

    @Value("10000")
    private int expires;


    public String generateJWToken(Authentication authentication) {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        System.out.println(userDetails.getUsername());

        return Jwts.builder()
                .setIssuer(appname)
                .setSubject((userDetails.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(genExpDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException e) {
            System.out.println("Invalid JWT token -> Message: " + e);
        } catch (ExpiredJwtException e) {
            System.out.println("Expired JWT token -> Message: " + e);
        } catch (UnsupportedJwtException e) {
            System.out.println("Unsupported JWT token -> Message: " + e);
        } catch (IllegalArgumentException e) {
            System.out.println("JWT claims string is empty -> Message: " + e);
        }

        return false;
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    private Date genExpDate() {
        return new Date((new Date()).getTime() + expires * 1000);
    }


    private Claims getAllClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }


    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            claims.setIssuedAt(new Date());
            refreshedToken = Jwts.builder()
                    .setClaims(claims)
                    .setExpiration(genExpDate())
                    .signWith(SignatureAlgorithm.HS512, secret)
                    .compact();
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    public String getJwt(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.replace("Bearer ", "");
        }

        return null;
    }
}
