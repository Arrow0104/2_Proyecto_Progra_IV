package cr.ac.una.job.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "oferentes")
public class Oferente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOferente;

    @Column(nullable = false, length = 80)
    private String nombre;

    // ── Campos nuevos P2 ──────────────────────────────────────────────────────

    @Column(nullable = false, length = 80)
    private String apellido;

    @Column(length = 60)
    private String nacionalidad;

    @Column(length = 20)
    private String telefono;

    @Column(length = 100)
    private String correoOferente;

    // ─────────────────────────────────────────────────────────────────────────

    @Column(nullable = false, length = 255)
    private String residencia;

    @Column
    private String cvPath;

    @Column(nullable = false)
    private boolean active;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Oferente() {}

    /** Constructor completo P2 */
    public Oferente(Long idOferente, String nombre, String apellido,
                    String nacionalidad, String telefono, String correoOferente,
                    String residencia, String cvPath,
                    boolean active, LocalDateTime createdAt, Usuario usuario) {
        this.idOferente = idOferente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nacionalidad = nacionalidad;
        this.telefono = telefono;
        this.correoOferente = correoOferente;
        this.residencia = residencia;
        this.cvPath = cvPath;
        this.active = active;
        this.createdAt = createdAt;
        this.usuario = usuario;
    }

    @PrePersist
    public void prePersist() {
        if (createdAt == null) createdAt = LocalDateTime.now();
        active = true;
    }

    public Long getIdOferente() { return idOferente; }
    public void setIdOferente(Long idOferente) { this.idOferente = idOferente; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getNacionalidad() { return nacionalidad; }
    public void setNacionalidad(String nacionalidad) { this.nacionalidad = nacionalidad; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getCorreoOferente() { return correoOferente; }
    public void setCorreoOferente(String correoOferente) { this.correoOferente = correoOferente; }

    public String getResidencia() { return residencia; }
    public void setResidencia(String residencia) { this.residencia = residencia; }

    public String getCvPath() { return cvPath; }
    public void setCvPath(String cvPath) { this.cvPath = cvPath; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Oferente of)) return false;
        return Objects.equals(idOferente, of.idOferente);
    }

    @Override
    public int hashCode() { return Objects.hash(idOferente); }

    @Override
    public String toString() {
        return "Oferente{id=" + idOferente + ", nombre='" + nombre + " " + apellido + "', active=" + active + "}";
    }
}