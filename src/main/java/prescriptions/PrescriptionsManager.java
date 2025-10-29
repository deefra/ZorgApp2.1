package prescriptions;

import medication.Medication;
import medication.MedicationManager;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import patient.Patient;
import user.User;
import util.Utility;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionsManager {
    private ArrayList<Prescriptions> prescriptions = new ArrayList<>();
    private Terminal terminal;
    private PrintWriter writer;
    private LineReader reader;
    private Utility utility;

    public PrescriptionsManager(Terminal terminal) {
        this.terminal = terminal;
        this.writer = new PrintWriter(terminal.writer(), true);
        this.reader = LineReaderBuilder.builder().terminal(terminal).build();
        this.utility = new Utility(terminal, reader, writer);

        // Initialize prescriptions
        prescriptions.add(new Prescriptions(1, 1, 1, 300, "If you tweak, take a leak.", false));
        prescriptions.add(new Prescriptions(2, 2, 2, 150, "", true));
        prescriptions.add(new Prescriptions(3, 2, 3, 100, "First dosage in the morning...", true));
    }

    public List<Prescriptions> getPrescriptionsForPatient(Patient patient) {
        List<Prescriptions> patientPrescriptions = new ArrayList<>();
        for (Prescriptions p : prescriptions) {
            if (p.getPatientId() == patient.getId()) {
                patientPrescriptions.add(p);
            }
        }
        return patientPrescriptions;
    }

    public void displayPrescriptionsForPatient(Patient patient, MedicationManager medicationManager) {
        List<Prescriptions> patientPrescriptions = getPrescriptionsForPatient(patient);

        if (patientPrescriptions.isEmpty()) {
            writer.println(utility.centerText("No prescriptions found for this patient"));
            return;
        }

        for (Prescriptions p : patientPrescriptions) {
            Medication med = medicationManager.searchMedicationByID(p.getMedicationId());
            String medName = (med != null) ? med.getName() : "Unknown";
            writer.println(utility.centerText(p.getPrescriptionId() + " | " + medName + " | " + p.getDosage() + " (MG)"));
            utility.padding();
            writer.println(utility.centerText(utility.getGreen() + "ID" + " | " + "Name" + " | " + "Dosage" +  utility.getReset()));
        }
    }
}


