package pdes.c1.universitypanel;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
@PropertySource("file:spring.env")
public class UniversitypanelApplication {

	public static void main(String[] args) {
		SpringApplication.run(UniversitypanelApplication.class, args);
	}

}
