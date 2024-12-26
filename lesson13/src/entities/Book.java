package entities;

public class Book {
    private static int autoId;

    private String id;
    private String name;
    private String auth;
    private double price;

    public Book( String name, String auth, double price) {
        this.id ="B"+ ++autoId;
        this.name = name;
        this.auth = auth;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", auth='" + auth + '\'' +
                ", price=" + price +
                '}';
    }
}
