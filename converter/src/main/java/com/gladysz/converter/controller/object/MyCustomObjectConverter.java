package com.gladysz.converter.controller.object;

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

public class MyCustomObjectConverter implements HttpMessageConverter<Object> {

    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType) {

        return clazz.getName().equals("com.gladysz.converter.domain.MyCustomClass")
                && mediaType.getType().equals("text") && mediaType.getSubtype().equals("object");
    }


    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {

        return clazz.getName().equals("com.gladysz.converter.domain.MyCustomClass")
                 && mediaType.getType().equals("text") && mediaType.getSubtype().equals("object");
    }


    @Override
    public List<MediaType> getSupportedMediaTypes() {

        return List.of(new MediaType("text","object"));
    }


    @Override
    public Object read(@NonNull Class<?> clazz, @NonNull HttpInputMessage inputMessage) throws IOException {

        String[] fields = getStrings(inputMessage);

        String field1 = fields[0]
                .split("=")[1]
                .replace("\"", "")
                .trim();

        String field2 = fields[1]
                .split("=")[1]
                .replace("\"","")
                .trim();

        String field3 = fields[2]
                .split("=")[1]
                .replace("\"","")
                .trim();

        return new MyCustomClass(field1, field2, field3);
    }


    private static String @NonNull [] getStrings(@NonNull HttpInputMessage inputMessage) throws IOException {

        StringBuilder builder = new StringBuilder();

        try (Reader reader = new BufferedReader(
                    new InputStreamReader(inputMessage.getBody(),
                            StandardCharsets.UTF_8))) {
            int c;
            while ((c = reader.read()) != -1) {
                builder.append((char) c);
            }
        }

        String text = builder.toString();

        text = text.replace("MyCustomClass(", "")
                .replace(")","");

        return text.split(",");
    }


    @Override
    public void write(Object o, @Nullable MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

    }
}
