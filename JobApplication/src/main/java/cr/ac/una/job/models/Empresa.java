package cr.ac.una.job.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "empresas")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmpresa;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 20)
    private String telefono;

    // ── Campos nuevos P2 ──────────────────────────────────────────────────────

    @Column(length = 150)
    private String localizacion;

    @Column(length = 100)
    private String correoEmpresa;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    // ─────────────────────────────────────────────────────────────────────────

    @Column(nullable = false)
    private boolean active;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Empresa() {}

    /** Constructor completo P2 */
    public Empresa(Long idEmpresa, String nombre, String telefono,
                   String localizacion, String correoEmpresa, String descripcion,
                   boolean active, LocalDateTime createdAt, Usuario usuario) {
        this.idEmpresa = idEmpresa;
        this.nombre = nombre;
        this.telefono = telefono;
        this.localizacion = localizacion;
        this.correoEmpresa = correoEmpresa;
        this.descripcion = descripcion;
        this.active = active;
        this.createdAt = createdAt;
        this.usuario = usuario;
    }

    @PrePersist
    public void prePersist() {
        if (createdAt == null) createdAt = LocalDateTime.now();
        active = true;
    }

    public Long getIdEmpresa() { return idEmpresa; }
    public void setIdEmpresa(Long idEmpresa) { this.idEmpresa = idEmpresa; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getLocalizacion() { return localizacion; }
    public void setLocalizacion(String localizacion) { this.localizacion = localizacion; }

    public String getCorreoEmpresa() { return correoEmpresa; }
    public void setCorreoEmpresa(String correoEmpresa) { this.correoEmpresa = correoEmpresa; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Empresa e)) return false;
        return Objects.equals(idEmpresa, e.idEmpresa);
    }

    @Override
    public int hashCode() { return Objects.hash(idEmpresa); }

    @Override
    public String toString() {
        return "Empresa{id=" + idEmpresa + ", nombre='" + nombre + "', active=" + active + "}";
    }
}