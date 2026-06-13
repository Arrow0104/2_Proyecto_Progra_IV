package cr.ac.una.job.services;

import cr.ac.una.job.dtos.oferente.CreateOferenteRequest;
import cr.ac.una.job.dtos.oferente.OferenteResponse;
import cr.ac.una.job.dtos.oferente.UpdateOferenteRequest;
import cr.ac.una.job.exceptions.OferenteNotFoundException;
import cr.ac.una.job.models.EstadoUsuario;
import cr.ac.una.job.models.Oferente;
import cr.ac.una.job.models.Usuario;
import cr.ac.una.job.repositories.IOferenteRepository;
import cr.ac.una.job.repositories.IUsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class OferenteService {
    private static final Logger log = LoggerFactory.getLogger(OferenteService.class);

    private final IOferenteRepository repository;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    public OferenteService(IOferenteRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<OferenteResponse> getAllOferentes() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(java.util.stream.Collectors.toList());
    }

    @Transactional(readOnly = true)
    public OferenteResponse getOferenteById(Long id) {
        return toResponse(repository.findById(id).orElseThrow(() -> new OferenteNotFoundException(id)));
    }

    @Transactional(readOnly = true)
    public Oferente getDomainOferenteById(Long id) {
        return repository.findById(id).orElseThrow(() -> new OferenteNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public Oferente getDomainOferenteByUsuario(Long idUsuario) {
        return repository.findFirstByUsuarioIdUsuario(idUsuario)
                .orElseThrow(() -> new OferenteNotFoundException(0L));
    }

    @Transactional(readOnly = true)
    public List<OferenteResponse> searchByNombre(String nombre) {
        if (nombre == null || nombre.trim().length() < 2) return List.of();
        return repository.findByActiveTrueAndNombreContaining(nombre).stream().map(this::toResponse).toList();
    }

    public OferenteResponse createOferente(CreateOferenteRequest request) {
        log.info("Creando oferente nombre={}", request.getNombre());
        Oferente o = new Oferente(
                null,
                request.getNombre().trim(),
                request.getApellido().trim(),
                trim(request.getNacionalidad()),
                trim(request.getTelefono()),
                trim(request.getCorreoOferente()),
                request.getResidencia().trim(),
                trim(request.getCvPath()),
                false,              // <-- era true, ahora false
                LocalDateTime.now(),
                null
        );
        return toResponse(repository.save(o));
    }

    public OferenteResponse updateOferente(Long id, UpdateOferenteRequest request) {
        log.info("Actualizando oferente id={}", id);
        Oferente o = repository.findById(id).orElseThrow(() -> new OferenteNotFoundException(id));

        o.setNombre(request.getNombre().trim());
        o.setApellido(request.getApellido().trim());
        o.setNacionalidad(trim(request.getNacionalidad()));
        o.setTelefono(trim(request.getTelefono()));
        o.setCorreoOferente(trim(request.getCorreoOferente()));
        o.setResidencia(request.getResidencia().trim());
        o.setCvPath(trim(request.getCvPath()));

        return toResponse(repository.save(o));
    }

    public void changeActiveStatus(Long id, boolean value) {
        Oferente o = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Oferente no encontrado"));
        o.setActive(value);
        repository.save(o);

        // También actualizar el usuario vinculado
        if (o.getUsuario() != null) {
            Usuario u = o.getUsuario();
            u.setActive(value);
            u.setEstado(value ? EstadoUsuario.ACTIVO : EstadoUsuario.INACTIVO);
            usuarioRepository.save(u);
        }
    }

    public void deleteLogical(Long id) {
        changeActiveStatus(id, false);
    }

    public UpdateOferenteRequest buildUpdateRequest(Long id) {
        Oferente o = repository.findById(id).orElseThrow(() -> new OferenteNotFoundException(id));
        return new UpdateOferenteRequest(
                o.getNombre(), o.getApellido(), o.getNacionalidad(),
                o.getTelefono(), o.getCorreoOferente(),
                o.getResidencia(), o.getCvPath()
        );
    }

    private OferenteResponse toResponse(Oferente o) {
        return new OferenteResponse(
                o.getIdOferente(), o.getNombre(), o.getApellido(),
                o.getNacionalidad(), o.getTelefono(), o.getCorreoOferente(),
                o.getResidencia(), o.getCvPath(),
                o.isActive(), o.getCreatedAt()
        );
    }

    private String trim(String s) { return (s == null) ? null : s.trim(); }
}