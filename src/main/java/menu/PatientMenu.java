package menu;

import org.jline.reader.LineReader;
import org.jline.terminal.Terminal;
import patient.Patient;
import patient.PatientManager;
import user.User;
import user.UserManager;

import java.io.IOException;
import java.io.PrintWriter;

public class PatientMenu extends BaseMenu {
    private final UserManager userManager;
    private PatientManager patientManager;
    private User currentUser;
    private Patient currentPatient;

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
               try {
                   getUtility().padding();
                   String nameOrId = getUtility().centeredInput("Enter patient name or ID > ");
                   currentPatient = patientManager.searchPatient(nameOrId);

                   if (currentPatient != null) {

                   } else {
                       patientManager.displayPartialMatches(patientManager.searchPatientsByPartialName(nameOrId));
                       try {
                           getTerminal().reader().read();
                       } catch (IOException e) {
                           throw new RuntimeException(e);
                       }

                   }
               } catch (Exception e) {
                   throw new RuntimeException(e);
               }

                break;
            case '2':
                getUtility().clearScreen();
                getUtility().padding();
                getWriter().println(getUtility().centerText(getUtility().getRed() + "Start of the list..." + getUtility().getReset()));
                getUtility().padding();
                patientManager.displayAllPatients();
                try {
                    getWriter().println(getUtility().centerText(getUtility().getGreen() + "Press any key to continue..." + getUtility().getReset()));
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
            getUtility().clearScreen();
            getUtility().centerAscii(getUtility().ascii());
            getUtility().padding();
            getWriter().println(getUtility().centerText("Patient Menu"));
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