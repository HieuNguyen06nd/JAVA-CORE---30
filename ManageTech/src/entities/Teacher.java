package entities;

import enums.Major;
import enums.Role;

public class Teacher extends User{
    private double salary;
    private String experience;
    private String bio;
    private Major major;

    public Teacher(String username, String password, String email, Role role, double salary, String experience, String bio, Major major) {
        super(username, password, email, Role.TEACHER);
        this.salary = salary;
        this.experience = experience;
        this.bio = bio;
        this.major = major;
    }
}
