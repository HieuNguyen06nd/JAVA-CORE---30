package entities;

import enums.Role;

public class Student extends User {
    private String education_level;

    public Student(String username, String password, String email, Role role, String education_level) {
        super(username, password, email, role);
        this.education_level = education_level;
    }

    public Student() {
        super(); // Gọi constructor mặc định của lớp cha (User)
    }

    // Các phương thức getter và setter
    public String getEducation_level() {
        return education_level;
    }
}