package cr.ac.una.job.models;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "caracteristicas")
public class Caracteristica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCaracteristica;

    @Column(nullable = false, length = 100)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "padre_id")
    private Caracteristica padre;

    public Caracteristica() {}

    public Caracteristica(Long idCaracteristica, String nombre, Caracteristica padre) {
        this.idCaracteristica = idCaracteristica;
        this.nombre = nombre;
        this.padre = padre;
    }

    public Long getIdCaracteristica() { return idCaracteristica; }
    public void setIdCaracteristica(Long id) { this.idCaracteristica = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Caracteristica getPadre() { return padre; }
    public void setPadre(Caracteristica padre) { this.padre = padre; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Caracteristica c)) return false;
        return Objects.equals(idCaracteristica, c.idCaracteristica);
    }

    @Override
    public int hashCode() { return Objects.hash(idCaracteristica); }

    @Override
    public String toString() {
        return "Caracteristica{id=" + idCaracteristica + ", nombre='" + nombre + "'}";
    }
}