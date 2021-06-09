package com.joebrooks.whoareyou.Common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {
    @Value("${jwt.secretKey}")
    private String secretKey;

    @PostConstruct
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String email){
        Date now = new Date();

        return Jwts.builder()
                .claim("email", email)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String getEmailFromClaims(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();

        Map<String, Object> map = (Map<String, Object>) claims;

        return  map.get("email").toString();
    }


    public boolean isValidate(String jwtToken){
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return true;
        } catch (Exception e){
            return false;
        }
    }

}
