package api.helpdesk;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashSet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@SpringBootApplication
@EnableSwagger2
public class Application  {

	public static void main(String[] args) throws IOException,NoSuchAlgorithmException {
		SpringApplication.run(Application.class, args);
		System.out.println("Aplicacao rodando");
	}

	private Contact contato() {
		return new Contact(
			"João Henrique",
			"http://teste.com.br",
			"joaohenriquevidalribeiro@gmail.com"
		);
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
			.title("Teste JWT")
			.description("Teste de aplicação JWT")
			.version("1.0")
			.termsOfServiceUrl("Termo de uso: Open Source")
			.license("Licença - Belacci")
			.licenseUrl("http://belacci.com.br")
			.contact(contato())
			.build();
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
			.select()
			.apis(RequestHandlerSelectors.any()) 
			.paths(PathSelectors.any())
			.build()
			.apiInfo(apiInfo())
			.consumes(new HashSet<>(Arrays.asList("application/json")))
			.produces(new HashSet<>(Arrays.asList("application/json")));
	}

}
