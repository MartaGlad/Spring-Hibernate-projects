package com.gladysz.securitycommon;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests(auth -> auth

                        .requestMatchers(HttpMethod.GET, "/bean-lifecycle/**")
                        .hasAnyRole("BASIC", "ADVANCED", "ADMIN")

                        .requestMatchers(HttpMethod.POST, "/spring-events/**")
                        .hasAnyRole("BASIC", "ADVANCED", "ADMIN")

                        .requestMatchers(HttpMethod.POST, "/csv-converter/**")
                        .hasAnyRole("BASIC", "ADVANCED", "ADMIN")

                        .requestMatchers(HttpMethod.GET, "/file-integration/monitor/results/**")
                        .hasAnyRole("BASIC", "ADVANCED", "ADMIN")

                        .requestMatchers(HttpMethod.POST, "/file-integration/monitor/create")
                        .hasAnyRole("ADMIN")

                        .requestMatchers(HttpMethod.POST, "/converter/**")
                        .hasAnyRole("ADVANCED", "ADMIN")

                        .requestMatchers(HttpMethod.GET, "/webflux/**")
                        .hasAnyRole( "ADVANCED", "ADMIN")

                        .requestMatchers(HttpMethod.GET, "/byte-manipulation/**")
                        .hasAnyRole("ADMIN")

                        .requestMatchers(HttpMethod.POST, "/jms/**")
                        .hasAnyRole("ADMIN")

                        .anyRequest()
                        .denyAll()
                )
                .httpBasic(withDefaults());
        return http.build();
    }


    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails basic = User
                .withUsername("basic")
                .password("{noop}basic")
                .roles("BASIC")
                .build();

        UserDetails advanced = User
                .withUsername("advanced")
                .password("{noop}advanced")
                .roles("ADVANCED")
                .build();

        UserDetails admin = User
                .withUsername("admin")
                .password("{noop}admin")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(basic, advanced, admin);
    }
}



