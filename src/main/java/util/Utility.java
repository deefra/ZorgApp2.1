package util;

import org.jline.reader.LineReader;
import org.jline.terminal.Terminal;

import java.io.PrintWriter;

public class Utility {
    public final Terminal terminal;
    public final LineReader reader;
    public final PrintWriter writer;

    public Utility(Terminal terminal, LineReader reader, PrintWriter writer) {
        this.terminal = terminal;
        this.reader = reader;
        this.writer = writer;
    }

    public Terminal getTerminal() {
        return terminal;
    }

    public LineReader getReader() {
        return reader;
    }

    public PrintWriter getWriter() {
        return writer;
    }

    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String RESET = "\u001B[0m";

    public String getRed() {return RED;}
    public String getGreen() {return GREEN;}
    public String getReset() {return RESET;}

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void padding() {
        getWriter().println("");
    }

    // STRING & ASCII UTILITY

    public String centerText(String text) {
        int width = getTerminal().getWidth();
        int visibleLength = stripAnsiCodes(text).length();
        int padding = (width - visibleLength) / 2;
        return " ".repeat(Math.max(0, padding)) + text;
    }

    public String centerInput(String text) {
        int width = getTerminal().getWidth();
        int visibleLength = stripAnsiCodes(text).length();
        int padding = (width - visibleLength) / 2;
        return " ".repeat(Math.max(0, padding)) + text;
    }

    private String stripAnsiCodes(String text) {
        return text.replaceAll("\u001B\\[[;\\d]*m", "");
    }

    public String centeredInput(String prompt, boolean isPassword) {
        return isPassword ? getReader().readLine(centerInput(prompt), '*') : getReader().readLine(centerInput(prompt));
    }

    public String centeredInput(String prompt) {
        return centeredInput(prompt, false);
    }

    public String ascii () {
        String ascii = " __   __                  ____                                        \n" +
                " \\ \\ / /__  _   _ _ __   / ___|___  _ __ ___  _ __   __ _ _ __  _   _ \n" +
                "  \\ V / _ \\| | | | '__| | |   / _ \\| '_ ` _ \\| '_ \\ / _` | '_ \\| | | |\n" +
                "   | | (_) | |_| | |    | |__| (_) | | | | | | |_) | (_| | | | | |_| |\n" +
                "   |_|\\___/ \\__,_|_|     \\____\\___/|_| |_| |_| .__/ \\__,_|_| |_|\\__, |\n" +
                "                                             |_|                |___/ ";
        return ascii;
    }

    public void centerAscii(String text) {
        String[] lines = text.split("\n");
        int consoleWidth = getTerminal().getWidth();

        for (String line : lines) {
            int textLength = line.length();
            int padding = Math.max(0, (consoleWidth - textLength) / 2);

            getWriter().println(" ".repeat(padding) + line);
        }
    }
}

