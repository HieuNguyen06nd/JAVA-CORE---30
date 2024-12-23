package entities;

import enums.Major;
import enums.Role;

import java.util.List;

public class Teacher extends User{
    private double salary;
    private String experience;
    private String bio;
    private List<Major> majors;

    public Teacher(String username, String password, String email, Role role, double salary, String experience, String bio,  List<Major> majors) {
        super(username, password, email, Role.TEACHER);
        this.salary = salary;
        this.experience = experience;
        this.bio = bio;
        this.majors = majors;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<Major> getMajors() {
        return majors;
    }

    public void setMajors(List<Major> majors) {
        this.majors = majors;
    }
}
