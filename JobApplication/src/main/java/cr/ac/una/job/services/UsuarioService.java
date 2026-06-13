package cr.ac.una.job.services;

import cr.ac.una.job.dtos.usuario.CreateUsuarioRequest;
import cr.ac.una.job.dtos.usuario.UpdateUsuarioRequest;
import cr.ac.una.job.dtos.usuario.UsuarioResponse;
import cr.ac.una.job.exceptions.UsuarioNotFoundException;
import cr.ac.una.job.models.Usuario;
import cr.ac.una.job.repositories.IUsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService {
    private static final Logger log = LoggerFactory.getLogger(UsuarioService.class);

    private final IUsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(IUsuarioRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    public List<UsuarioResponse> getAllUsuarios() {
        return repository.findByActiveTrue().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public UsuarioResponse getUsuarioById(Long id) {
        return toResponse(repository.findById(id).orElseThrow(() -> new UsuarioNotFoundException(id)));
    }

    @Transactional(readOnly = true)
    public Usuario getDomainUsuarioById(Long id) {
        return repository.findById(id).orElseThrow(() -> new UsuarioNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public Optional<Usuario> findByCorreo(String correo) {
        return repository.findByCorreo(correo);
    }

    @Transactional(readOnly = true)
    public List<UsuarioResponse> searchByCorreo(String correo) {
        if (correo == null || correo.trim().length() < 2) return List.of();
        return repository.findByActiveTrueAndCorreoContaining(correo).stream().map(this::toResponse).toList();
    }

    public UsuarioResponse createUsuario(CreateUsuarioRequest request) {
        log.info("Creando usuario correo={}", request.getCorreo());
        Usuario u = new Usuario(
                null,
                request.getCorreo().trim(),
                request.getIdentificacion().trim(),
                passwordEncoder.encode(request.getPassword()),
                request.getRol(),
                request.getEstado(),
                false,             // ← siempre inactivo al crear, el admin activa después
                LocalDateTime.now()
        );
        return toResponse(repository.save(u));
    }

    public UsuarioResponse updateUsuario(Long id, UpdateUsuarioRequest request) {
        log.info("Actualizando usuario id={}", id);
        Usuario u = repository.findById(id).orElseThrow(() -> new UsuarioNotFoundException(id));

        u.setCorreo(request.getCorreo().trim());
        u.setIdentificacion(request.getIdentificacion().trim());
        u.setRol(request.getRol());
        u.setEstado(request.getEstado());

        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            u.setPassword(passwordEncoder.encode(request.getPassword()));   // ← BCrypt P2
        }

        return toResponse(repository.save(u));
    }

    public void changeActiveStatus(Long id, boolean value) {
        Usuario u = repository.findById(id).orElseThrow(() -> new UsuarioNotFoundException(id));
        u.setActive(value);
        repository.save(u);
    }

    public void deleteLogical(Long id) {
        changeActiveStatus(id, false);
    }

    public UpdateUsuarioRequest buildUpdateRequest(Long id) {
        Usuario u = repository.findById(id).orElseThrow(() -> new UsuarioNotFoundException(id));
        return new UpdateUsuarioRequest(u.getCorreo(), u.getIdentificacion(), null, u.getRol(), u.getEstado());
    }

    private UsuarioResponse toResponse(Usuario u) {
        return new UsuarioResponse(
                u.getIdUsuario(), u.getCorreo(), u.getIdentificacion(),
                u.getRol(), u.getEstado(), u.isActive(), u.getCreatedAt()
        );
    }
}