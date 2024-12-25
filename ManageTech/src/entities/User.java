package entities;

import enums.Role;

import java.time.LocalDate;

public class User {
    private static int autoId;

    private String id;
    private String username;
    private String password;
    private String email;
    private Role role;
    private double budget;
    private LocalDate created_at = LocalDate.now();

    public User( String username, String password, String email, Role role) {
        this.id ="KH"+ ++autoId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.budget = 1000;
        this.role =role;
    }
    public User(String id, String username, String password, String email, Role role, double budget, LocalDate created_at) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.budget = budget;
        this.created_at = created_at; // Đảm bảo tạo từ LocalDate
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at() {
        this.created_at = LocalDate.now();
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", budget=" + budget +
                ", created_at=" + created_at.toString() + // Chuyển đổi LocalDate thành String
                '}';
    }
}
