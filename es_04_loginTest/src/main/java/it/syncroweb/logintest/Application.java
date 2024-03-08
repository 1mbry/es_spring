package it.syncroweb.logintest;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import it.syncroweb.logintest.service.EmailSenderService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Cocktail DB",
				version = "1.0",
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
		),
		security = {
				@SecurityRequirement(name = "bearerAuth")
		}
)
@SecurityScheme(
		name = "bearerAuth",
		description = "JWT authentication",
		scheme = "bearer",
		type = SecuritySchemeType.HTTP,
		bearerFormat = "JWT",
		in = SecuritySchemeIn.HEADER
)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	private EmailSenderService senderService;
	/*@EventListener(ApplicationReadyEvent.class)
	public void triggerMail() throws MessagingException {
		senderService.sendSimpleEmail("petrisor.imbrea@gmail.com",
				"This is email body",
				"This is email subject");

	}*/
}
