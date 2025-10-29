package prescriptions;

public class Prescriptions {
    private int prescriptionId;
    private int patientId;
    private int medicationId;
    private int dosage;
    private boolean narcotic;
    private String comment;

    Prescriptions(int prescriptionId, int patientId, int medicationId, int dosage, String comment, boolean narcotic) {
        this.prescriptionId = prescriptionId;
        this.patientId = patientId;
        this.medicationId = medicationId;
        this.dosage = dosage;
        this.comment = comment;
        this.narcotic = narcotic;
    }

    public int getPrescriptionId() {return prescriptionId;}
    public int getPatientId () {return patientId;}
    public int getMedicationId  () {return medicationId;}
    public int getDosage () {return dosage;}
    public boolean getNarcoticStatus() {return narcotic;}
    public String getComment() {return comment;}

    public void setDosage(int newDosage) {dosage = newDosage;}
    public void setComment (String newComment) {comment = newComment;}

}
