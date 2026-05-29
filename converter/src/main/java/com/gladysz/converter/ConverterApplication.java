package com.gladysz.converter;

import com.gladysz.securitycommon.SecurityConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;


@Import(SecurityConfiguration.class)
@SpringBootApplication
public class ConverterApplication {

    public static void main(String[] args) {

        SpringApplication.run(ConverterApplication.class, args);
    }
}