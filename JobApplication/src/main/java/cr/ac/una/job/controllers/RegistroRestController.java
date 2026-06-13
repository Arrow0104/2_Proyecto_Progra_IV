// JobApplication/.../controllers/RegistroRestController.java
package cr.ac.una.job.controllers;

import cr.ac.una.job.dtos.empresa.CreateEmpresaRequest;
import cr.ac.una.job.dtos.oferente.CreateOferenteRequest;
import cr.ac.una.job.dtos.usuario.CreateUsuarioRequest;
import cr.ac.una.job.models.EstadoUsuario;
import cr.ac.una.job.models.Rol;
import cr.ac.una.job.models.Usuario;
import cr.ac.una.job.models.Empresa;
import cr.ac.una.job.models.Oferente;
import cr.ac.una.job.repositories.IEmpresaRepository;
import cr.ac.una.job.repositories.IOferenteRepository;
import cr.ac.una.job.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/registro")
public class RegistroRestController {

    @Autowired private UsuarioService usuarioService;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private IEmpresaRepository empresaRepository;
    @Autowired private IOferenteRepository oferenteRepository;

    @PostMapping("/empresa")
    public ResponseEntity<?> registrarEmpresa(@RequestBody Map<String, String> body) {
        // Crear usuario
        var req = new CreateUsuarioRequest(
            body.get("correo"), body.get("identificacion"),
            body.get("password"), Rol.EMPRESA, EstadoUsuario.PENDIENTE
        );
        var usuarioResp = usuarioService.createUsuario(req);
        Usuario u = usuarioService.getDomainUsuarioById(usuarioResp.getIdUsuario());

        // Crear empresa vinculada
        Empresa e = new Empresa(null,
            body.getOrDefault("nombre", ""),
            body.getOrDefault("telefono", ""),
            body.getOrDefault("localizacion", null),
            body.getOrDefault("correoEmpresa", null),
            body.getOrDefault("descripcion", null),
            false, LocalDateTime.now(), u
        );
        empresaRepository.save(e);

        return ResponseEntity.ok(Map.of("mensaje", "Empresa registrada correctamente"));
    }

    @PostMapping("/oferente")
    public ResponseEntity<?> registrarOferente(@RequestBody Map<String, String> body) {
        var req = new CreateUsuarioRequest(
            body.get("correo"), body.get("identificacion"),
            body.get("password"), Rol.OFERENTE, EstadoUsuario.PENDIENTE
        );
        var usuarioResp = usuarioService.createUsuario(req);
        Usuario u = usuarioService.getDomainUsuarioById(usuarioResp.getIdUsuario());

        Oferente o = new Oferente(null,
            body.getOrDefault("nombre", ""),
            body.getOrDefault("apellido", ""),
            body.getOrDefault("nacionalidad", null),
            body.getOrDefault("telefono", null),
            body.getOrDefault("correoOferente", null),
            body.getOrDefault("residencia", ""),
            null, false, LocalDateTime.now(), u
        );
        oferenteRepository.save(o);

        return ResponseEntity.ok(Map.of("mensaje", "Oferente registrado correctamente"));
    }
}