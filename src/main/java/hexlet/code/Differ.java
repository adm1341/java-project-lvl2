package hexlet.code;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class Differ {
    public static String generate(String filepath1, String filePath2, String format) throws Exception {


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

            Optional val1 = Optional.ofNullable(map1.get(key));
            Optional val2 = Optional.ofNullable(map2.get(key));

            String value;
            if (entry.getValue() != null) {
                value = entry.getValue().toString();
            } else {
                value = null;
            }
            if (!map2.containsKey(key)) {
                mapOut.put("- " + key, value);
            } else if (!map1.containsKey(key)) {
                mapOut.put("+ " + key, value);
            } else if (val2.equals(val1)) {
                mapOut.put("  " + key, value);
            } else if (!val2.equals(val1)) {
                mapOut.put("- " + key, val1.isPresent() ? val1.get().toString() : null);
                mapOut.put("+ " + key, val2.isPresent() ? val2.get().toString() : null);
            }
        }

        return Formatter.formatOut(format, mapOut);

    }

    static String getExtensionByStringHandling(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1)).get();
    }
}
