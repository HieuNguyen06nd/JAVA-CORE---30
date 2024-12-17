package Lab06.entities.fptShop;

public class Product {
    private static int autoId;

    private int id;
    private String name;
    private String status;
    private int quantity;
    private double price;
    private String description;

    public Product( String name, String status, int quantity,double price, String description) {
        this.id = ++autoId;
        this.name = name;
        this.status = status;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
    }
}
