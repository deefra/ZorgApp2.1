package menu;

import org.jline.reader.LineReader;
import org.jline.terminal.Terminal;
import user.User;
import user.UserManager;

import java.io.IOException;
import java.io.PrintWriter;

public class MedicationMenu extends BaseMenu {
    private final UserManager userManager;
    private User currentUser;

    public MedicationMenu(Terminal terminal, LineReader reader, PrintWriter writer, UserManager userManager, User currentUser) {
        super(terminal, reader, writer);
        this.userManager = userManager;
        this.currentUser = currentUser;
    }

    @Override
    public void initializeMenuOptions() {
        menuOptions.put('1', "Option 1");
        menuOptions.put('2', "Option 2");
        menuOptions.put('Q', "Logout");
    }

    @Override
    protected void handleInput(char key) {
        switch (key) {
            case '1':
                System.out.println(utility.centerText("case 1"));
                break;
            case '2':
                System.out.println(utility.centerText("case 2"));
                break;
            case 'q':

                break;
            default:
                getWriter().println("Invalid option. Please try again.");
                break;
        }
    }

    @Override
    public void display() {
        boolean running = true;

        while (running) {
            getUtility().clearScreen();
            getUtility().centerAscii(getUtility().ascii());
            getUtility().padding();
            getWriter().println(getUtility().centerText("Medication Menu"));
            getUtility().padding();
            initializeMenuOptions();
            printMenu();
            getUtility().padding();
            menuInstructions();

            try {
                char key = (char) getTerminal().reader().read();
                handleInput(key);
                if (Character.toLowerCase(key) == 'q') {
                    running = false; // Exit to return to MainMenu
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

