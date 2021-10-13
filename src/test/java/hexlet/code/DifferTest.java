package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {

    @Test
    public void testDifferfile1andfile2() throws IOException {
        String expected = "{\n"
                + "  - follow: false\n"
                + "    host: hexlet.io\n"
                + "  - proxy: 123.234.53.22\n"
                + "  - timeout: 50\n"
                + "  + timeout: 20\n"
                + "  + verbose: true\n"
                + "}\n";
        String actual = Differ.generate("src/test/resources/file1.json", "src/test/resources/file2.json", "stylish");
        assertEquals(expected, actual);
    }

    @Test
    public void testDifferfile1andfile1() throws IOException {
        String expected = "{\n"
                + "    follow: false\n"
                + "    host: hexlet.io\n"
                + "    proxy: 123.234.53.22\n"
                + "    timeout: 50\n"
                + "}\n";
        String actual = Differ.generate("src/test/resources/file1.json", "src/test/resources/file1.json", "stylish");
        assertEquals(expected, actual);
    }

    @Test
    public void testDifferYMLFile1andFile2() throws IOException {
        String expected = "{\n"
                + "  - follow: false\n"
                + "    host: hexlet.io\n"
                + "  - proxy: 123.234.53.22\n"
                + "  - timeout: 50\n"
                + "  + timeout: 20\n"
                + "  + verbose: true\n"
                + "}\n";
        String actual;
        actual = Differ.generate("src/test/resources/fileYML1.yml", "src/test/resources/fileYML2.yml", "stylish");
        assertEquals(expected, actual);
    }

    @Test
    public void testDifferYMLFile1andFile1() throws IOException {
        String expected = "{\n"
                + "    follow: false\n"
                + "    host: hexlet.io\n"
                + "    proxy: 123.234.53.22\n"
                + "    timeout: 50\n"
                + "}\n";
        String actual;
        actual = Differ.generate("src/test/resources/fileYML1.yml", "src/test/resources/fileYML1.yml", "stylish");
        assertEquals(expected, actual);
    }

    @Test
    public void testDifferBigFile1andFile2() throws IOException {
        String expected = "{\n"
                + "    chars1: [a, b, c]\n"
                + "  - chars2: [d, e, f]\n"
                + "  + chars2: false\n"
                + "  - checked: false\n"
                + "  + checked: true\n"
                + "  - default: null\n"
                + "  + default: [value1, value2]\n"
                + "  - id: 45\n"
                + "  + id: null\n"
                + "  - key1: value1\n"
                + "  + key2: value2\n"
                + "    numbers1: [1, 2, 3, 4]\n"
                + "  - numbers2: [2, 3, 4, 5]\n"
                + "  + numbers2: [22, 33, 44, 55]\n"
                + "  - numbers3: [3, 4, 5]\n"
                + "  + numbers4: [4, 5, 6]\n"
                + "  + obj1: {nestedKey=value, isNested=true}\n"
                + "  - setting1: Some value\n"
                + "  + setting1: Another value\n"
                + "  - setting2: 200\n"
                + "  + setting2: 300\n"
                + "  - setting3: true\n"
                + "  + setting3: none\n"
                + "}\n";
        String actual;
        actual = Differ.generate("src/test/resources/file1Big.json", "src/test/resources/file2Big.json", "stylish");
        assertEquals(expected, actual);
    }

    @Test
    public void testDifferYMLBigFile1andFile2() throws IOException {
        String expected = "{\n"
                + "    chars1: [a, b, c]\n"
                + "  - chars2: [d, e, f]\n"
                + "  + chars2: false\n"
                + "  - checked: false\n"
                + "  + checked: true\n"
                + "  - default: null\n"
                + "  + default: [value1, value2]\n"
                + "  - id: 45\n"
                + "  + id: null\n"
                + "  - key1: value1\n"
                + "  + key2: value2\n"
                + "    numbers1: [1, 2, 3, 4]\n"
                + "  - numbers2: [2, 3, 4, 5]\n"
                + "  + numbers2: [22, 33, 44, 55]\n"
                + "  - numbers3: [3, 4, 5]\n"
                + "  + numbers4: [4, 5, 6]\n"
                + "  + obj1: {nestedKey=value, isNested=true}\n"
                + "  - setting1: Some value\n"
                + "  + setting1: Another value\n"
                + "  - setting2: 200\n"
                + "  + setting2: 300\n"
                + "  - setting3: true\n"
                + "  + setting3: none\n"
                + "}\n";
        String actual;
        actual = Differ.generate("src/test/resources/file1BigYML.yml", "src/test/resources/file2BigYML.yml", "stylish");
        assertEquals(expected, actual);
    }

    @Test
    public void testDifferPlainBigFile1andFile2() throws IOException {
        String expected = "\n"
                + "Property 'chars2' was updated. From [complex value] to false\n"
                + "Property 'checked' was updated. From false to true\n"
                + "Property 'default' was updated. From null to [complex value]\n"
                + "Property 'id' was updated. From 45 to null\n"
                + "Property 'key1' was removed\n"
                + "Property 'key2' was added with value: 'value2'\n"
                + "Property 'numbers2' was updated. From [complex value] to [complex value]\n"
                + "Property 'numbers3' was removed\n"
                + "Property 'numbers4' was added with value: [complex value]\n"
                + "Property 'obj1' was added with value: [complex value]\n"
                + "Property 'setting1' was updated. From 'Some value' to 'Another value'\n"
                + "Property 'setting2' was updated. From 200 to 300\n"
                + "Property 'setting3' was updated. From true to 'none'\n";
        String actual;
        actual = Differ.generate("src/test/resources/file1Big.json", "src/test/resources/file2Big.json", "plain");
        assertEquals(expected, actual);
    }

    @Test
    public void testDifferJSONBigFile1andFile2() throws IOException {

        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get("src/test/resources/JsonExpect.json"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String fileString = sb.toString();

        String expected = fileString.substring(0, fileString.length() - 1);
        String actual;
        actual = Differ.generate("src/test/resources/file1Big.json", "src/test/resources/file2Big.json", "json");
        assertEquals(expected, actual);
    }
}
