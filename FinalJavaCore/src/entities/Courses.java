package entities;

import java.time.LocalDate;

public class Courses {
    private static int autoID;

    private String id;
    private String title;
    private String description;
    private double price;

    private String teacherId;
//thoi gian bat dau
    private LocalDate start_date;
//thoi gian ket thuc
    private LocalDate end_date;
}
