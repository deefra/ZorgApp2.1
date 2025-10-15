package menu;

import org.jline.terminal.Terminal;

public class MenuManager {

    public static String centerTextTest(String text, Terminal terminal) {
        int width = terminal.getWidth();
        int padding = (width - text.length()) / 2 - 4;
        return " ".repeat(Math.max(0, padding)) + text;
    }
}
