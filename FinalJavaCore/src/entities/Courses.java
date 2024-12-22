package entities;

import java.time.LocalDate;

public class Courses {
    private static int autoID;

    private String id;
    private String title;
    private String description;
    private double price;
    private String user_id;
    //thoi gian bat dau
    private LocalDate start_date;
    //thoi gian ket thuc
    private LocalDate end_date;

    public Courses(String title, String description, double price, String user_id, LocalDate start_date, LocalDate end_date) {
        this.id = "COU" + ++autoID;
        this.title = title;
        this.description = description;
        this.price = price;
        this.user_id = user_id;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }
}
