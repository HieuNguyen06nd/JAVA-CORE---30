package entities;

import enums.Role;

import java.time.LocalDate;

public class Student extends User{
    private String education_level;

    public Student(String username, String password, String email, Role role, String education_level) {
        super(username, password, email, Role.STUDENT);
        this.education_level = education_level;
    }

    public Student(String id, String username, String password, String email, Role role, double budget, LocalDate createdAt) {
        super(id, username, password, email, role, budget, createdAt); // Gọi constructor của lớp User
    }

    public String getEducation_level() {
        return education_level;
    }

    public void setEducation_level(String education_level) {
        this.education_level = education_level;
    }


}
