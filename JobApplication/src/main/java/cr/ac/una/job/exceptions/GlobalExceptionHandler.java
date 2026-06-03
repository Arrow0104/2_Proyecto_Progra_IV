package cr.ac.una.job.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * GlobalExceptionHandler P2
 * Reemplaza el ViewExceptionHandler del P1 (que devolvía páginas HTML de error).
 * En el P2 todas las respuestas de error son JSON para el frontend React.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmpresaNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleEmpresa(EmpresaNotFoundException ex) {
        return error(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(OferenteNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleOferente(OferenteNotFoundException ex) {
        return error(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(PuestoNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handlePuesto(PuestoNotFoundException ex) {
        return error(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(UsuarioNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleUsuario(UsuarioNotFoundException ex) {
        return error(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneric(Exception ex) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno: " + ex.getMessage());
    }

    private ResponseEntity<Map<String, Object>> error(HttpStatus status, String mensaje) {
        return ResponseEntity.status(status).body(Map.of(
                "timestamp", LocalDateTime.now().toString(),
                "status",    status.value(),
                "error",     status.getReasonPhrase(),
                "mensaje",   mensaje
        ));
    }
}