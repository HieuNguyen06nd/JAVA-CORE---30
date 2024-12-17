package Lab06.entities.fptShop;

import Lab06.entities.Person;
import Lab06.enums.Role;

public class Staff extends Person {

    public Staff(String address, String name, String password, String phone, String email) {
        super(address, name, password, phone, Role.STAFF, email);
    }
}
