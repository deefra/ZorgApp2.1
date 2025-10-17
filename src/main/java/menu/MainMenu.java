package menu;

import user.User;
import user.UserManager;
import org.jline.reader.LineReader;
import org.jline.terminal.Terminal;

import java.io.IOException;
import java.io.PrintWriter;

public class MainMenu extends BaseMenu {
    private final UserManager userManager;
    private User currentUser;

    public MainMenu(Terminal terminal, LineReader reader, PrintWriter writer, UserManager userManager, User currentUser) {
        super(terminal, reader, writer);
        this.userManager = userManager;
        this.currentUser = currentUser;
    }


    @Override
    protected void initializeMenuOptions () {
        menuOptions.put('1', "Patient menu");
        menuOptions.put('2', "Medication menu");
        menuOptions.put('Q', "Logout");
    }

    @Override
    protected void handleInput(char key) {
        switch (key) {
            case '1':
                PatientMenu patientMenu = new PatientMenu(terminal, reader, writer, userManager, currentUser);
                patientMenu.display();
                break;
            case '2':
                MedicationMenu medicationMenu = new MedicationMenu(terminal, reader, writer, userManager, currentUser);
                medicationMenu.display();
                break;
            case 'q':
            case 'Q':
                userManager.logout();
                break;
            default:
                getWriter().println("Invalid option. Please try again.");
                break;
        }
    }

    public void display() {
        boolean running = true;

        while (running) {
            getUtility().clearScreen();
            getUtility().centerAscii(getUtility().ascii());
            getUtility().padding();
            getWriter().println(getUtility().centerText("Logged in as " + currentUser.getUsername()));
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