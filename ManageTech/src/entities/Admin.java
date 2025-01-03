package entities;

import enums.Role;

public class Admin extends User{
    public Admin(String username, String password, String email, Role role) {
        super(username, password, email, role);
    }

    public Admin() {
    }
}
