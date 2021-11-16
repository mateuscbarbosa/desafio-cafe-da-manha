package br.com.unidac.desafiocafedamanhaapi.infra;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxSwaggerConfigurations {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
						.select()
						.apis(RequestHandlerSelectors.any())
						.paths(PathSelectors.any())
						.build()
						.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfo("Api Desafio Café da Manhã Unidac",
							"Registra os colaboradores que pretendem participar do desjejum, e que irão trazer.",
							"Termos de Uso",
							"Termos de Serviço",
							new Contact("Mateus C. Barbosa", "Sem Site", "mateuscbarbosa@gmail.com"),
							"License of API", "API License URL", Collections.emptyList());
	}
}
