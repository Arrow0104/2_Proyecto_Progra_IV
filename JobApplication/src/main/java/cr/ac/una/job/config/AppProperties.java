package cr.ac.una.job.config;


 import org.springframework.boot.context.properties.ConfigurationProperties;


 @ConfigurationProperties(prefix = "app")
 public class AppProperties {

     private String name = "Bolsa de Empleo";

     public String getName() { return name; }
     public void setName(String name) { this.name = name; }
 }