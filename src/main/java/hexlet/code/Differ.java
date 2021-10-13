package hexlet.code;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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


        ArrayList<DiffObject> diffArray = new ArrayList<>();

        for (Map.Entry entry : mapAll.entrySet()) {
            String key = (String) entry.getKey();

            Optional val1 = Optional.ofNullable(map1.get(key));
            Optional val2 = Optional.ofNullable(map2.get(key));
            String val1String = val1.isPresent() ? val1.get().toString() : null;
            String val2String = val2.isPresent() ? val2.get().toString() : null;

            if (!map2.containsKey(key)) {
                diffArray.add(new DiffObject(key, "removed", val1, val2));
            } else if (!map1.containsKey(key)) {
                diffArray.add(new DiffObject(key, "added", val1, val2));
            } else if (val2.equals(val1)) {
                diffArray.add(new DiffObject(key, "nothing", val1, val2));
            } else if (!val2.equals(val1)) {
                diffArray.add(new DiffObject(key, "updated", val1, val2));
            }
        }

        return Formatter.formatOut(format, diffArray);

    }

    static String getExtensionByStringHandling(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1)).get();
    }
}
