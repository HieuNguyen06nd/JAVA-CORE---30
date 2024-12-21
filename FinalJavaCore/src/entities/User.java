package entities;

import enums.Role;

import java.time.LocalDate;

public class User {

    private static int autoID;

    private String id;
    private String username;
    private String email;
    private String phone;
    private String password;
    private Role role;
    private int status;
    private LocalDate created_at;

    public User() {
    }

    public User(String email, String username, String phone, String password, Role role) {
        this.email = email;
        this.id = "KH" + ++autoID;
        this.username = username;
        this.phone = phone;
        this.password = password;
        this.role = role;
        this.status =1;
        this.created_at=LocalDate.now();
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", status=" + status +
                ", created_at=" + created_at +
                '}';
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }
}
