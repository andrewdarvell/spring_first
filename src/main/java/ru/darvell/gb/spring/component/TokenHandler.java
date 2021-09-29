package ru.darvell.gb.spring.component;

import com.google.common.io.BaseEncoding;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import ru.darvell.gb.spring.domain.ShopUser;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class TokenHandler {

    private final SecretKey secretKey;
    public static final String ROLE_KEY = "roles";

    public TokenHandler() {
        String jwtKey = "lniwerg34ScafdsfpoPiw3";
        byte[] decodedKey =
                java.util.Base64.getDecoder().decode(jwtKey);
        secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    }

    public Optional<Long> extractUserId(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            Claims body = claimsJws.getBody();
            return Optional
                    .ofNullable(body.getId())
                    .map(Long::valueOf);
        } catch (RuntimeException e) {
            return Optional.empty();
        }
    }

    public String generateAccessToken(ShopUser user, LocalDateTime expires) {
        return Jwts.builder()
                .setId(String.valueOf(user.getId()))
                .setExpiration(Date.from(expires.atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    Map<String, Object> convertAuthoririesToClaim(ShopUser user){
        Map<String, Object> map = new HashMap<>();
        map.put(ROLE_KEY, user.getRoles());
        return map;
    }
}
