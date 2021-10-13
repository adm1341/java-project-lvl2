package hexlet.code;

import hexlet.code.formatters.FormatterPlain;
import hexlet.code.formatters.FormatterStylish;

import java.util.ArrayList;

public class Formatter {
    public static String formatOut(String format, ArrayList<DiffObject> diffArray) {
        switch (format) {
            case "stylish":
                return FormatterStylish.formatOut(diffArray);
            case "plain":
                return FormatterPlain.formatOut(diffArray);
            default:
                throw new IllegalStateException("Unexpected value: " + format);
        }
    }


}
