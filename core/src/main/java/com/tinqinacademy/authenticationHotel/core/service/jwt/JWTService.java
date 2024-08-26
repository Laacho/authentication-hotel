package com.tinqinacademy.authenticationHotel.core.service.jwt;

import com.tinqinacademy.authenticationHotel.api.models.exceptions.customException.InvalidJWTException;
import com.tinqinacademy.authenticationHotel.persistence.entities.User;
import com.tinqinacademy.authenticationHotel.persistence.repository.BlacklistedCodesRepository;
import com.tinqinacademy.authenticationHotel.persistence.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.InvalidClaimException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.security.Key;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JWTService {
    private String SECRETE_KEY = "B4F4918CA0EEF0FC1BE5BAE6909D53AC9370174042166409B564FA859ED215FAF634AF3552986BEA9A6958E02D3478ACEB6E156527910611C8E24DC365DD7E367FDED56646F5B0F0B73F89DA018271CAD93C001D1A60B3F156931B64F3835593DA77D639AFE162329CB07CDF6B52932A921F106C64F3F26BE54C446FD52ADC25";
    private final UserRepository userRepository;
    private final BlacklistedCodesRepository blacklistedCodesRepository;
    public boolean validateJwt(String token) {
        try {
            return extractExpiration(token).after(new Date(System.currentTimeMillis()))
                   && blacklistedCodesRepository.findByCode(token).isEmpty();
        } catch (InvalidJWTException ex) {
            return false;
        }
    }
    private Date extractExpiration(String token) {
        Claims claims = extractAllClaims(token);
        return claims.getExpiration();
    }
    public Optional<User> validateJWTAndReturnUser(String jwt) {
        String id;
        String role;
        try {
            id=extractId(jwt);
            role=extractRole(jwt);
        }
        catch (InvalidClaimException e) {
            return Optional.empty();
        }

        Optional<User> user = userRepository.findById(UUID.fromString(id));
        if(user.isEmpty() ||!user.get().getRole().toString().equals(role)){
            return Optional.empty();
        }
        return user;
    }


    public String extractRole(String token) {
        return extractClaim(token, claims -> claims.get("role",String.class));
    }
    public String extractId(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    private <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims=extractAllClaims(token);
        return claimsResolver.apply(claims);
    }




    private Claims  extractAllClaims(String token) {
        try {
        return Jwts.parserBuilder()
                .setSigningKey(getSingInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        } catch (Exception e) {
            throw new InvalidParameterException("invalid JWT token");
        }

    }


    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUserId().toString())
                .claim("role", user.getRole().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + (5 * 60 * 1000)))
                .signWith(getSingInKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    private Key getSingInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRETE_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
