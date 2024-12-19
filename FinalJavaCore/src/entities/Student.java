package entities;

import enums.Role;

public class Student extends User{
//    Trình độ học vấn.
    private String education_level;

    public Student(String email, String username, String phone, String password, String education_level) {
        super(email, username, phone, password, Role.STUDENT);
        this.education_level = education_level;
    }
}
