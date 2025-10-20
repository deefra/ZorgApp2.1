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

    public PatientMenu(Terminal terminal, LineReader reader, PrintWriter writer, UserManager userManager, PatientManager patientManager) {
        super(terminal, reader, writer);
        this.userManager = userManager;
        this.patientManager = patientManager;
        this.currentUser = userManager.getCurrentUser();
        this.currentPatient = patientManager.getCurrentPatient();
    }

    @Override
    protected void initializeMenuOptions () {
        menuOptions.put('1', "Patient data");
//        menuOptions.put('2', "Patient prescriptions");
//        menuOptions.put('3', "Remove Patient");
        menuOptions.put('Q', "Back");
    }

    @Override
    protected void handleInput(char key) {
        switch (key) {
            case '1':
                boolean viewingPatient = true;
                while (viewingPatient) {
                    getUtility().clearScreen();
                    getUtility().padding();
                    patientManager.displayDetailedPatient(currentPatient);
                    getUtility().padding();
                    getWriter().println(getUtility().centerText(getUtility().getGreen() + "[E] Edit Patient [Q] Back" + getUtility().getReset()));

                    try {
                        char action = (char) getTerminal().reader().read();
                        switch (Character.toLowerCase(action)) {
                            case 'e':
                                getUtility().padding();
                                patientManager.editPatient(currentPatient);
                                break;
                            case 'q':
                                viewingPatient = false;
                                break;
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                break;
            case '2':

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
            getWriter().println(getUtility().centerText("Selected patient: " + Patient.getFullName(currentPatient)));
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