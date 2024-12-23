package entities;

import java.time.LocalDate;

public class Score {
    private static int autoId;

    private String id;
    private String student_id;
    private String lesson_id;
    private double score;
    private LocalDate created_at;
    private LocalDate update_at;
}
