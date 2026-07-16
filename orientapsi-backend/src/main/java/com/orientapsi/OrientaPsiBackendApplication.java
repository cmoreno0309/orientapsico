package com.orientapsi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class OrientaPsiBackendApplication {

    public static void main(String[] args) {

        // TEMPORAL: generar hash para la contraseña 123
        System.out.println("=== HASH BCrypt: " + new BCryptPasswordEncoder().encode("123") + " ===");
        SpringApplication.run(OrientaPsiBackendApplication.class, args);
    }
}
