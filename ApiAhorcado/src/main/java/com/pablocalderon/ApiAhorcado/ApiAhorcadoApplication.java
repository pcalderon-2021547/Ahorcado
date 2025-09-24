package com.pablocalderon.ApiAhorcado;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiAhorcadoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ApiAhorcadoApplication.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("API funcionando");
    }
}
