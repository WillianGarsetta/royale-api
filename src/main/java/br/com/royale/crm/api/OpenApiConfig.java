package br.com.royale.crm.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.royale.crm.api.entity.AccountEntity;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI customConfiguration() {
		return new OpenAPI().components(new Components()).info(new Info().title("Royale - CRM API").description(
				"Royale - CRM API é uma api responsável pela comunicação entre os portais com o Banco de dados")
				.version("1.0.0-Beta")
				.license(new License().name("Royale All Rights Reserved").url("http://royale.com.br/"))
				.termsOfService("http://royale.com.br/"));
	}
	
	 @Bean
	    public Docket forumApi() {
	        return new Docket(DocumentationType.SWAGGER_2)
	                .select()
	                .apis(RequestHandlerSelectors.basePackage("br.com.royale.crm.api"))
	                .paths(PathSelectors.ant("/**"))
	                .build()
	                .ignoredParameterTypes(AccountEntity.class);
	    }
}