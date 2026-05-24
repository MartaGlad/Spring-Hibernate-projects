package com.gladysz.converter.config;

import com.gladysz.converter.converters.object.MyCustomObjectConverter;
import com.gladysz.converter.converters.slash.MyCustomSlashConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;


@Configuration
public class ConverterConfiguration {

    @Bean
    public HttpMessageConverter<Object> customSlashConverter() {

        return new MyCustomSlashConverter();
    }


    @Bean
    public HttpMessageConverter<Object> customObjectConverter() {

        return new MyCustomObjectConverter();
    }
}
