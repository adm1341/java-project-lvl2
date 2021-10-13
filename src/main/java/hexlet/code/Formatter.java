package hexlet.code;

import hexlet.code.formatters.FormatterJson;
import hexlet.code.formatters.FormatterPlain;
import hexlet.code.formatters.FormatterStylish;

import java.io.IOException;
import java.util.ArrayList;

public class Formatter {
    public static String formatOut(String format, ArrayList<DiffObject> diffArray) throws IOException {
        switch (format) {
            case "stylish":
                return FormatterStylish.formatOut(diffArray);
            case "plain":
                return FormatterPlain.formatOut(diffArray);
            case "json":
                return FormatterJson.formatOut(diffArray);
            default:
                throw new IllegalStateException("Unexpected value: " + format);
        }
    }


}
