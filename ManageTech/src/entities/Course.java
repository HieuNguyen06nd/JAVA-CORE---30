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
        this.id =generateId();
        this.name = name;
        this.description = description;
        this.price = price;
        this.mode = mode;
    }

    public Course() {
        this.id = generateId(); // Tạo ID tự động ngay cả khi sử dụng constructor không tham số
    }

    private String generateId() {
        return "COU" + ++autoId;
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

}
