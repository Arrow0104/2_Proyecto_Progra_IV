package cr.ac.una.job.services;

import cr.ac.una.job.dtos.empresa.CreateEmpresaRequest;
import cr.ac.una.job.dtos.empresa.EmpresaResponse;
import cr.ac.una.job.dtos.empresa.UpdateEmpresaRequest;
import cr.ac.una.job.exceptions.EmpresaNotFoundException;
import cr.ac.una.job.models.Empresa;
import cr.ac.una.job.models.EstadoUsuario;
import cr.ac.una.job.models.Usuario;
import cr.ac.una.job.repositories.IEmpresaRepository;
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
public class EmpresaService {
    private static final Logger log = LoggerFactory.getLogger(EmpresaService.class);

    private final IEmpresaRepository repository;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    public EmpresaService(IEmpresaRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<EmpresaResponse> getAllEmpresas() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(java.util.stream.Collectors.toList());
    }

    @Transactional(readOnly = true)
    public EmpresaResponse getEmpresaById(Long id) {
        return toResponse(repository.findById(id).orElseThrow(() -> new EmpresaNotFoundException(id)));
    }

    @Transactional(readOnly = true)
    public Empresa getDomainEmpresaById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EmpresaNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public Empresa getDomainEmpresaByUsuario(Long idUsuario) {
        return repository.findFirstByUsuarioIdUsuario(idUsuario)
                .orElseThrow(() -> new EmpresaNotFoundException(0L));
    }

    @Transactional(readOnly = true)
    public List<EmpresaResponse> searchByName(String nombre) {
        if (nombre == null || nombre.trim().length() < 2) return List.of();
        return repository.findByActiveTrueAndNombreContaining(nombre).stream().map(this::toResponse).toList();
    }

    public EmpresaResponse createEmpresa(CreateEmpresaRequest request) {
        log.info("Creando empresa nombre={}", request.getNombre());
        Empresa e = new Empresa(
                null,
                request.getNombre().trim(),
                request.getTelefono().trim(),
                trim(request.getLocalizacion()),
                trim(request.getCorreoEmpresa()),
                trim(request.getDescripcion()),
                false,
                LocalDateTime.now(),
                null
        );
        return toResponse(repository.save(e));
    }

    public EmpresaResponse updateEmpresa(Long id, UpdateEmpresaRequest request) {
        log.info("Actualizando empresa id={}", id);
        Empresa e = repository.findById(id).orElseThrow(() -> new EmpresaNotFoundException(id));

        e.setNombre(request.getNombre().trim());
        e.setTelefono(request.getTelefono().trim());
        e.setLocalizacion(trim(request.getLocalizacion()));
        e.setCorreoEmpresa(trim(request.getCorreoEmpresa()));
        e.setDescripcion(trim(request.getDescripcion()));

        return toResponse(repository.save(e));
    }

    public void changeActiveStatus(Long id, boolean value) {
        Empresa e = repository.findById(id)
                .orElseThrow(() -> new EmpresaNotFoundException(id));
        e.setActive(value);
        repository.save(e);

        // También actualizar el usuario vinculado
        if (e.getUsuario() != null) {
            Usuario u = e.getUsuario();
            u.setActive(value);
            u.setEstado(value ? EstadoUsuario.ACTIVO : EstadoUsuario.INACTIVO);
            usuarioRepository.save(u);
        }
    }

    public void deleteLogical(Long id) {
        changeActiveStatus(id, false);
    }

    public UpdateEmpresaRequest buildUpdateRequest(Long id) {
        Empresa e = repository.findById(id).orElseThrow(() -> new EmpresaNotFoundException(id));
        return new UpdateEmpresaRequest(
                e.getNombre(), e.getTelefono(),
                e.getLocalizacion(), e.getCorreoEmpresa(), e.getDescripcion()
        );
    }

    private EmpresaResponse toResponse(Empresa e) {
        return new EmpresaResponse(
                e.getIdEmpresa(), e.getNombre(), e.getTelefono(),
                e.getLocalizacion(), e.getCorreoEmpresa(), e.getDescripcion(),
                e.isActive(), e.getCreatedAt()
        );
    }

    private String trim(String s) { return (s == null) ? null : s.trim(); }
}