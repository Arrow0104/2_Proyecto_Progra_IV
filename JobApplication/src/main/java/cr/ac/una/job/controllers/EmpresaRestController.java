package cr.ac.una.job.controllers;

import cr.ac.una.job.dtos.empresa.EmpresaResponse;
import cr.ac.una.job.dtos.empresa.UpdateEmpresaRequest;
import cr.ac.una.job.dtos.puesto.CreatePuestoRequest;
import cr.ac.una.job.dtos.puesto.PuestoResponse;
import cr.ac.una.job.dtos.puesto.UpdatePuestoRequest;
import cr.ac.una.job.models.Empresa;
import cr.ac.una.job.services.EmpresaService;
import cr.ac.una.job.services.PuestoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import cr.ac.una.job.dtos.oferente.OferenteResponse;
import cr.ac.una.job.services.OferenteService;


import java.util.List;

@RestController
@RequestMapping("/api/empresa")
public class EmpresaRestController {

    @Autowired private EmpresaService empresaService;
    @Autowired private PuestoService puestoService;

    // ── Perfil ────────────────────────────────────────────────────────────────

    @GetMapping("/perfil")
    public ResponseEntity<EmpresaResponse> perfil(HttpServletRequest req) {
        Long uid = (Long) req.getAttribute("usuarioId");
        Empresa e = empresaService.getDomainEmpresaByUsuario(uid);
        return ResponseEntity.ok(empresaService.getEmpresaById(e.getIdEmpresa()));
    }

    @PutMapping("/perfil")
    public ResponseEntity<EmpresaResponse> actualizarPerfil(
            HttpServletRequest req,
            @RequestBody UpdateEmpresaRequest body) {
        Long uid = (Long) req.getAttribute("usuarioId");
        Empresa e = empresaService.getDomainEmpresaByUsuario(uid);
        return ResponseEntity.ok(empresaService.updateEmpresa(e.getIdEmpresa(), body));
    }

    // ── Puestos ───────────────────────────────────────────────────────────────

    @GetMapping("/puestos")
    public ResponseEntity<List<PuestoResponse>> misPuestos(HttpServletRequest req) {
        Long uid = (Long) req.getAttribute("usuarioId");
        Empresa e = empresaService.getDomainEmpresaByUsuario(uid);
        return ResponseEntity.ok(puestoService.getPuestosByEmpresa(e.getIdEmpresa()));
    }

    @PostMapping("/puestos")
    public ResponseEntity<PuestoResponse> crearPuesto(
            HttpServletRequest req,
            @RequestBody CreatePuestoRequest body) {
        Long uid = (Long) req.getAttribute("usuarioId");
        Empresa e = empresaService.getDomainEmpresaByUsuario(uid);
        body.setIdEmpresa(e.getIdEmpresa());
        return ResponseEntity.ok(puestoService.createPuesto(body));
    }

    @PutMapping("/puestos/{id}")
    public ResponseEntity<PuestoResponse> actualizarPuesto(
            @PathVariable Long id,
            @RequestBody UpdatePuestoRequest body) {
        return ResponseEntity.ok(puestoService.updatePuesto(id, body));
    }

    @DeleteMapping("/puestos/{id}")
    public ResponseEntity<Void> eliminarPuesto(@PathVariable Long id) {
        puestoService.deleteLogical(id);
        return ResponseEntity.noContent().build();
    }
    @Autowired private OferenteService oferenteService;

    @GetMapping("/candidatos")
    public ResponseEntity<List<OferenteResponse>> buscarCandidatos() {
        return ResponseEntity.ok(oferenteService.getAllOferentes());
    }
}