package cr.ac.una.job.auth;

/**
 * ⚠️  DEPRECATED - P2
 *
 * Este interceptor de sesión HTTP fue usado en el Proyecto 1 (Thymeleaf + HttpSession).
 * En el Proyecto 2 la autenticación se maneja con JWT mediante {@link JwtAuthFilter}
 * y Spring Security ({@code SecurityConfig}).
 *
 * Se conserva el archivo solo como referencia. NO está registrado en WebConfig.
 */
@Deprecated
public class AuthInterceptor {
    // Vacío intencionalmente — reemplazado por JwtAuthFilter
}