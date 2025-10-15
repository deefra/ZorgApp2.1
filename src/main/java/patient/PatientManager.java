package patient;

import menu.MenuManager;
import org.jline.terminal.Terminal;

import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;

public class PatientManager {

    private final ArrayList<Patient> patients = new ArrayList<>();
    private Terminal terminal;
    private PrintWriter writer;

    public PatientManager(Terminal terminal) {
        this.terminal = terminal;
        this.writer = new PrintWriter(terminal.writer(), true);
        patients.add(new Patient(1, "van Puffelen", "Pierre", LocalDate.of(2000, 12, 20), 75.3, 176, 4.5));
        patients.add(new Patient(2, "van Huffelen", "Dierre", LocalDate.of(1980, 12, 5), 99.9, 148, 4.5));
        patients.add(new Patient(3, "van Ruffelen", "Hierre", LocalDate.of(2000, 12, 1), 60.2, 210, 4.5));
        patients.add(new Patient(4, "van Puffelen", "Pierre", LocalDate.of(2000, 12, 20), 75.3, 176, 4.5));
        patients.add(new Patient(5, "van Huffelen", "Dierre", LocalDate.of(1980, 12, 5), 99.9, 148, 4.5));
        patients.add(new Patient(6, "van Ruffelen", "Hierre", LocalDate.of(2000, 12, 1), 60.2, 210, 4.5));
        patients.add(new Patient(7, "van Puffelen", "Pierre", LocalDate.of(2000, 12, 20), 75.3, 176, 4.5));
        patients.add(new Patient(8, "van Huffelen", "Dierre", LocalDate.of(1980, 12, 5), 99.9, 148, 4.5));
        patients.add(new Patient(9, "van Ruffelen", "Hierre", LocalDate.of(2000, 12, 1), 60.2, 210, 4.5));
        patients.add(new Patient(10, "van Puffelen", "Pierre", LocalDate.of(2000, 12, 20), 75.3, 176, 4.5));
        patients.add(new Patient(11, "van Huffelen", "Dierre", LocalDate.of(1980, 12, 5), 99.9, 148, 4.5));
        patients.add(new Patient(12, "van Ruffelen", "Hierre", LocalDate.of(2000, 12, 1), 60.2, 210, 4.5));
        patients.add(new Patient(13, "van Puffelen", "Pierre", LocalDate.of(2000, 12, 20), 75.3, 176, 4.5));
        patients.add(new Patient(14, "van Huffelen", "Dierre", LocalDate.of(1980, 12, 5), 99.9, 148, 4.5));
        patients.add(new Patient(15, "van Ruffelen", "Hierre", LocalDate.of(2000, 12, 1), 60.2, 210, 4.5));
        patients.add(new Patient(16, "van Puffelen", "Pierre", LocalDate.of(2000, 12, 20), 75.3, 176, 4.5));
        patients.add(new Patient(17, "van Huffelen", "Dierre", LocalDate.of(1980, 12, 5), 99.9, 148, 4.5));
        patients.add(new Patient(18, "van Ruffelen", "Hierre", LocalDate.of(2000, 12, 1), 60.2, 210, 4.5));
        patients.add(new Patient(19, "van Puffelen", "Pierre", LocalDate.of(2000, 12, 20), 75.3, 176, 4.5));
        patients.add(new Patient(20, "van Huffelen", "Dierre", LocalDate.of(1980, 12, 5), 99.9, 148, 4.5));
        patients.add(new Patient(21, "van Ruffelen", "Hierre", LocalDate.of(2000, 12, 1), 60.2, 210, 4.5));
    }

    public PrintWriter getWriter() {return writer;}
    public Terminal getTerminal() {return terminal;}

    public void displayAllPatients () {
        for (Patient p : patients) {
            displayPatient(p);
            getWriter().println(); // Padding
        }
    }

    public void displayPatient(Patient patient) {

        getWriter().println(MenuManager.centerTextTest("ID: " + patient.getId(), getTerminal()));
        getWriter().println(MenuManager.centerTextTest("Surname: " + patient.getSurname(), getTerminal()));
        getWriter().println(MenuManager.centerTextTest("First name: " + patient.getFirstName(), getTerminal()));
        getWriter().println(MenuManager.centerTextTest("Date of birth: " + patient.getDateOfBirth(), getTerminal()));

//        System.out.println(MenuManager.centerTextTest(String.format("s", "ID:", patient.getId()), getTerminal()));
//        System.out.println(MenuManager.centerTextTest(String.format("%-17s %s", "Surname:", patient.getSurname()), getTerminal()));
//        System.out.println(MenuManager.centerTextTest(String.format("%-17s %s", "First name:", patient.getFirstName()), getTerminal()));
//        System.out.println(MenuManager.centerTextTest(String.format("%-17s %s", "Date of birth:", patient.getDateOfBirth()), getTerminal()));
    }


    public void displayDetailedPatient(PrintWriter writer, String centerText, Patient patient) {
        writer.println();
        writer.println(centerText + String.format("%-17s %s", "ID:", patient.getId()));
        writer.println(centerText + String.format("%-17s %s", "Surname:", patient.getSurname()));
        writer.println(centerText + String.format("%-17s %s", "First name:", patient.getFirstName()));
        writer.println(centerText + String.format("%-17s %s", "Date of birth:", patient.getDateOfBirth()));
        writer.println(centerText + String.format("%-17s %d", "Age:", patient.getAge()));
        writer.println(centerText + String.format("%-17s %.1f", "Weight(KG):", patient.getWeight()));
        writer.println(centerText + String.format("%-17s %d", "Height(CM):", patient.getHeight()));
        writer.println(centerText + String.format("%-17s %.1f", "BMI:", patient.getBMI()));
        writer.println(centerText + String.format("%-17s %.1f", "Lung volume(L):", patient.getLungVolume()));
        writer.println();
    }
}
