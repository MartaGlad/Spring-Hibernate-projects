package com.gladysz.converter.controller.slash;

import com.gladysz.converter.domain.MyCustomClass;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class MyCustomSlashConverter implements HttpMessageConverter<Object> {

    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType) {

        return clazz.getName().equals("com.gladysz.converter.domain.MyCustomClass")
                && mediaType.getType().equals("text") && mediaType.getSubtype().equals("plain");
    }


    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {

        return clazz.getName().equals("com.gladysz.converter.domain.MyCustomClass")
                && mediaType.getType().equals("text") && mediaType.getSubtype().equals("plain");
    }


    @Override
    public List<MediaType> getSupportedMediaTypes() {

        return List.of(/*MediaType.ALL*/MediaType.TEXT_PLAIN);
    }


    @Override
    public Object read(@NonNull Class<?> clazz, @NonNull HttpInputMessage inputMessage) throws IOException {

        StringBuilder builder = new StringBuilder();

        try (Reader reader = new BufferedReader(
                new InputStreamReader(inputMessage.getBody(),
                        StandardCharsets.UTF_8))) {
            int c;
            while ((c = reader.read()) != -1) {
                builder.append((char) c);
            }
        }

        String[] fields = builder.toString().split("/");

        return new MyCustomClass(fields[0], fields[1], fields[2]);
    }


    @Override
    public void write(Object o, @Nullable MediaType contentType, @NonNull HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

    }
}
