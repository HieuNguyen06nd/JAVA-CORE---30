package entities;

import enums.Role;

import java.math.BigDecimal;
import java.time.LocalDate;


public class User {
    private static int autoId = 0;

    private String id; // Trường id
    private String username;
    private String password;
    private String email;
    private Role role;
    private BigDecimal budget;
    private LocalDate created_at;

    // Constructor mặc định
    public User() {
        this.id = generateId(); // Tạo ID mới
        this.budget = new BigDecimal(10000); // Khởi tạo budget
        this.created_at = LocalDate.now(); // Khởi tạo created_at
    }

    // Constructor có tham số
    public User(String username, String password, String email, Role role) {
        this.id = generateId(); // Tạo ID mới
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.budget = new BigDecimal(10000); // Khởi tạo budget
        this.created_at = LocalDate.now(); // Khởi tạo created_at
    }

    // Phương thức tạo ID tự động
    private String generateId() {
        return "USER" + ++autoId;
    }

    // Getter và Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }
}