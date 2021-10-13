package hexlet.code.formatters;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.module.SimpleModule;
import hexlet.code.CustomDifferObjectSerializer;
import hexlet.code.DiffObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;


public class FormatterJson {

    public static String formatOut(ArrayList<DiffObject> diffArray) throws IOException {

        OutputStream outputStream = new ByteArrayOutputStream();
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule("CustomDifferObjectSerializer", new Version(1, 0, 0, null, null, null));
        module.addSerializer(DiffObject.class, new CustomDifferObjectSerializer());
        objectMapper.registerModule(module);
        ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());
        writer.writeValue(outputStream, diffArray);


        return (outputStream.toString());
    }
}

