package cr.ac.una.job.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

import java.nio.file.Paths;


@Configuration
public class WebConfig implements WebMvcConfigurer {

    // ── CORS ──────────────────────────────────────────────────────────────────
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins(
                    "http://localhost:5173",   // Vite dev server
                    "http://localhost:5174"    // por si el 5173 está ocupado
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(false);
    }

    // ── Archivos estáticos externos (CVs subidos) ─────────────────────────────
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Sirve los PDFs de CV subidos por los oferentes
        String uploadPath = Paths.get("uploads/cv/").toAbsolutePath().toUri().toString();
        registry.addResourceHandler("/cv/**")
                .addResourceLocations("classpath:/static/cv/", uploadPath);
    }

    // ── SPA fallback (para producción con vite build) ─────────────────────────
    // Spring devuelve index.html para cualquier ruta que no sea /api/** ni un recurso,
    // así React Router puede manejar las rutas del frontend.
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/{path:[^\\.]*}")
                .setViewName("forward:/index.html");
    }
}