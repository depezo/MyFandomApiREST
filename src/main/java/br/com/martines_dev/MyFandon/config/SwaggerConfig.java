package br.com.martines_dev.MyFandon.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket api() {
    	
    	
    	List<SecurityScheme> schemeList = new ArrayList<>();
    	schemeList.add(new BasicAuth("basicAuth"));
    	
        return new Docket(DocumentationType.SWAGGER_2)
          .select()
          .apis(RequestHandlerSelectors.any())
          .paths(PathSelectors.ant("/api/**"))
          .build()
          .apiInfo( apiInfo() )
          .securitySchemes(schemeList);
          
    }
    
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Aplicativo de administração")
                .description("O aplicativo de fandom permite gerenciar uma fandom de anime, "
                        + "ou mangá. Nela é possível se obter informações sobre animes, autores, "
                        + "personagens, fazer comentários, administrar usuários etc.")
                .version("1.0.0")
                //.license("Apache License Version 2.0")
                //.licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .build();
    }	
}