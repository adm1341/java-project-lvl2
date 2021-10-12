package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;

public class Formatter {
    public static String formatOut(String format, LinkedHashMap<String, String> mapOut) {
        if (format.equals("stylish")) {
            return stylishOut(mapOut);
        }
        return "";
    }

    private static String stylishOut(LinkedHashMap<String, String> mapOut) {
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
        return (stringBuilder.toString());
    }
}
