package cr.ac.una.job.controllers;

import cr.ac.una.job.auth.JwtUtil;
import cr.ac.una.job.dtos.auth.LoginRequest;
import cr.ac.una.job.dtos.auth.LoginResponse;
import cr.ac.una.job.models.Usuario;
import cr.ac.una.job.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {

    @Autowired private UsuarioService usuarioService;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        return usuarioService.findByCorreo(request.getCorreo())
                .filter(u -> u.isActive())
                .filter(u -> passwordEncoder.matches(request.getPassword(), u.getPassword()))
                .map(u -> ResponseEntity.ok(
                        new LoginResponse(
                                jwtUtil.generateToken(u.getCorreo(), u.getRol(), u.getIdUsuario()),
                                u.getRol().name(),
                                u.getIdUsuario()
                        )
                ))
                .orElse(ResponseEntity.status(401).build());
    }
}