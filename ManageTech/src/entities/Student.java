package entities;

import enums.Role;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Student extends User {
    private String education_level;

    // Constructor không tham số (bắt buộc cho Jackson)
    public Student() {
        super();
    }

    // Constructor có tham số
    public Student(String username, String password, String email, Role role, String education_level) {
        super(username, password, email, Role.STUDENT);
        this.education_level = education_level;
    }

    // Constructor có tham số khác
    public Student(String id, String username, String password, String email, Role role, BigDecimal budget, LocalDate createdAt) {
        super(id, username, password, email, role, budget, createdAt); // Gọi constructor của lớp User
    }

    // Getters and Setters
    public String getEducation_level() {
        return education_level;
    }

    public void setEducation_level(String education_level) {
        this.education_level = education_level;
    }
}