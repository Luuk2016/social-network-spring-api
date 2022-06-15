package dev.lkenselaar.social.network.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

/**
 * @author Luuk Kenselaar (https://lkenselaar.dev)
 * @since 15/06/2022
 */
@Configuration
@OpenAPIDefinition(info = @Info(title = "Social network API", version = "v0.1"))
@SecurityScheme(
    name = "bearerAuth",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    scheme = "bearer"
)
public class OpenApi30Config {
}
