package hexlet.code;

import org.junit.jupiter.api.Test;

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
                + "}";
        String actual = Differ.generate(getFullPath("file1.json"), getFullPath("file2.json"), "stylish");
        assertEquals(expected, actual);
    }

    @Test
    public void testDifferfile1andfile1() throws IOException {
        String expected = "{\n"
                + "    follow: false\n"
                + "    host: hexlet.io\n"
                + "    proxy: 123.234.53.22\n"
                + "    timeout: 50\n"
                + "}";
        String actual = Differ.generate(getFullPath("file1.json"), getFullPath("file1.json"), "stylish");
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
                + "}";
        String actual;
        actual = Differ.generate(getFullPath("fileYML1.yml"), getFullPath("fileYML2.yml"), "stylish");
        assertEquals(expected, actual);
    }

    @Test
    public void testDifferYMLFile1andFile1() throws IOException {
        String expected = "{\n"
                + "    follow: false\n"
                + "    host: hexlet.io\n"
                + "    proxy: 123.234.53.22\n"
                + "    timeout: 50\n"
                + "}";
        String actual;
        actual = Differ.generate(getFullPath("fileYML1.yml"), getFullPath("fileYML1.yml"), "stylish");
        assertEquals(expected, actual);
    }

    @Test
    public void testDifferBigFile1andFile2() throws IOException {
        String expected = Files.readString(Paths.get(getFullPath("stylishExpect.txt")));
        String actual;
        actual = Differ.generate(getFullPath("file1Big.json"), getFullPath("file2Big.json"), "stylish");
        assertEquals(expected, actual);
    }

    @Test
    public void testDifferYMLBigFile1andFile2() throws IOException {
        String expected = Files.readString(Paths.get(getFullPath("stylishExpect.txt")));
        String actual;
        actual = Differ.generate(getFullPath("file1BigYML.yml"), getFullPath("file2BigYML.yml"), "stylish");
        assertEquals(expected, actual);
    }

    @Test
    public void testDifferYMLBigFile1andFile2NoFormat() throws IOException {
        String expected = Files.readString(Paths.get(getFullPath("stylishExpect.txt")));
        String actual;
        actual = Differ.generate(getFullPath("file1BigYML.yml"), getFullPath("file2BigYML.yml"));
        assertEquals(expected, actual);
    }

    @Test
    public void testDifferPlainBigFile1andFile2() throws IOException {
        String expected = "Property 'chars2' was updated. From [complex value] to false\n"
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
                + "Property 'setting3' was updated. From true to 'none'";
        String actual;
        actual = Differ.generate(getFullPath("file1Big.json"), getFullPath("file2Big.json"), "plain");
        assertEquals(expected, actual);
    }

    @Test
    public void testDifferJSONBigFile1andFile2() throws IOException {

        String expected = Files.readString(Paths.get(getFullPath("JsonExpect.json")));
        String actual;
        actual = Differ.generate(getFullPath("file1Big.json"), getFullPath("file2Big.json"), "json");
        assertEquals(expected, actual);
    }

    public static String getFullPath(String fileName) {
        return "src/test/resources/" + fileName;
    }
}
