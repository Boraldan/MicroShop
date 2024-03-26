package boraldan.keycloak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"ru.javabegin.micro.planner"})
public class KeyCloakApplication {

	public static void main(String[] args) {
		SpringApplication.run(KeyCloakApplication.class, args);
	}

}
