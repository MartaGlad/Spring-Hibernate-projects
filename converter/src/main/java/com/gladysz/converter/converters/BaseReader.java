package com.gladysz.converter.converters;

import org.springframework.http.HttpInputMessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

public final class BaseReader {

    private BaseReader() {}

    public static String read(HttpInputMessage inputMessage) throws IOException {
        StringBuilder builder = new StringBuilder();

        try (Reader reader = new BufferedReader(new InputStreamReader(inputMessage.getBody(), StandardCharsets.UTF_8))) {

            int c;
            while ((c = reader.read()) != -1) {
                builder.append((char) c);
            }
        }
        return builder.toString();
    }
}
