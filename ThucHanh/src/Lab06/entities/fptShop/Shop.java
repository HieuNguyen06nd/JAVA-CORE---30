package Lab06.entities.fptShop;

import java.util.List;

public class Shop {

    private List<Product>products;
    private List<Staff>staff;
    private List<Customer>customers;
    private List<Orders>orders;

    public Shop(List<Customer> customers, List<Product> products, List<Staff> staff, List<Orders> orders) {
        this.customers = customers;
        this.products = products;
        this.staff = staff;
        this.orders = orders;
    }
}
