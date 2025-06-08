// backend/src/main/java/banking2025com/example/bankapp/config/OpenApiConfig.java
package banking2025com.example.bankapp.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenApiCustomizer customOpenApi() {
        return openApi -> openApi.info(
                new Info()
                        .title("Bank App API")
                        .version("1.0")
                        .description("Minimal bank application exposing REST endpoints for accounts & transactions")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org"))
        );
    }
}
