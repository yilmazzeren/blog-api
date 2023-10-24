package com.example.blog.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Value("http://localhost:8081")
    private String devUrl;
    @Value("${project.version}")
    private String version;

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");

        Contact contact = new Contact();
        contact.setEmail("yilmazzeren93@gmail.com");
        contact.setName("Yılmaz ZEREN");
        contact.setUrl("https://github.com/yilmazzeren");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("Blog")
                .version(version)
                .contact(contact)
                .description("This is example description.")
                .license(mitLicense);

        return new OpenAPI().info(info).components(new Components());
    }
}
