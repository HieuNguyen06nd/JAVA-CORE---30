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
}
