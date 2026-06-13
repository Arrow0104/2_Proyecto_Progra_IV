package cr.ac.una.job.controllers;

import cr.ac.una.job.dtos.puesto.PuestoResponse;
import cr.ac.una.job.services.PuestoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public")
public class PublicController {

    @Autowired private PuestoService puestoService;


    @GetMapping("/puestos")
    public ResponseEntity<List<PuestoResponse>> listar(
            @RequestParam(required = false) String titulo) {
        if (titulo != null && !titulo.isBlank()) {
            return ResponseEntity.ok(puestoService.searchPuestosPublicos(titulo));
        }
        return ResponseEntity.ok(puestoService.getPuestosPublicos());
    }


    @GetMapping("/puestos/{id}")
    public ResponseEntity<PuestoResponse> detalle(@PathVariable Long id) {
        return ResponseEntity.ok(puestoService.getPuestoById(id));
    }
}