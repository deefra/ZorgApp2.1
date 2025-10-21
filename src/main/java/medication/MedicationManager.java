package medication;

import java.io.PrintWriter;
import java.util.ArrayList;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import patient.Patient;
import util.Utility;

public class MedicationManager {

    private Terminal terminal;
    private PrintWriter writer;
    private LineReader reader;
    public Utility utility;
    public Patient currentPatient;

    ArrayList<Medication> medications = new ArrayList<>();

    public MedicationManager(Terminal terminal) {
        medications.add(new Medication(1, "Fentanyl", 100));
        medications.add(new Medication(2, "Ketamine", 100));
        medications.add(new Medication(3, "Xanax", 100));

        this.terminal = terminal;
        this.writer = new PrintWriter(terminal.writer(), true);
        this.reader = LineReaderBuilder.builder().terminal(terminal).build();
        this.utility = new Utility(terminal, reader, writer);
    }

    public void getAllMedication() {
        for (Medication m : medications) {
            displayAllMedication(m);
        }
    }

    public void displayAllMedication(Medication medication) {
        writer.println(utility.centerText(medication.getId() + " | " + medication.getName() + " | " + medication.getQuantity()));
//        writer.println(utility.centerText("ID: " + medication.getId()));
//        writer.println(utility.centerText("Name: " + medication.getName()));
//        writer.println(utility.centerText("Quantity: " + medication.getQuantity()));
    }
}
