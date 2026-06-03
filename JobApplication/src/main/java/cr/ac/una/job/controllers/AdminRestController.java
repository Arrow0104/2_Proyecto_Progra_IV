package cr.ac.una.job.controllers;

import cr.ac.una.job.dtos.empresa.EmpresaResponse;
import cr.ac.una.job.dtos.oferente.OferenteResponse;
import cr.ac.una.job.dtos.usuario.UsuarioResponse;
import cr.ac.una.job.models.EstadoUsuario;
import cr.ac.una.job.models.Usuario;
import cr.ac.una.job.services.EmpresaService;
import cr.ac.una.job.services.OferenteService;
import cr.ac.una.job.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminRestController {

    @Autowired private UsuarioService usuarioService;
    @Autowired private EmpresaService empresaService;
    @Autowired private OferenteService oferenteService;

    // ── Usuarios ──────────────────────────────────────────────────────────────

    @GetMapping("/usuarios")
    public ResponseEntity<List<UsuarioResponse>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.getAllUsuarios());
    }

    @PutMapping("/usuarios/{id}/estado")
    public ResponseEntity<Void> cambiarEstado(
            @PathVariable Long id,
            @RequestParam EstadoUsuario estado) {
        Usuario u = usuarioService.getDomainUsuarioById(id);
        u.setEstado(estado);
        // Activar/desactivar el active según el estado
        boolean activo = estado == EstadoUsuario.ACTIVO;
        usuarioService.changeActiveStatus(id, activo);
        return ResponseEntity.noContent().build();
    }

    // ── Empresas ──────────────────────────────────────────────────────────────

    @GetMapping("/empresas")
    public ResponseEntity<List<EmpresaResponse>> listarEmpresas() {
        return ResponseEntity.ok(empresaService.getAllEmpresas());
    }

    @PutMapping("/empresas/{id}/activar")
    public ResponseEntity<Void> activarEmpresa(@PathVariable Long id) {
        empresaService.changeActiveStatus(id, true);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/empresas/{id}/desactivar")
    public ResponseEntity<Void> desactivarEmpresa(@PathVariable Long id) {
        empresaService.changeActiveStatus(id, false);
        return ResponseEntity.noContent().build();
    }

    // ── Oferentes ─────────────────────────────────────────────────────────────

    @GetMapping("/oferentes")
    public ResponseEntity<List<OferenteResponse>> listarOferentes() {
        return ResponseEntity.ok(oferenteService.getAllOferentes());
    }

    @PutMapping("/oferentes/{id}/activar")
    public ResponseEntity<Void> activarOferente(@PathVariable Long id) {
        oferenteService.changeActiveStatus(id, true);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/oferentes/{id}/desactivar")
    public ResponseEntity<Void> desactivarOferente(@PathVariable Long id) {
        oferenteService.changeActiveStatus(id, false);
        return ResponseEntity.noContent().build();
    }
}