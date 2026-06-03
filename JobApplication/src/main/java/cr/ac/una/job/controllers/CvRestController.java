package cr.ac.una.job.controllers;

import cr.ac.una.job.models.Oferente;
import cr.ac.una.job.services.OferenteService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/oferente/cv")
public class CvRestController {

    @Autowired private OferenteService oferenteService;

    private static final Path CV_DIR = Paths.get("uploads/cv/");

    @PostMapping
    public ResponseEntity<Map<String, String>> subirCv(
            HttpServletRequest req,
            @RequestParam("file") MultipartFile file) throws IOException {

        Long uid = (Long) req.getAttribute("usuarioId");
        Oferente o = oferenteService.getDomainOferenteByUsuario(uid);

        Files.createDirectories(CV_DIR);
        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path dest = CV_DIR.resolve(filename);
        Files.copy(file.getInputStream(), dest, StandardCopyOption.REPLACE_EXISTING);

        String cvPath = "/cv/" + filename;

        // Actualizar el cvPath en el oferente
        var update = oferenteService.buildUpdateRequest(o.getIdOferente());
        update.setCvPath(cvPath);
        oferenteService.updateOferente(o.getIdOferente(), update);

        return ResponseEntity.ok(Map.of("cvPath", cvPath));
    }
}