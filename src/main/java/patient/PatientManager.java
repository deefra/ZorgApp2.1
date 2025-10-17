package patient;

import menu.BaseMenu;
import menu.MenuManager;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import util.Utility;

import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PatientManager {

    private final ArrayList<Patient> patients = new ArrayList<>();
    private Terminal terminal;
    private PrintWriter writer;
    private LineReader reader;
    public Utility utility;

    public PatientManager(Terminal terminal) {
        this.terminal = terminal;
        this.writer = new PrintWriter(terminal.writer(), true);
        this.reader = LineReaderBuilder.builder().terminal(terminal).build();
        this.utility = new Utility(terminal, reader, getWriter());


        patients.add(new Patient(1, "van Puffelen", "Pierre", LocalDate.of(2000, 12, 20), 75.3, 176, 4.5));
        patients.add(new Patient(2, "van Huffelen", "Dierre", LocalDate.of(1980, 12, 5), 99.9, 148, 4.5));
        patients.add(new Patient(3, "van Ruffelen", "Hierre", LocalDate.of(2000, 12, 1), 60.2, 210, 4.5));
//        patients.add(new Patient(4, "van Puffelen", "Pierre", LocalDate.of(2000, 12, 20), 75.3, 176, 4.5));
//        patients.add(new Patient(5, "van Huffelen", "Dierre", LocalDate.of(1980, 12, 5), 99.9, 148, 4.5));
//        patients.add(new Patient(6, "van Ruffelen", "Hierre", LocalDate.of(2000, 12, 1), 60.2, 210, 4.5));
//        patients.add(new Patient(7, "van Puffelen", "Pierre", LocalDate.of(2000, 12, 20), 75.3, 176, 4.5));
//        patients.add(new Patient(8, "van Huffelen", "Dierre", LocalDate.of(1980, 12, 5), 99.9, 148, 4.5));
//        patients.add(new Patient(9, "van Ruffelen", "Hierre", LocalDate.of(2000, 12, 1), 60.2, 210, 4.5));
//        patients.add(new Patient(10, "van Puffelen", "Pierre", LocalDate.of(2000, 12, 20), 75.3, 176, 4.5));
//        patients.add(new Patient(11, "van Huffelen", "Dierre", LocalDate.of(1980, 12, 5), 99.9, 148, 4.5));
//        patients.add(new Patient(12, "van Ruffelen", "Hierre", LocalDate.of(2000, 12, 1), 60.2, 210, 4.5));
//        patients.add(new Patient(13, "van Puffelen", "Pierre", LocalDate.of(2000, 12, 20), 75.3, 176, 4.5));
//        patients.add(new Patient(14, "van Huffelen", "Dierre", LocalDate.of(1980, 12, 5), 99.9, 148, 4.5));
//        patients.add(new Patient(15, "van Ruffelen", "Hierre", LocalDate.of(2000, 12, 1), 60.2, 210, 4.5));
//        patients.add(new Patient(16, "van Puffelen", "Pierre", LocalDate.of(2000, 12, 20), 75.3, 176, 4.5));
//        patients.add(new Patient(17, "van Huffelen", "Dierre", LocalDate.of(1980, 12, 5), 99.9, 148, 4.5));
//        patients.add(new Patient(18, "van Ruffelen", "Hierre", LocalDate.of(2000, 12, 1), 60.2, 210, 4.5));
//        patients.add(new Patient(19, "van Puffelen", "Pierre", LocalDate.of(2000, 12, 20), 75.3, 176, 4.5));
//        patients.add(new Patient(20, "van Huffelen", "Dierre", LocalDate.of(1980, 12, 5), 99.9, 148, 4.5));
//        patients.add(new Patient(21, "van Ruffelen", "Hierre", LocalDate.of(2000, 12, 1), 60.2, 210, 4.5));
    }

    public PrintWriter getWriter() {return writer;}
    public Terminal getTerminal() {return terminal;}

    public Patient searchPatientByName (String patientName) {
        String cleanPatientName = patientName.toLowerCase().replace(" ", "");

        for (Patient p : patients) {
            String searchResult = p.getFirstName().toLowerCase().replace(" ", "") + p.getSurname().toLowerCase().replace(" ", "");
            if (searchResult.equals(cleanPatientName)) {
                return p;
            }
        } return null;
    }

    public Patient searchPatientById (int patientId) {
        for (Patient p : patients) {
            if (p.getId()==patientId) {
                return p;
            }
        } return null;
    }

    public Patient searchPatient (String nameOrId) {
        Patient foundPatient = null;

        try {
            int id = Integer.parseInt(nameOrId);
            foundPatient = searchPatientById(id);

        } catch (NumberFormatException e) {
            foundPatient = searchPatientByName(nameOrId);

        }
        return foundPatient;
    }

    public List<Patient> searchPatientsByPartialName(String partialName) {
        String cleanPartialName = partialName.toLowerCase().replace(" ", "");
        List<Patient> matches = new ArrayList<>();

        for (Patient p : patients) {
            String patientFullName = (p.getFirstName() + p.getSurname()).toLowerCase().replace(" ", "");
            if (patientFullName.contains(cleanPartialName)) {
                matches.add(p);
            }
        }

        return matches;
    }

    public void displayPartialMatches(List<Patient> partialResults) {

        if (partialResults.isEmpty()) {
            getWriter().println(utility.centerText(utility.getRed() + "No matches found." + utility.getReset()));
        } else {
            utility.clearScreen();
            getWriter().println(utility.centerText(utility.getGreen() + "Matches found:" + utility.getReset()));
            utility.padding();
            for (Patient p : partialResults) {
                getWriter().println(utility.centerText("ID:" + p.getId() + " " + p.getFirstName() + " " + p.getSurname()));
            }
        }
    }

    public void displayAllPatients () {
        for (Patient p : patients) {
            displayPatient(p);
            getWriter().println(); // Padding
        }
    }

    public void displayPatient(Patient patient) {
        getWriter().println(utility.centerText("ID: " + patient.getId()));
        getWriter().println(utility.centerText("Surname: " + patient.getSurname()));
        getWriter().println(utility.centerText("First name: " + patient.getFirstName()));
        getWriter().println(utility.centerText("Date of birth: " + patient.getDateOfBirth()));
    }


    public void displayDetailedPatient(String centerText, Patient patient) {
        getWriter().println();
        getWriter().println(centerText + String.format("%-17s %s", "ID:", patient.getId()));
        getWriter().println(centerText + String.format("%-17s %s", "Surname:", patient.getSurname()));
        getWriter().println(centerText + String.format("%-17s %s", "First name:", patient.getFirstName()));
        getWriter().println(centerText + String.format("%-17s %s", "Date of birth:", patient.getDateOfBirth()));
        getWriter().println(centerText + String.format("%-17s %d", "Age:", patient.getAge()));
        getWriter().println(centerText + String.format("%-17s %.1f", "Weight(KG):", patient.getWeight()));
        getWriter().println(centerText + String.format("%-17s %d", "Height(CM):", patient.getHeight()));
        getWriter().println(centerText + String.format("%-17s %.1f", "BMI:", patient.getBMI()));
        getWriter().println(centerText + String.format("%-17s %.1f", "Lung volume(L):", patient.getLungVolume()));
        getWriter().println();
    }
}
