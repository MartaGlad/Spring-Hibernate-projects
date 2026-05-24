package com.gladysz.converter.converters.object;

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

public class MyCustomObjectConverter implements HttpMessageConverter<Object> {

    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType) {

        return clazz.equals(MyCustomClass.class)
                && mediaType != null
                && mediaType.getType().equals("text")
                && mediaType.getSubtype().equals("object");
    }


    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {

        return clazz.equals(MyCustomClass.class)
                && mediaType != null
                && mediaType.getType().equals("text")
                && mediaType.getSubtype().equals("object");
    }


    @Override
    public List<MediaType> getSupportedMediaTypes() {

        return List.of(new MediaType("text","object"));
    }


    @Override
    public Object read(@NonNull Class<?> clazz, @NonNull HttpInputMessage inputMessage) throws IOException {

        String[] fields = getStrings(inputMessage);

        if(fields.length != 3) {
            throw new HttpMessageNotReadableException(
                    "Message must have exactly 3 fields with values",
                    inputMessage
            );
        }

        String field1 = getValue(fields[0], inputMessage);
        String field2 = getValue(fields[1], inputMessage);
        String field3 = getValue(fields[2], inputMessage);

        return new MyCustomClass(field1, field2, field3);
    }


    private static String @NonNull [] getStrings(@NonNull HttpInputMessage inputMessage) throws IOException {

        String text = BaseReader.read(inputMessage).trim();

        if(!text.startsWith("MyCustomClass(") || !text.endsWith(")")) {
            throw new HttpMessageNotReadableException(
                    "Message must have format MyCustomClass(key=\"value\",key=\"value\",key=\"value\")",
                    inputMessage
            );
        }

        text = text.replace("MyCustomClass(", "").replace(")","");

        return text.split(",");
    }


    private static String getValue(String field, HttpInputMessage inputMessage) {

        String[] parts = field.split("=",2);

        if(parts.length != 2) {
            throw new HttpMessageNotReadableException(
                    "Each field must have format key=\"value\"",
                    inputMessage
            );
        }
        return parts[1].replace("\"", "").trim();
    }


    @Override
    public void write(Object o, @Nullable MediaType contentType, HttpOutputMessage outputMessage) {

        throw new UnsupportedOperationException("Writing is not supported by this converter.");
    }
}
