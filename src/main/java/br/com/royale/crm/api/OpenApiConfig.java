//package br.com.royale.crm.api;
//
//import java.util.Arrays;
//import java.util.Collections;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.MediaType;
//
//import br.com.royale.crm.api.entity.AccountEntity;
//import io.swagger.v3.oas.models.Components;
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Info;
//import io.swagger.v3.oas.models.info.License;
//import springfox.documentation.builders.ParameterBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.builders.RequestParameterBuilder;
//import springfox.documentation.schema.ModelRef;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.HttpAuthenticationScheme;
//import springfox.documentation.service.RequestParameter;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//
//@Configuration
//public class OpenApiConfig {
//
//	@Bean
//	public OpenAPI customConfiguration() {
//		return new OpenAPI().components(new Components())
//				.info(new Info().title("Royale - CRM API").description(
//						"Royale - CRM API é uma api responsável pela comunicação entre os portais com o Banco de dados")
//						.version("1.0.0-Beta")
//						.license(new License().name("Royale All Rights Reserved").url("http://royale.com.br/"))
//						.termsOfService("http://royale.com.br/"));
//	}
//
//	@Bean
//	public Docket forumApi() {
//
//		HttpAuthenticationScheme authenticationScheme = HttpAuthenticationScheme.JWT_BEARER_BUILDER.name("JWT Token")
//				.build();
//
//		return new Docket(DocumentationType.OAS_30).select()
//				.apis(RequestHandlerSelectors.basePackage("br.com.royale.crm.api")).paths(PathSelectors.ant("/**"))
//				.build().ignoredParameterTypes(AccountEntity.class)
//				.securitySchemes(Collections.singletonList(authenticationScheme));
//
//	}
//}

package br.com.royale.crm.api;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.HttpAuthenticationScheme;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableOpenApi
public class OpenApiConfig {
    
    @Autowired
    private ServletContext context;

    @Bean
    public Docket api() {       
        HttpAuthenticationScheme authenticationScheme = HttpAuthenticationScheme.JWT_BEARER_BUILDER
                .name("Authorization")
                .build();

        return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant(context.getContextPath() + "/royale/**"))
                .build()
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Collections.singletonList(authenticationScheme));
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("Authorization", authorizationScopes));
    }

}