package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import static java.lang.System.out;


@Command(
        name = "gendiff",
        description = "Compares two configuration files and shows a difference.")
public class App {
    @Option(names = {"-h", "--help"}, description = "Show this help message and exit.", help = true)
    boolean help;

    @Option(names = {"-V", "--version"}, description = "Print version information and exit.", help = true)
    boolean version;

    public static void main(String[] args) {
        final App main = CommandLine.populateCommand(new App(), args);
        if (main.help) {
            CommandLine.usage(main, out, CommandLine.Help.Ansi.AUTO);
        }
    }

}
