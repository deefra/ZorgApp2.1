package patient;

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
    public Patient currentPatient;

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
    public Patient getCurrentPatient() {return currentPatient;}

    public void setCurrentPatient(Patient currentPatient) {
        this.currentPatient = currentPatient;
    }

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
            utility.padding();
            getWriter().println(utility.centerText(utility.getRed() + "No matches found" + utility.getReset()));
        } else {
            utility.padding();
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


    public void displayDetailedPatient(Patient patient) {
        getWriter().println();

        String line1 = String.format("%20s %-29s", "ID:", patient.getId());
        String line2 = String.format("%20s %-29s", "Surname:", patient.getSurname());
        String line3 = String.format("%20s %-29s", "First name:", patient.getFirstName());
        String line4 = String.format("%20s %-29s", "Date of birth:", patient.getDateOfBirth());
        String line5 = String.format("%20s %-29s", "Age:", patient.getAge() + " years");
        String line6 = String.format("%20s %-29s", "Weight:", String.format("%.1f kg", patient.getWeight()));
        String line7 = String.format("%20s %-29s", "Height:", patient.getHeight() + " cm");
        String line8 = String.format("%20s %-29s", "BMI:", String.format("%.1f", patient.getBMI()));
        String line9 = String.format("%20s %-29s", "Lung volume:", String.format("%.1f L", patient.getLungVolume()));

        // Add a few extra spaces to shift the block
        String extraPadding = "     ";  // Adjust this to move left or right

        getWriter().println(extraPadding + utility.centerText(line1));
        getWriter().println(extraPadding + utility.centerText(line2));
        getWriter().println(extraPadding + utility.centerText(line3));
        getWriter().println(extraPadding + utility.centerText(line4));
        getWriter().println(extraPadding + utility.centerText(line5));
        getWriter().println(extraPadding + utility.centerText(line6));
        getWriter().println(extraPadding + utility.centerText(line7));
        getWriter().println(extraPadding + utility.centerText(line8));
        getWriter().println(extraPadding + utility.centerText(line9));

        getWriter().println();
    }

    public void editPatient (Patient patient) {
        utility.getWriter().println(utility.centerText(utility.getRed() + "Leave empty to keep current value!" + utility.getReset()));
        utility.padding();

        String newName = utility.centeredInput("New first name > ");
        if (!newName.isEmpty()) {
            patient.setFirstname(newName);
        }
        utility.padding();

        String newSurname = utility.centeredInput("New surname > ");
        if (!newSurname.isEmpty()) {
            patient.setSurname(newSurname);
        }
        utility.padding();

        String newDate = utility.centeredInput("Date of birth (YYYY-MM-DD) > ");
        if (!newDate.isEmpty()) {
            try {
                patient.setDateofBirth(LocalDate.parse(newDate));
            } catch (Exception e) {
                utility.getWriter().println(utility.centerText(
                        utility.getRed() + "Invalid date format, keeping current value" + utility.getReset()
                ));
            }
        }
        utility.padding();

        String newWeight = utility.centeredInput("Weight (kg) > ");
        if (!newWeight.isEmpty()) {
            try {
                patient.setWeight(Double.parseDouble(newWeight));
            } catch (NumberFormatException e) {
                utility.getWriter().println(utility.centerText(
                        utility.getRed() + "Invalid number, keeping current value" + utility.getReset()
                ));
            }
        }
        utility.padding();

        String newHeight = utility.centeredInput("Height (cm) > ");
        if (!newHeight.isEmpty()) {
            try {
                patient.setHeight(Integer.parseInt(newHeight));
            } catch (NumberFormatException e) {
                utility.getWriter().println(utility.centerText(
                        utility.getRed() + "Invalid number, keeping current value" + utility.getReset()
                ));
            }
        }
        utility.padding();

        utility.getWriter().println(utility.centerText(
                utility.getGreen() + "Patient updated successfully!" + utility.getReset()
        ));
    }
}
