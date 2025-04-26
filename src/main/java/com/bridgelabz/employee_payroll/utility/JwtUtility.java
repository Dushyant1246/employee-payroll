package com.bridgelabz.employee_payroll.utility;

import com.bridgelabz.employee_payroll.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.bridgelabz.employee_payroll.model.User;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtility {

    @Autowired
    UserRepository userRepository;

    private static final String SECRET_KEY="ouy78hyf7hu12vi76t8vc";
    private static final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // secure 256-bit key


    public String generateToken(String email){
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+5*60*1000))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

    }

    public String extractEmail(String token){
        try{
            System.out.println(token);
            Claims claims=Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            System.out.println("getting email =>"+claims);
            return claims.getSubject();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public boolean validateToken(String token,String userEmail){
        final String email=extractEmail(token);
        boolean isTokenPresent=true;
        User user= userRepository.findByEmail(email).orElse(null);

        if(user!=null && user.getToken() == null){
            isTokenPresent=false;
        }
        final boolean valid=Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody().getExpiration().before(new Date());
        return (email.equals(userEmail) && !valid && isTokenPresent);
    }
}