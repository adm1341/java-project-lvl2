package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.TreeMap;

public class Parser {
    public static TreeMap parseInMap(String format, File file) throws IOException {
        ObjectMapper mapper;
        if (format.equals("yml")) {
            mapper = new ObjectMapper(new YAMLFactory());
        } else {
            mapper = new ObjectMapper();
        }
        return mapper.readValue(file, TreeMap.class);
    }
}
