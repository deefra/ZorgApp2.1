package patient;

import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.Period;

class Patient {

    private int       id;
    private String    surname;
    private String    firstName;
    private LocalDate dateOfBirth;
    private Double    weight;
    private int   height;
    private Double   lungVolume;

    public void setFirstname(String name) { firstName = name; }
    public void setSurname(String lastName) { surname = lastName; }
    public void setDateofBirth(LocalDate dob) { dateOfBirth = dob; }
    public void setWeight(Double setWeight) { weight = setWeight; }
    public void setHeight(int setHeight) { height = setHeight; }

    public int getId () {return id;}
    public String getFirstName() {return firstName;}
    public String getSurname() {return surname;}
    public LocalDate getDateOfBirth() {return dateOfBirth;}
    public Double getWeight() {return weight;}
    public int getHeight() {return height;}
    public Double getLungVolume() { return lungVolume; }

    Patient(int id, String surname, String firstName, LocalDate dateOfBirth,  Double Weight, int Height, double lungVolume) {
        this.id = id;
        this.surname = surname;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.weight = Weight;
        this.height = Height;
        this.lungVolume = lungVolume;
    }

    double getBMI() {
        if (weight == null) {
            return 0;
        }
        double heightM = (double) height / 100;
        return  weight /  (heightM * heightM);
    }

    int getAge() {
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(dateOfBirth, currentDate);
        return period.getYears();
    }
}
