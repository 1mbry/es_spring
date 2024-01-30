package it.syncroweb.es_02;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Hello World Project",
				version = "1.0.0",
				description = "This project is only for learning!",
				termsOfService = "runcodenow",
				contact = @Contact(
						name = "Ioan Imbrea",
						email = "ioan.imbrea@edu.itspiemonte.it"
				),
				license = @License(
						name = "license",
						url = "runcodenow"
				)
		)
)
public class Es02Application {

	public static void main(String[] args) {
		SpringApplication.run(Es02Application.class, args);
	}

}
