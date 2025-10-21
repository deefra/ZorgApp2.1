package menu;

import patient.Patient;
import patient.PatientManager;
import user.User;
import user.UserManager;
import org.jline.reader.LineReader;
import org.jline.terminal.Terminal;

import java.io.IOException;
import java.io.PrintWriter;

public class MainMenu extends BaseMenu {
    public final UserManager userManager;
    public PatientManager patientManager;
    public User currentUser;
    public Patient currentPatient;

    public MainMenu(Terminal terminal, LineReader reader, PrintWriter writer, UserManager userManager, User currentUser) {
        super(terminal, reader, writer);
        this.patientManager = new PatientManager(getTerminal());
        this.userManager = userManager;
        this.currentUser = currentUser;
    }


    @Override
    protected void initializeMenuOptions () {
        menuOptions.put('1', "Select patient");
        menuOptions.put('2', "Medication menu");
        menuOptions.put('Q', "Logout");
    }

    @Override
    protected void handleInput(char key) {
        switch (key) {
            case '1':
                try {
                    getUtility().clearScreen();
                    getUtility().padding();
                    getWriter().println(getUtility().centerText(getUtility().getGreen() + "Type 'quit' to return" + getUtility().getReset()));
                    getUtility().padding();
                    String nameOrId = getUtility().centeredInput("Enter patient name or ID > ");

                    while (true) {
                        if (nameOrId.equalsIgnoreCase("quit")) {
                            break;
                        }

                        currentPatient = patientManager.searchPatient(nameOrId);

                        if (currentPatient != null) {
                            patientManager.setCurrentPatient(currentPatient);
                            PatientMenu patientMenu = new PatientMenu(getTerminal(), getReader(), getWriter(), userManager, patientManager);
                            patientMenu.display();
                            break;
                        } else {
                            getUtility().clearScreen();
                            getUtility().padding();
                            getWriter().println(getUtility().centerText(getUtility().getGreen() + "Type 'quit' to return" + getUtility().getReset()));
                            // No exact match found, show partial matches
                            patientManager.displayPartialMatches(patientManager.searchPatientsByPartialName(nameOrId));
                            getUtility().padding();
                            nameOrId = getUtility().centeredInput("Enter patient name or ID > ");
                        }
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
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