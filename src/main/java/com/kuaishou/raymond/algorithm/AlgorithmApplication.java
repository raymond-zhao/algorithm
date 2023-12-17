package com.kuaishou.raymond.algorithm;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class AlgorithmApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlgorithmApplication.class, args);
    }

    // @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext context) {
        return args -> {
            log.info("Let's inspect the beans provided by Spring Boot:");
            String[] beanNames = context.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                log.info("beanName = {}", beanName);
            }
        };
    }

}
