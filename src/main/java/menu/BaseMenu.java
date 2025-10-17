package menu;

import org.jline.reader.LineReader;
import org.jline.terminal.Terminal;
import util.Utility;

import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class BaseMenu {
    public final Terminal terminal;
    public final LineReader reader;
    public final PrintWriter writer;
    public final Utility utility;

    protected Map<Character, String> menuOptions = new LinkedHashMap<>();

    public BaseMenu(Terminal terminal, LineReader reader, PrintWriter writer) {
        this.terminal = terminal;
        this.reader = reader;
        this.writer = writer;
        this.utility = new Utility(terminal, reader, writer);
    }

    public abstract void display();

    protected abstract void initializeMenuOptions();

    protected abstract void handleInput(char key);

    public Terminal getTerminal() {return terminal;}
    public LineReader getReader() {return reader;}
    public PrintWriter getWriter() {return writer;}
    public Utility getUtility() {return utility;}

    protected void printMenu() {
        menuOptions.forEach((key, value) -> getWriter().println(utility.centerText("[" + key + "] " + value)));
    }

    protected void menuInstructions() {
        getWriter().println(utility.getGreen() + utility.centerText("Choose a menu option using your keyboard [1,2,3... Q, E]") + utility.getReset());
    }
}
