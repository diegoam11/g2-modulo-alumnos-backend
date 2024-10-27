package com.fisiunmsm.ayudoc_alumnos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
public class AyudocAlumnosApplication {

    public static void main(String[] args) {
        SpringApplication.run(AyudocAlumnosApplication.class, args);
    }

}
