package cr.ac.una.job.config;

import cr.ac.una.job.auth.JwtAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // Sin CSRF porque usamos JWT (stateless)
            .csrf(AbstractHttpConfigurer::disable)

            // Sin sesión HTTP — todo por token
            .sessionManagement(sm ->
                sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

            .authorizeHttpRequests(auth -> auth

                // ── Públicos (sin token) ────────────────────────────────────
                .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/registro/**").permitAll()
                .requestMatchers(HttpMethod.GET,  "/api/public/**").permitAll()

                // Recursos estáticos del frontend (build de Vite)
                .requestMatchers("/", "/index.html", "/assets/**", "/favicon.ico").permitAll()

                // ── Admin ───────────────────────────────────────────────────
                .requestMatchers("/api/admin/**").hasRole("ADMIN")

                // ── Empresa ─────────────────────────────────────────────────
                .requestMatchers("/api/empresa/**").hasRole("EMPRESA")

                // ── Oferente ────────────────────────────────────────────────
                .requestMatchers("/api/oferente/**").hasRole("OFERENTE")

                .requestMatchers(HttpMethod.GET, "/api/caracteristicas/**").authenticated()
                // Cualquier otra ruta requiere autenticación
                .anyRequest().authenticated()
            )

            // Inyectar el filtro JWT antes del filtro de usuario/contraseña estándar
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /** BCrypt para hashear contraseñas */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}