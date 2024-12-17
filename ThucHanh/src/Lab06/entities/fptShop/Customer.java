package Lab06.entities.fptShop;

import Lab06.entities.Person;
import Lab06.enums.Role;

import java.util.ArrayList;
import java.util.List;

public class Customer extends Person {
    private double balance;
    private List<Orders> orders;

    public Customer(String address, String name, String password, String phone, String email, double balance, List<Orders> orders) {
        super(address, name, password, phone, Role.CUSTOMER, email);
        this.balance = balance;
        this.orders = new ArrayList<>();
    }
}
