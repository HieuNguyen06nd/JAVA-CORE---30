package Lab06.entities.fptSchool;

import Lab06.Main;
import Lab06.entities.Person;
import Lab06.enums.Major;
import Lab06.enums.Role;

public class Teacher extends Person {

    private Major major;

    public Teacher(String address, String name, String password, String phone, String email, Major major) {
        super(address, name, password, phone, Role.TEACHER, email);
        this.major = major;
    }
}
