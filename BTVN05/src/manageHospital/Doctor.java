package manageHospital;

import java.util.Date;

public class Doctor extends Person {
    private String specialty;
    private double numberOfWorking;


    public Doctor() {

    }


    public Doctor(String name, int age, String specialty, double numberOfWorking) {
        super(name, age);
        this.specialty = specialty;
        this.numberOfWorking = numberOfWorking;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public double getNumberOfWorking() {
        return numberOfWorking;
    }

    public void setNumberOfWorking(double numberOfWorking) {
        this.numberOfWorking = numberOfWorking;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "name : "+ super.getName()+
                ", Age=" + super.getAge() +
                "specialty='" + specialty + '\'' +
                ", numberOfWorking=" + numberOfWorking +
                '}';
    }
}

