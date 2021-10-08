package hexlet.code;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ObjectNode;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;

public class Differ {
    public static String generate(String filepath1, String filePath2) throws Exception {

        Path Pathfile1 = Paths.get(filepath1);
        Path Pathfile2 = Paths.get(filePath2);

        ObjectMapper mapper = new ObjectMapper();

        TreeMap map1 = mapper.readValue(new File(Pathfile1.toUri()), TreeMap.class);
        TreeMap map2 = mapper.readValue(new File(Pathfile2.toUri()), TreeMap.class);

        TreeMap<String, String> mapAll = new TreeMap();
        mapAll.putAll(map1);
        mapAll.putAll(map2);

        ObjectNode rootNode = mapper.createObjectNode();
        OutputStream outputStream = new ByteArrayOutputStream();

        for (Map.Entry entry : mapAll.entrySet()) {
            String key = (String) entry.getKey();
            if (map2.get(key) == null) {
                rootNode.put("- " + key, entry.getValue().toString());
            } else if (map1.get(key) == null) {
                rootNode.put("+ " + key, entry.getValue().toString());
            } else if (map2.get(key).equals(map1.get(key))) {
                rootNode.put(key, entry.getValue().toString());
            } else if (!map2.get(key).equals(map1.get(key))) {
                rootNode.put("- " + key, map1.get(key).toString());
                rootNode.put("+ " + key, map2.get(key).toString());
            }
        }
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        writer.writeValue(outputStream, rootNode);

        return outputStream.toString();

    }
}