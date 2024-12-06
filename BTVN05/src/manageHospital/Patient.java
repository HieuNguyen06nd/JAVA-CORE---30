package manageHospital;

import java.time.LocalDate;
import java.util.Date;

public class Patient extends Person{
    private int medicalRecordNumber;
    private LocalDate dateOfAdmission ;

    public Patient() {
    }


    public Patient(String name, int age, int medicalRecordNumber) {
        super(name, age);
        this.medicalRecordNumber = medicalRecordNumber;
        this.dateOfAdmission = LocalDate.now();
    }

    public int getMedicalRecordNumber() {
        return medicalRecordNumber;
    }

    public void setMedicalRecordNumber(int medicalRecordNumber) {
        this.medicalRecordNumber = medicalRecordNumber;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "name : "+ super.getName()+
                ", Age=" + super.getAge() +
                "medicalRecordNumber=" + medicalRecordNumber +
                ", dateOfAdmission=" + dateOfAdmission +
                '}';
    }

}
