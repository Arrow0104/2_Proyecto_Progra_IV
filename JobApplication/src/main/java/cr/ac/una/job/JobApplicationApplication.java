package cr.ac.una.job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import cr.ac.una.job.config.AppProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class JobApplicationApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobApplicationApplication.class, args);
    }
}