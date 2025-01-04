package entities;

import enums.Major;
import enums.Role;

import java.math.BigDecimal;
import java.util.List;

public class Teacher extends User {
    private BigDecimal salary;
    private String experience;
    private String bio;
    private List<Major> majors;

    public Teacher(String username, String password, String email, Role role, BigDecimal salary, String experience, String bio, List<Major> majors) {
        super(username, password, email, role);
        this.salary = salary;
        this.experience = experience;
        this.bio = bio;
        this.majors = majors;
    }
    public Teacher() {
        super(); // Gọi constructor mặc định của lớp cha (User)
    }

    // Các phương thức getter và setter
    public BigDecimal getSalary() {
        return salary;
    }

    public String getExperience() {
        return experience;
    }

    public String getBio() {
        return bio;
    }

    public List<Major> getMajors() {
        return majors;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setMajors(List<Major> majors) {
        this.majors = majors;
    }
}