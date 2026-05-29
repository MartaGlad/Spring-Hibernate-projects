package com.gladysz.jms;

import com.gladysz.securitycommon.SecurityConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.jms.annotation.EnableJms;


@Import(SecurityConfiguration.class)
@SpringBootApplication
@EnableJms
public class JmsApplication {

    public static void main(String[] args) {

        SpringApplication.run(JmsApplication.class, args);
    }
}