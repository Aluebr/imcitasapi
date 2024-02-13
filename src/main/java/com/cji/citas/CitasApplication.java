package com.cji.citas;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title="API Rest Citas I&M Asesores",
                description = "Documentación de la API REST de citas para I&M Asesores",
                version="v1",
                contact = @Contact(
                        name="Jose Vicente Ebri, Carlos Hernandez, Iker Rodríguez",
                        email="imasesorescji@gmail.com"
                ),
                license = @License(
                        name="Apache2.0"
                )
        )
)
public class CitasApplication {

    public static void main(String[] args) {
        SpringApplication.run(CitasApplication.class, args);
    }

}
