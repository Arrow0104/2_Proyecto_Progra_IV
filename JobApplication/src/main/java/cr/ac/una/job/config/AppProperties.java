package cr.ac.una.job.config;


 import org.springframework.boot.context.properties.ConfigurationProperties;

 /**
  * AppProperties P2
  * Propiedades con prefijo "app" en application.properties.
  * Se simplificó respecto al P1: se eliminaron defaultCurrency y taxRate
  * que eran del módulo de productos que ya no existe.
  */
 @ConfigurationProperties(prefix = "app")
 public class AppProperties {

     private String name = "Bolsa de Empleo";

     public String getName() { return name; }
     public void setName(String name) { this.name = name; }
 }