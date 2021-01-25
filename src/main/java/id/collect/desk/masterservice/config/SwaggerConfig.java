package id.collect.desk.masterservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("id.kinyis.restapi"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact(
                "Kinyis",
                "kinyis.id",
                "kinyis@gmail.com");
        return new ApiInfo(
                "Belajar Bangun Rest API",
                "Lagi belajar bangun rest api sih",
                "Version 0.1",
                "Bebas Siapa Aja",
                contact,
                "Gratis",
                "Gratis Terus...",
                Collections.emptyList());
    }
}
