package com.makarov.fa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FaApplication {

    public static void main(String[] args) {
        SpringApplication.run(FaApplication.class, args);
    }
}

