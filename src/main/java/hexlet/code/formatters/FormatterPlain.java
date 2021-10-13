package hexlet.code.formatters;

import hexlet.code.DiffObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;


public class FormatterPlain {
    public static String formatOut(ArrayList<DiffObject> diffArray) {

        StringBuilder stringBuilder = new StringBuilder();

        for (DiffObject diffObject : diffArray) {
            String stringVal1 = null;
            if (diffObject.getVal1().isPresent()) {
                if (diffObject.getVal1().get() instanceof String) {
                    stringVal1 = "'" + diffObject.getVal1().get() + "'";
                } else if (diffObject.getVal1().get() instanceof ArrayList) {
                    stringVal1 = "[complex value]";
                } else if (diffObject.getVal1().get() instanceof LinkedHashMap) {
                    stringVal1 = "[complex value]";
                } else {
                    stringVal1 = diffObject.getVal1().get().toString();
                }
            }
            String stringVal2 = null;
            if (diffObject.getVal2().isPresent()) {
                if (diffObject.getVal2().get() instanceof String) {
                    stringVal2 = "'" + diffObject.getVal2().get() + "'";
                } else if (diffObject.getVal2().get() instanceof ArrayList) {
                    stringVal2 = "[complex value]";
                } else if (diffObject.getVal2().get() instanceof LinkedHashMap) {
                    stringVal2 = "[complex value]";
                } else {
                    stringVal2 = diffObject.getVal2().get().toString();
                }
            }

            switch (diffObject.getAction()) {
                case "removed":
                    stringBuilder.append("Property '");
                    stringBuilder.append(diffObject.getKey());
                    stringBuilder.append("' was removed");
                    stringBuilder.append("\n");
                    break;
                case "added":
                    stringBuilder.append("Property '");
                    stringBuilder.append(diffObject.getKey());
                    stringBuilder.append("' was added with value: ");
                    stringBuilder.append(stringVal2);
                    stringBuilder.append("\n");
                    break;
                case "nothing":
                    break;
                case "updated":
                    stringBuilder.append("Property '");
                    stringBuilder.append(diffObject.getKey());
                    stringBuilder.append("' was updated. From ");
                    stringBuilder.append(stringVal1);
                    stringBuilder.append(" to ");
                    stringBuilder.append(stringVal2);
                    stringBuilder.append("\n");
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + diffObject.getAction());
            }
        }
        String stringBuilderString = stringBuilder.toString();



        return  stringBuilderString.substring(0, stringBuilderString.length() - 1);
    }
}
