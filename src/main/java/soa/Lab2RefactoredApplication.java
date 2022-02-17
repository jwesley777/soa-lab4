package soa;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import soa.webservices.*;

import javax.xml.ws.Endpoint;

@SpringBootApplication
@EnableScheduling
public class Lab2RefactoredApplication {

    public static void main(String[] args) {
        SpringApplication.run(Lab2RefactoredApplication.class, args);

        Endpoint.publish(
                "http://localhost:8083/addtasks",
                new AdditionalTasksWebService()
        );
        Endpoint.publish(
                "http://localhost:8083/coordinates",
                new CoordinatesWebService()
        );
        Endpoint.publish(
                "http://localhost:8083/locations",
                new LocationWebService()
        );
        Endpoint.publish(
                "http://localhost:8083/persons",
                new PersonWebService()
        );
        Endpoint.publish(
                "http://localhost:8083/tickets",
                new TicketWebService()
        );
    }


    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*").allowedHeaders("*").allowedMethods("*");
            }
        };
    }
}
