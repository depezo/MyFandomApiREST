package br.com.martines_dev.MyFandon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
          .select()
          .apis(RequestHandlerSelectors.any())
          .paths(PathSelectors.any())
          .build();
          
    }
    
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("Aplicativo de administração")
//                .description("Permite controlar uma fandom")
//                .version("1.0.0")
//                //.license("Apache License Version 2.0")
//                //.licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
//                .build();
//    }	
}