package cr.ac.una.job.repositories;

import cr.ac.una.job.models.Puesto;
import cr.ac.una.job.models.TipoPublicacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPuestoRepository extends JpaRepository<Puesto, Long> {

    List<Puesto> findByActiveTrue();

    List<Puesto> findByTituloContaining(String titulo);

    List<Puesto> findByActiveTrueAndTituloContaining(String titulo);

    List<Puesto> findByEmpresaIdEmpresa(Long empresaId);

    List<Puesto> findByActiveTrueAndEmpresaIdEmpresa(Long empresaId);

    // ── Nuevos P2 ──────────────────────────────────────────────────────────────

    /** Para la búsqueda pública: solo puestos ACTIVOS y PÚBLICOS */
    List<Puesto> findByActiveTrueAndTipoPublicacion(TipoPublicacion tipo);

    /** Para la búsqueda pública con filtro de título */
    List<Puesto> findByActiveTrueAndTipoPublicacionAndTituloContaining(TipoPublicacion tipo, String titulo);
}