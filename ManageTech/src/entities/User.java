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
    private LocalDate created_at = LocalDate.now();

    public User( String username, String password, String email, Role role) {
        this.id =""+ autoId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role =role;
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
}
