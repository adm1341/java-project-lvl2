package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {

    @Test
    public void testDifferfile1andfile2() throws Exception {
        String expected = "{\n"
                + "  - follow: false\n"
                + "    host: hexlet.io\n"
                + "  - proxy: 123.234.53.22\n"
                + "  - timeout: 50\n"
                + "  + timeout: 20\n"
                + "  + verbose: true\n"
                + "}\n";
        String actual = Differ.generate("src/test/resources/file1.json", "src/test/resources/file2.json");
        assertEquals(expected, actual);
    }

    @Test
    public void testDifferfile1andfile1() throws Exception {
        String expected = "{\n"
                + "    follow: false\n"
                + "    host: hexlet.io\n"
                + "    proxy: 123.234.53.22\n"
                + "    timeout: 50\n"
                + "}\n";
        String actual = Differ.generate("src/test/resources/file1.json", "src/test/resources/file1.json");
        assertEquals(expected, actual);
    }

    @Test
    public void testDifferYMLFile1andFile2() throws Exception {
        String expected = "{\n"
                + "  - follow: false\n"
                + "    host: hexlet.io\n"
                + "  - proxy: 123.234.53.22\n"
                + "  - timeout: 50\n"
                + "  + timeout: 20\n"
                + "  + verbose: true\n"
                + "}\n";
        String actual = Differ.generate("src/test/resources/fileYML1.yml", "src/test/resources/fileYML2.yml");
        assertEquals(expected, actual);
    }

    @Test
    public void testDifferYMLFile1andFile1() throws Exception {
        String expected = "{\n"
                + "    follow: false\n"
                + "    host: hexlet.io\n"
                + "    proxy: 123.234.53.22\n"
                + "    timeout: 50\n"
                + "}\n";
        String actual = Differ.generate("src/test/resources/fileYML1.yml", "src/test/resources/fileYML1.yml");
        assertEquals(expected, actual);
    }
}
