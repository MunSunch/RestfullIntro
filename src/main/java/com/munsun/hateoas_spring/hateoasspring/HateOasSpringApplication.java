package com.munsun.hateoas_spring.hateoasspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilderFactory;

@SpringBootApplication
public class HateOasSpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(HateOasSpringApplication.class, args);
    }
}
