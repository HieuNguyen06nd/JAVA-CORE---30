package entities;

import java.time.LocalDate;

public class Scores {
    private static int autoId;

    private String id;
    private String student_id;
    private String lesson_id;
    private String course_id;
    private double score;

    private LocalDate graded_at;

}
