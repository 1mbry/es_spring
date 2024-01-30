package it.syncroweb.es_03_spring_swagger_database;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Cocktail DB",
				version = "1.0.0",
				description = "REST API Cocktail DB",
				termsOfService = "ioan",
				contact = @Contact(
						name = "Ioan Imbrea",
						email = "ioan.imbrea@edu.itspiemonte.it"
				),
				license = @License(
						name = "license",
						url = "ioan"
				)
		)
)
public class Es03SpringSwaggerDatabaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(Es03SpringSwaggerDatabaseApplication.class, args);
	}

}
