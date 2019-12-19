package com.techieonthenet;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * The type Bootstrap app.
 */
@SpringBootApplication
@EnableJpaAuditing
public class BootstrapApp {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(BootstrapApp.class, args);
    }

    /**
     * Model mapper model mapper.
     *
     * @return the model mapper
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


}