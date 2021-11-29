package com.example.Cuenta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;

@SpringBootApplication
public class CuentaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CuentaApplication.class, args);
    }

    @Bean
    public RestTemplate getresttemplate() {
        return new RestTemplate();
    }

}
