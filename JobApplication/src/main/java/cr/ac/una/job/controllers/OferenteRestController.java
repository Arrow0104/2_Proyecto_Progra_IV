package cr.ac.una.job.controllers;

import cr.ac.una.job.dtos.oferente.OferenteResponse;
import cr.ac.una.job.dtos.oferente.UpdateOferenteRequest;
import cr.ac.una.job.dtos.puesto.PuestoResponse;
import cr.ac.una.job.models.Oferente;
import cr.ac.una.job.models.OferenteCaracteristica;
import cr.ac.una.job.models.Caracteristica;
import cr.ac.una.job.repositories.IOferenteCaracteristicaRepository;
import cr.ac.una.job.repositories.ICaracteristicaRepository;
import cr.ac.una.job.services.OferenteService;
import cr.ac.una.job.services.PuestoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/oferente")
public class OferenteRestController {

    @Autowired private OferenteService oferenteService;
    @Autowired private PuestoService puestoService;
    @Autowired private IOferenteCaracteristicaRepository ocRepo;
    @Autowired private ICaracteristicaRepository carRepo;

    // ── Perfil ────────────────────────────────────────────────────────────────

    @GetMapping("/perfil")
    public ResponseEntity<OferenteResponse> perfil(HttpServletRequest req) {
        Long uid = (Long) req.getAttribute("usuarioId");
        Oferente o = oferenteService.getDomainOferenteByUsuario(uid);
        return ResponseEntity.ok(oferenteService.getOferenteById(o.getIdOferente()));
    }

    @PutMapping("/perfil")
    public ResponseEntity<OferenteResponse> actualizar(
            HttpServletRequest req,
            @RequestBody UpdateOferenteRequest body) {
        Long uid = (Long) req.getAttribute("usuarioId");
        Oferente o = oferenteService.getDomainOferenteByUsuario(uid);
        return ResponseEntity.ok(oferenteService.updateOferente(o.getIdOferente(), body));
    }

    // ── Habilidades (características del oferente) ────────────────────────────

    @GetMapping("/habilidades")
    public ResponseEntity<?> misHabilidades(HttpServletRequest req) {
        Long uid = (Long) req.getAttribute("usuarioId");
        Oferente o = oferenteService.getDomainOferenteByUsuario(uid);

        List<Map<String, Object>> result = ocRepo.findByOferenteIdOferente(o.getIdOferente())
            .stream()
            .map(oc -> {
                Map<String, Object> m = new HashMap<>();
                m.put("idCaracteristica", oc.getCaracteristica().getIdCaracteristica());
                m.put("nombre",           oc.getCaracteristica().getNombre());
                m.put("nivel",            oc.getNivel());
                return m;
            })
            .collect(java.util.stream.Collectors.toList());

        return ResponseEntity.ok(result);
    }

    @PostMapping("/habilidades")
    public ResponseEntity<?> agregarHabilidad(
            HttpServletRequest req,
            @RequestBody Map<String, Object> body) {
        Long uid = (Long) req.getAttribute("usuarioId");
        Oferente o = oferenteService.getDomainOferenteByUsuario(uid);
        Long carId = Long.valueOf(body.get("idCaracteristica").toString());
        int nivel  = Integer.parseInt(body.get("nivel").toString());
        Caracteristica car = carRepo.findById(carId)
                .orElseThrow(() -> new RuntimeException("Característica no encontrada"));
        ocRepo.save(new OferenteCaracteristica(o, car, nivel));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/habilidades/{idCaracteristica}")
    @org.springframework.transaction.annotation.Transactional
    public ResponseEntity<Void> eliminarHabilidad(
            HttpServletRequest req,
            @PathVariable Long idCaracteristica) {
        Long uid = (Long) req.getAttribute("usuarioId");
        Oferente o = oferenteService.getDomainOferenteByUsuario(uid);
        ocRepo.deleteByOferenteIdOferenteAndCaracteristicaIdCaracteristica(
                o.getIdOferente(), idCaracteristica);
        return ResponseEntity.noContent().build();
    }

    // ── Puestos disponibles ───────────────────────────────────────────────────

    @GetMapping("/puestos")
    public ResponseEntity<List<PuestoResponse>> puestosDisponibles(
            @RequestParam(required = false) String titulo) {
        if (titulo != null && !titulo.isBlank()) {
            return ResponseEntity.ok(puestoService.searchByTitulo(titulo));
        }
        return ResponseEntity.ok(puestoService.getAllPuestos());
    }
}