package com.gladysz.converter.converters.slash;

import com.gladysz.converter.converters.BaseReader;
import com.gladysz.converter.domain.MyCustomClass;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.io.IOException;
import java.util.List;

public class MyCustomSlashConverter implements HttpMessageConverter<Object> {

    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType) {

        return clazz.equals(MyCustomClass.class)
                && mediaType != null
                && mediaType.getType().equals("text")
                && mediaType.getSubtype().equals("plain");
    }


    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {

        return clazz.equals(MyCustomClass.class)
                && mediaType != null
                && mediaType.getType().equals("text")
                && mediaType.getSubtype().equals("plain");
    }


    @Override
    public List<MediaType> getSupportedMediaTypes() {

        return List.of(MediaType.TEXT_PLAIN);
    }


    @Override
    public Object read(@NonNull Class<?> clazz, @NonNull HttpInputMessage inputMessage) throws IOException {

        String[] fields = BaseReader.read(inputMessage).split("/");

        if (fields.length != 3) {
            throw new HttpMessageNotReadableException(
                    "Message must have exactly 3 elements separated by '/'",
                    inputMessage);
        }
        return new MyCustomClass(fields[0], fields[1], fields[2]);
    }


    @Override
    public void write(Object o, @Nullable MediaType contentType, @NonNull HttpOutputMessage outputMessage) {

        throw new UnsupportedOperationException("Writing is not supported by this converter.");
    }
}
