package org.example.springsample.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@OpenAPIDefinition(
        security = @SecurityRequirement(
                name = "bearerAuth"
        )

)
@SecuritySchemes({
        @SecurityScheme(
                name = "bearerAuth",
                type = SecuritySchemeType.HTTP,
                scheme = "bearer",
                bearerFormat = "JWT"
        )
})
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        Contact contact = new Contact();
        contact.setUrl("http://localhost:8080/swagger-ui.html");
        contact.setEmail("sample@gmail.com");

        License license = new License().name("Apache 2.0")
                .url("https://www.apache.org/licenses/LICENSE-2.0.html")
                .name("Apache 2.0");

        Info info = new Info();
        info.setContact(contact);
        info.description("This is a sample description");
        info.title("Sample Api");
        info.version("1.0");
        info.license(license);

        return new OpenAPI().info(info);

    }
}
