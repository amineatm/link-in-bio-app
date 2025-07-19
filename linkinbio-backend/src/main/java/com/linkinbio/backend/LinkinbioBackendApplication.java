package com.linkinbio.backend;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class LinkinbioBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(LinkinbioBackendApplication.class, args);
    }

    @Bean
    public ApplicationRunner openSwaggerUI(WebClient.Builder builder) {
        return args -> {
            try {
                Thread.sleep(3000);
                String swaggerUrl = "http://localhost:8080/swagger-ui/index.html";
                String chromePath = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe";

                ProcessBuilder processBuilder = new ProcessBuilder(chromePath, swaggerUrl);
                processBuilder.start();

            } catch (Exception e) {
                System.err.println("Failed to open browser: " + e.getMessage());
            }
        };
    }

}
