package entities;

import enums.Major;
import enums.Role;

import java.util.List;

public class Teacher extends User {
    private double salary;
    private String experience;
    private String bio;
    private List<Major> majors;

    public Teacher(String username, String password, String email, Role role, double salary, String experience, String bio, List<Major> majors) {
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
    public double getSalary() {
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
}