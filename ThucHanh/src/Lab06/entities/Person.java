package Lab06.entities;

import Lab06.enums.Role;

public class Person {
    private static int autoId;

    private String id;
    private String name;
    private String password;
    private String phone;
    private String address;
    private Role role;
    private String email;

    public Person(String address, String name, String password, String phone, Role role, String email) {
        this.address = address;
        this.id ="FPT"+ ++autoId;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.role = role;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
