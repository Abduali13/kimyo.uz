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
                description = "Java Project",
                license = @License(
                        name = "Kimyo.uz Project License"
                ),
                contact = @Contact(
                        name = "Abduali Abdumutalibov",
                        url = "https://t.me/abdumutalibov_a",
                        email = "dryden1309@gmail.com"
                )
        )
)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
