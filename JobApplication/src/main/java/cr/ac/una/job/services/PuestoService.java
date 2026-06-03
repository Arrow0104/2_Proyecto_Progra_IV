package cr.ac.una.job.services;

import cr.ac.una.job.dtos.puesto.CreatePuestoRequest;
import cr.ac.una.job.dtos.puesto.PuestoResponse;
import cr.ac.una.job.dtos.puesto.UpdatePuestoRequest;
import cr.ac.una.job.exceptions.PuestoNotFoundException;
import cr.ac.una.job.models.Empresa;
import cr.ac.una.job.models.Puesto;
import cr.ac.una.job.models.TipoPublicacion;
import cr.ac.una.job.repositories.IPuestoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class PuestoService {
    private static final Logger log = LoggerFactory.getLogger(PuestoService.class);

    private final IPuestoRepository repository;
    private final EmpresaService empresaService;

    public PuestoService(IPuestoRepository repository, EmpresaService empresaService) {
        this.repository = repository;
        this.empresaService = empresaService;
    }

    @Transactional(readOnly = true)
    public List<PuestoResponse> getAllPuestos() {
        return repository.findByActiveTrue().stream().map(this::toResponse).toList();
    }

    /** Solo puestos PÚBLICOS — para el endpoint sin autenticación */
    @Transactional(readOnly = true)
    public List<PuestoResponse> getPuestosPublicos() {
        return repository.findByActiveTrueAndTipoPublicacion(TipoPublicacion.PUBLICO)
                .stream().map(this::toResponse).toList();
    }

    /** Búsqueda pública por título */
    @Transactional(readOnly = true)
    public List<PuestoResponse> searchPuestosPublicos(String titulo) {
        if (titulo == null || titulo.trim().length() < 2) return getPuestosPublicos();
        return repository.findByActiveTrueAndTipoPublicacionAndTituloContaining(TipoPublicacion.PUBLICO, titulo)
                .stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public PuestoResponse getPuestoById(Long id) {
        return toResponse(repository.findById(id).orElseThrow(() -> new PuestoNotFoundException(id)));
    }

    @Transactional(readOnly = true)
    public Puesto getDomainPuestoById(Long id) {
        return repository.findById(id).orElseThrow(() -> new PuestoNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public List<PuestoResponse> searchByTitulo(String titulo) {
        if (titulo == null || titulo.trim().length() < 2) return List.of();
        return repository.findByActiveTrueAndTituloContaining(titulo).stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<PuestoResponse> getPuestosByEmpresa(Long empresaId) {
        return repository.findByActiveTrueAndEmpresaIdEmpresa(empresaId).stream().map(this::toResponse).toList();
    }

    public PuestoResponse createPuesto(CreatePuestoRequest request) {
        log.info("Creando puesto titulo={}, empresaId={}", request.getTitulo(), request.getIdEmpresa());
        Empresa empresa = empresaService.getDomainEmpresaById(request.getIdEmpresa());

        Puesto p = new Puesto(
                null,
                request.getTitulo().trim(),
                request.getDescripcion().trim(),
                request.getSalario(),
                request.getEstado(),
                request.getTipoPublicacion() != null ? request.getTipoPublicacion() : TipoPublicacion.PUBLICO,
                true,
                LocalDateTime.now(),
                empresa
        );
        return toResponse(repository.save(p));
    }

    public PuestoResponse updatePuesto(Long id, UpdatePuestoRequest request) {
        log.info("Actualizando puesto id={}", id);
        Puesto p = repository.findById(id).orElseThrow(() -> new PuestoNotFoundException(id));
        Empresa empresa = empresaService.getDomainEmpresaById(request.getIdEmpresa());

        p.setTitulo(request.getTitulo().trim());
        p.setDescripcion(request.getDescripcion().trim());
        p.setSalario(request.getSalario());
        p.setEstado(request.getEstado());
        p.setTipoPublicacion(request.getTipoPublicacion());
        p.setEmpresa(empresa);

        return toResponse(repository.save(p));
    }

    public void changeActiveStatus(Long id, boolean value) {
        Puesto p = repository.findById(id).orElseThrow(() -> new PuestoNotFoundException(id));
        p.setActive(value);
        repository.save(p);
    }

    public void deleteLogical(Long id) {
        changeActiveStatus(id, false);
    }

    public UpdatePuestoRequest buildUpdateRequest(Long id) {
        Puesto p = repository.findById(id).orElseThrow(() -> new PuestoNotFoundException(id));
        Long idEmpresa = (p.getEmpresa() == null) ? null : p.getEmpresa().getIdEmpresa();
        return new UpdatePuestoRequest(
                p.getTitulo(), p.getDescripcion(), p.getSalario(),
                p.getEstado(), p.getTipoPublicacion(), idEmpresa
        );
    }

    private PuestoResponse toResponse(Puesto p) {
        Long idEmpresa = (p.getEmpresa() == null) ? null : p.getEmpresa().getIdEmpresa();
        return new PuestoResponse(
                p.getIdPuesto(), p.getTitulo(), p.getDescripcion(),
                p.getSalario(), p.getEstado(), p.getTipoPublicacion(),
                idEmpresa, p.isActive(), p.getCreatedAt()
        );
    }
}