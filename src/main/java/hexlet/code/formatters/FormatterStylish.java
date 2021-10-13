package hexlet.code.formatters;

import hexlet.code.DiffObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class FormatterStylish {
    public static String formatOut(ArrayList<DiffObject> diffArray) {

        Map<String, String> mapOut = new LinkedHashMap<>();

        for (DiffObject diffObject : diffArray) {

            String val1String = diffObject.getVal1().isPresent() ? diffObject.getVal1().get().toString() : null;
            String val2String = diffObject.getVal2().isPresent() ? diffObject.getVal2().get().toString() : null;

            switch (diffObject.getAction()) {
                case "removed":
                    mapOut.put("- " + diffObject.getKey(), val1String);
                    break;
                case "added":
                    mapOut.put("+ " + diffObject.getKey(), val2String);
                    break;
                case "nothing":
                    mapOut.put("  " + diffObject.getKey(), val2String);
                    break;
                case "updated":
                    mapOut.put("- " + diffObject.getKey(), val1String);
                    mapOut.put("+ " + diffObject.getKey(), val2String);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + diffObject.getAction());
            }
        }

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
