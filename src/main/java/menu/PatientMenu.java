package menu;

import org.jline.reader.LineReader;
import org.jline.terminal.Terminal;
import patient.PatientManager;
import user.User;
import user.UserManager;

import java.io.IOException;
import java.io.PrintWriter;

public class PatientMenu extends BaseMenu {
    private final UserManager userManager;
    private PatientManager patientManager;
    private User currentUser;

    public PatientMenu(Terminal terminal, LineReader reader, PrintWriter writer, UserManager userManager, User currentUser) {
        super(terminal, reader, writer);
        this.patientManager = new PatientManager(getTerminal());
        this.userManager = userManager;
        this.currentUser = currentUser;
    }

    @Override
    protected void initializeMenuOptions () {
        menuOptions.put('1', "Select Patient");
        menuOptions.put('2', "Display All Patients");
        menuOptions.put('3', "Remove Patient");
        menuOptions.put('Q', "Back");
    }

    @Override
    protected void handleInput(char key) {
        switch (key) {
            case '1':
               getWriter().println("Select Patient");
                break;
            case '2':
                clearScreen();
                padding();
                getWriter().println(centerText(getRed() + "Start of the list..." + getReset()));
                padding();
                patientManager.displayAllPatients();
                try {
                    getWriter().println(centerText(getGreen() + "Press any key to continue..." + getReset()));
                    getTerminal().reader().read();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
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
            clearScreen();
            centerAscii(ascii());
            padding();
            getWriter().println(centerText("Patient Menu"));
            padding();
            initializeMenuOptions();
            printMenu();
            padding();
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