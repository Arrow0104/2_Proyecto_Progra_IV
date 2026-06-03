package cr.ac.una.job.controllers;

import cr.ac.una.job.dtos.caracteristica.CaracteristicaResponse;
import cr.ac.una.job.dtos.caracteristica.CreateCaracteristicaRequest;
import cr.ac.una.job.services.CaracteristicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/caracteristicas")
public class CaracteristicaRestController {

    @Autowired private CaracteristicaService service;

    @GetMapping
    public ResponseEntity<List<CaracteristicaResponse>> listar() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/raices")
    public ResponseEntity<List<CaracteristicaResponse>> raices() {
        return ResponseEntity.ok(service.getRoots());
    }

    @GetMapping("/{id}/hijos")
    public ResponseEntity<List<CaracteristicaResponse>> hijos(@PathVariable Long id) {
        return ResponseEntity.ok(service.getChildren(id));
    }

    @PostMapping
    public ResponseEntity<CaracteristicaResponse> crear(@RequestBody CreateCaracteristicaRequest body) {
        return ResponseEntity.ok(service.create(body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}