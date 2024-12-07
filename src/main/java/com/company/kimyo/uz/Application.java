package com.company.kimyo.uz;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Kimyo.uz",
                version = "1 - version",
                description = "Java 7 Students Project",
                license = @License(
                        name = "Kimyo.uz Project License",
                        url = "https://isystem.uz"
                ),
                contact = @Contact(
                        name = "Hasanboy Xalilov",
                        url = "https://t.me/HasanboyXalilov",
                        email = "hasanboyxalilov75@gmail.com"
                )
        )
)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

	//todo;
}
