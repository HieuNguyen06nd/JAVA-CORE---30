package entities;

import enums.Mode;

import java.math.BigDecimal;

public class Course {
    private static int autoId;

    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private Mode mode;

    public Course( String name, String description,BigDecimal price, Mode mode) {
        this.id ="COU" + ++autoId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.mode = mode;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", mode=" + mode +
                '}';
    }
}
