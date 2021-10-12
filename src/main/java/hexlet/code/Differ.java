package hexlet.code;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class Differ {
    public static String generate(String filepath1, String filePath2) throws Exception {


        Path pathFile1 = Paths.get(filepath1);
        Path pathFile2 = Paths.get(filePath2);

        String format1 = getExtensionByStringHandling(filepath1);
        String format2 = getExtensionByStringHandling(filepath1);

        TreeMap map1 = Parser.parseInMap(format1, new File(pathFile1.toUri()));

        TreeMap map2 = Parser.parseInMap(format2, new File(pathFile2.toUri()));

        TreeMap<String, String> mapAll = new TreeMap();
        mapAll.putAll(map1);
        mapAll.putAll(map2);


        LinkedHashMap<String, String> mapOut = new LinkedHashMap<>();
        for (Map.Entry entry : mapAll.entrySet()) {
            String key = (String) entry.getKey();
            if (map2.get(key) == null) {
                mapOut.put("- " + key, entry.getValue().toString());
            } else if (map1.get(key) == null) {
                mapOut.put("+ " + key, entry.getValue().toString());
            } else if (map2.get(key).equals(map1.get(key))) {
                mapOut.put("  " + key, entry.getValue().toString());
            } else if (!map2.get(key).equals(map1.get(key))) {
                mapOut.put("- " + key, map1.get(key).toString());
                mapOut.put("+ " + key, map2.get(key).toString());
            }
        }

        return writeOutString(mapOut);

    }

    static String writeOutString(LinkedHashMap<String, String> mapOut) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\n");
        for (Map.Entry<String, String> entry : mapOut.entrySet()) {
            stringBuilder.append("  ");
            stringBuilder.append(entry.getKey());
            stringBuilder.append(": ");
            stringBuilder.append(entry.getValue());
            stringBuilder.append("\n");
        }
        stringBuilder.append("}\n");
        return stringBuilder.toString();
    }

    static String getExtensionByStringHandling(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1)).get();
    }
}
