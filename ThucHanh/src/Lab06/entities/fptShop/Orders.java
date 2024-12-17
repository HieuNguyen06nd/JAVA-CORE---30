package Lab06.entities.fptShop;

import java.time.LocalDate;

public class Orders {

    private static int autoId;

    private int id;
    private LocalDate orderDate;
    private String status;
    private double total;

    private Customer customer;
    private Product product;

    public Orders(Customer customer, Product product, String status, double total) {
        this.customer = customer;
        this.id = ++autoId;
        this.orderDate = LocalDate.now();
        this.product = product;
        this.status = status;
        this.total = total;
    }
}
