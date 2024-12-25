package entities;

import java.time.LocalDate;

public class Attendance {
    private static int autoId;
    private String id;
    private String student_id;
    private String lesson_id;
    private boolean isPresent; // true = có mặt, false = vắng mặt
    private LocalDate date;

    public Attendance(String student_id, String lesson_id, boolean isPresent, LocalDate date) {
        this.id = "ATT" + ++autoId;
        this.student_id = student_id;
        this.lesson_id = lesson_id;
        this.isPresent = isPresent;
        this.date = date;
    }

}
