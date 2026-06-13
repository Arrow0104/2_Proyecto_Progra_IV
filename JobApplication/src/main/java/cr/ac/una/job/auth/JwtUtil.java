package cr.ac.una.job.auth;

import cr.ac.una.job.models.Rol;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration-ms:86400000}")
    private long expirationMs;

    private SecretKey key() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }


    public String generateToken(String email, Rol rol, Long usuarioId) {
        return Jwts.builder()
                .subject(email)
                .claim("rol",        rol.name())
                .claim("usuarioId",  usuarioId)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(key())
                .compact();
    }


    public String getEmail(String token) {
        return claims(token).getSubject();
    }

    /** Extrae el rol del token */
    public Rol getRol(String token) {
        String rolStr = claims(token).get("rol", String.class);
        return Rol.valueOf(rolStr);
    }


    public Long getUsuarioId(String token) {
        return claims(token).get("usuarioId", Long.class);
    }


    public boolean isValid(String token) {
        try {
            claims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    private Claims claims(String token) {
        return Jwts.parser()
                .verifyWith(key())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}