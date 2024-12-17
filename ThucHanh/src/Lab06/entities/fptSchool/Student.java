package Lab06.entities.fptSchool;

import Lab06.entities.Person;
import Lab06.enums.Role;

public class Student extends Person {
    private double avgScore;

    public Student(String address, String name, String password, String phone, String email, double avgScore) {
        super(address, name, password, phone, Role.STUDENT, email);
        this.avgScore = avgScore;
    }
}
