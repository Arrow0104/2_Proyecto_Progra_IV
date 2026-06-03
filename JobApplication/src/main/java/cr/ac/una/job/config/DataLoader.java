package cr.ac.una.job.config;

import cr.ac.una.job.models.*;
import cr.ac.una.job.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DataLoader P2
 * Diferencias respecto al P1:
 * - Contraseñas hasheadas con BCrypt (ya no texto plano).
 * - Empresa ahora tiene: localizacion, correo, descripcion.
 * - Oferente ahora tiene: apellido, nacionalidad, telefono, correo.
 * - Puesto ahora tiene: tipoPublicacion (PUBLICO / PRIVADO).
 * - Se eliminó el módulo Product que ya no existe en P2.
 */
@Configuration
public class DataLoader {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner loadData(
            IUsuarioRepository usuarioRepository,
            IEmpresaRepository empresaRepository,
            IOferenteRepository oferenteRepository,
            IPuestoRepository puestoRepository,
            ICaracteristicaRepository caracteristicaRepository,
            IPuestoCaracteristicaRepository puestoCaracteristicaRepository) {

        return args -> {

            // ── Usuarios / Empresas / Oferentes ──────────────────────────────
            if (usuarioRepository.count() == 0) {

                String pass = passwordEncoder.encode("password123");
                String adminPass = passwordEncoder.encode("admin123");

                Usuario u1 = usuarioRepository.save(new Usuario(null,
                        "empresa1@jobapp.com", "22345678", pass,
                        Rol.EMPRESA, EstadoUsuario.ACTIVO, true, LocalDateTime.now()));
                Usuario u2 = usuarioRepository.save(new Usuario(null,
                        "empresa2@jobapp.com", "27654321", pass,
                        Rol.EMPRESA, EstadoUsuario.ACTIVO, true, LocalDateTime.now()));
                Usuario u3 = usuarioRepository.save(new Usuario(null,
                        "oferente1@jobapp.com", "11111111", pass,
                        Rol.OFERENTE, EstadoUsuario.ACTIVO, true, LocalDateTime.now()));
                Usuario u4 = usuarioRepository.save(new Usuario(null,
                        "oferente2@jobapp.com", "22222222", pass,
                        Rol.OFERENTE, EstadoUsuario.ACTIVO, true, LocalDateTime.now()));
                usuarioRepository.save(new Usuario(null,
                        "admin@jobapp.com", "55555555", adminPass,
                        Rol.ADMIN, EstadoUsuario.ACTIVO, true, LocalDateTime.now()));

                // Empresa — nuevos campos: localizacion, correo, descripcion
                Empresa e1 = empresaRepository.save(new Empresa(null,
                        "Tech Solutions Costa Rica", "+506 2234-5678",
                        "San José, Costa Rica",
                        "empresa1@techsolutions.cr",
                        "Empresa líder en desarrollo de software empresarial.",
                        true, LocalDateTime.now(), u1));

                Empresa e2 = empresaRepository.save(new Empresa(null,
                        "Digital Innovations Ltd", "+506 2765-4321",
                        "Heredia, Costa Rica",
                        "rrhh@digitalinnovations.cr",
                        "Especialistas en transformación digital para el sector financiero.",
                        true, LocalDateTime.now(), u2));

                // Oferente — nuevos campos: apellido, nacionalidad, telefono, correo
                oferenteRepository.save(new Oferente(null,
                        "Juan", "Pérez García",
                        "Costarricense", "8888-1111",
                        "juan.perez@gmail.com",
                        "San José", "/cv/juan_perez.pdf",
                        true, LocalDateTime.now(), u3));

                oferenteRepository.save(new Oferente(null,
                        "María", "López Rodríguez",
                        "Costarricense", "8888-2222",
                        "maria.lopez@gmail.com",
                        "Heredia", "/cv/maria_lopez.pdf",
                        true, LocalDateTime.now(), u4));
            }

            // ── Características (igual al P1) ─────────────────────────────────
            if (caracteristicaRepository.count() == 0) {
                Caracteristica prog    = save(caracteristicaRepository, "Programación",       null);
                Caracteristica bd      = save(caracteristicaRepository, "Bases de Datos",     null);
                Caracteristica devops  = save(caracteristicaRepository, "DevOps",             null);
                Caracteristica diseno  = save(caracteristicaRepository, "Diseño",             null);
                Caracteristica blandas = save(caracteristicaRepository, "Habilidades Blandas",null);

                // Programación
                save(caracteristicaRepository, "Java",        prog);
                save(caracteristicaRepository, "Python",      prog);
                save(caracteristicaRepository, "JavaScript",  prog);
                save(caracteristicaRepository, "TypeScript",  prog);
                save(caracteristicaRepository, "Spring Boot", prog);
                save(caracteristicaRepository, "React",       prog);
                save(caracteristicaRepository, "Node.js",     prog);

                // Bases de Datos
                save(caracteristicaRepository, "PostgreSQL",  bd);
                save(caracteristicaRepository, "MySQL",       bd);
                save(caracteristicaRepository, "MongoDB",     bd);
                save(caracteristicaRepository, "Oracle",      bd);

                // DevOps
                save(caracteristicaRepository, "Docker",          devops);
                save(caracteristicaRepository, "Kubernetes",      devops);
                save(caracteristicaRepository, "GitHub Actions",  devops);
                save(caracteristicaRepository, "AWS",             devops);

                // Diseño
                save(caracteristicaRepository, "Figma",       diseno);
                save(caracteristicaRepository, "Photoshop",   diseno);
                save(caracteristicaRepository, "UX Research", diseno);

                // Habilidades Blandas
                save(caracteristicaRepository, "Trabajo en equipo",       blandas);
                save(caracteristicaRepository, "Liderazgo",               blandas);
                save(caracteristicaRepository, "Comunicación",            blandas);
                save(caracteristicaRepository, "Resolución de problemas", blandas);
                save(caracteristicaRepository, "Gestión del tiempo",      blandas);
            }

            // ── Puestos — ahora con TipoPublicacion ───────────────────────────
            if (puestoRepository.count() == 0) {
                Empresa e1 = empresaRepository.findAll().get(0);
                Empresa e2 = empresaRepository.findAll().get(1);

                // PUBLICO = visible en la búsqueda pública sin login
                // PRIVADO = solo visible para oferentes logueados

                Puesto p1 = puestoRepository.save(new Puesto(null,
                        "Desarrollador Java Senior",
                        "Buscamos desarrollador Java con experiencia en Spring Boot y microservicios. Mínimo 5 años.",
                        new BigDecimal("2500000"), EstadoPuesto.ACTIVO, TipoPublicacion.PUBLICO,
                        true, LocalDateTime.now(), e1));

                Puesto p2 = puestoRepository.save(new Puesto(null,
                        "Diseñador UI/UX",
                        "Profesional en diseño de interfaces y experiencia de usuario con Figma.",
                        new BigDecimal("1800000"), EstadoPuesto.ACTIVO, TipoPublicacion.PUBLICO,
                        true, LocalDateTime.now(), e1));

                Puesto p3 = puestoRepository.save(new Puesto(null,
                        "Analista de Sistemas",
                        "Análisis y mejora de procesos con metodologías ágiles.",
                        new BigDecimal("2000000"), EstadoPuesto.ACTIVO, TipoPublicacion.PUBLICO,
                        true, LocalDateTime.now(), e2));

                Puesto p4 = puestoRepository.save(new Puesto(null,
                        "Desarrollador Full Stack",
                        "Java backend + React frontend con bases de datos relacionales.",
                        new BigDecimal("2200000"), EstadoPuesto.ACTIVO, TipoPublicacion.PRIVADO,
                        true, LocalDateTime.now(), e2));

                Puesto p5 = puestoRepository.save(new Puesto(null,
                        "Especialista en DevOps",
                        "Docker, Kubernetes, CI/CD y administración de infraestructura en la nube.",
                        new BigDecimal("2700000"), EstadoPuesto.ACTIVO, TipoPublicacion.PRIVADO,
                        true, LocalDateTime.now(), e2));

                // Links puesto-característica (igual al P1)
                link(puestoCaracteristicaRepository, caracteristicaRepository, p1, "Java",                    4);
                link(puestoCaracteristicaRepository, caracteristicaRepository, p1, "Spring Boot",             4);
                link(puestoCaracteristicaRepository, caracteristicaRepository, p1, "PostgreSQL",              3);
                link(puestoCaracteristicaRepository, caracteristicaRepository, p1, "Trabajo en equipo",       3);

                link(puestoCaracteristicaRepository, caracteristicaRepository, p2, "Figma",                   4);
                link(puestoCaracteristicaRepository, caracteristicaRepository, p2, "Photoshop",               3);
                link(puestoCaracteristicaRepository, caracteristicaRepository, p2, "UX Research",             3);

                link(puestoCaracteristicaRepository, caracteristicaRepository, p3, "Trabajo en equipo",       3);
                link(puestoCaracteristicaRepository, caracteristicaRepository, p3, "Liderazgo",               2);
                link(puestoCaracteristicaRepository, caracteristicaRepository, p3, "Resolución de problemas", 3);

                link(puestoCaracteristicaRepository, caracteristicaRepository, p4, "Java",                    3);
                link(puestoCaracteristicaRepository, caracteristicaRepository, p4, "React",                   3);
                link(puestoCaracteristicaRepository, caracteristicaRepository, p4, "PostgreSQL",              3);

                link(puestoCaracteristicaRepository, caracteristicaRepository, p5, "Docker",                  4);
                link(puestoCaracteristicaRepository, caracteristicaRepository, p5, "Kubernetes",              4);
                link(puestoCaracteristicaRepository, caracteristicaRepository, p5, "AWS",                     3);
                link(puestoCaracteristicaRepository, caracteristicaRepository, p5, "GitHub Actions",          3);
            }
        };
    }

    private Caracteristica save(ICaracteristicaRepository repo, String nombre, Caracteristica padre) {
        return repo.save(new Caracteristica(null, nombre, padre));
    }

    private void link(IPuestoCaracteristicaRepository pcRepo,
                      ICaracteristicaRepository carRepo,
                      Puesto puesto, String nombre, int nivel) {
        carRepo.findByNombre(nombre).ifPresent(car ->
                pcRepo.save(new PuestoCaracteristica(puesto, car, nivel)));
    }
}
}