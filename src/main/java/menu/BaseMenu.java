package menu;

import org.jline.reader.LineReader;
import org.jline.terminal.Terminal;

import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class BaseMenu {
    public Terminal terminal;
    public final LineReader reader;
    public final PrintWriter writer;

    protected Map<Character, String> menuOptions = new LinkedHashMap<>();

    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String RESET = "\u001B[0m";

    public BaseMenu(Terminal terminal, LineReader reader, PrintWriter writer) {
        this.terminal = terminal;
        this.reader = reader;
        this.writer = writer;
    }

    public abstract void display();
    protected abstract void initializeMenuOptions();
    protected abstract void handleInput(char key);

    public Terminal getTerminal() {return terminal;}
    public LineReader getReader () {return reader;}
    public PrintWriter getWriter () {return writer;}

    protected String getRed() {return RED;}
    protected String getGreen() {return GREEN;}
    protected String getReset() {return RESET;}

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    protected void printMenu () {
        menuOptions.forEach((key, value) -> getWriter().println(centerText("[" + key + "] " + value)));
    }

    protected void menuInstructions () {
        getWriter().println(getGreen() + centerText("Choose a menu option using your keyboard [1,2,3... Q, E]") + getReset());
    }

    // STYLING

    protected void padding() {
        getWriter().println("");
    }

    // STRING & ASCII UTILITY

    protected String centerText(String text) {
        int width = getTerminal().getWidth();
        int padding = (width - text.length()) / 2 - 4;
        return " ".repeat(Math.max(0, padding)) + text;
    }

    protected String centerInput(String text) {
        int width = getTerminal().getWidth();
        int padding = (width - text.length()) / 2 - 6;
        return " ".repeat(Math.max(0, padding)) + text;
    }

    protected String centeredInput(String prompt, boolean isPassword) {
        String input = isPassword ? getReader().readLine(centerInput(prompt), '*') : getReader().readLine(centerInput(prompt));
        return input;
    }

    protected String centeredInput(String prompt) {
        return centeredInput(prompt, false);
    }

    protected String ascii () {
        String ascii = " __   __                  ____                                        \n" +
                " \\ \\ / /__  _   _ _ __   / ___|___  _ __ ___  _ __   __ _ _ __  _   _ \n" +
                "  \\ V / _ \\| | | | '__| | |   / _ \\| '_ ` _ \\| '_ \\ / _` | '_ \\| | | |\n" +
                "   | | (_) | |_| | |    | |__| (_) | | | | | | |_) | (_| | | | | |_| |\n" +
                "   |_|\\___/ \\__,_|_|     \\____\\___/|_| |_| |_| .__/ \\__,_|_| |_|\\__, |\n" +
                "                                             |_|                |___/ ";
        return ascii;
    }

    protected void centerAscii(String text) {
        String[] lines = text.split("\n");
        int consoleWidth = getTerminal().getWidth();

        for (String line : lines) {
            int textLength = line.length();
            int padding = Math.max(0, (consoleWidth - textLength) / 2);

            getWriter().println(" ".repeat(padding) + line);
        }
    }
}
