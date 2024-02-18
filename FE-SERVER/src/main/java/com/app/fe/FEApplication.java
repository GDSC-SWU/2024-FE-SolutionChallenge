package com.app.fe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class FEApplication {

    public static void main(String[] args) {
        SpringApplication.run(FEApplication.class, args);
    }
}