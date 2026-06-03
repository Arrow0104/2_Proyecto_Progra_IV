package cr.ac.una.job.controllers;

import cr.ac.una.job.dtos.puesto.PuestoResponse;
import cr.ac.una.job.services.PuestoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/puestos")
public class PuestoRestController {

    @Autowired private PuestoService puestoService;

    /** Detalle de un puesto por id — accesible para cualquier usuario autenticado */
    @GetMapping("/{id}")
    public ResponseEntity<PuestoResponse> detalle(@PathVariable Long id) {
        return ResponseEntity.ok(puestoService.getPuestoById(id));
    }
}