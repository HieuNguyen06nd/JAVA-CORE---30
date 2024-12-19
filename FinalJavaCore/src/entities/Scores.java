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

    public Scores( String student_id, String lesson_id, String course_id, double score) {
        this.id ="SC" + ++autoId;
        this.student_id = student_id;
        this.lesson_id = lesson_id;
        this.course_id = course_id;
        this.score = score;
        this.graded_at = LocalDate.now();
    }

    public String getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(String lesson_id) {
        this.lesson_id = lesson_id;
    }

    public String getId() {
        return id;
    }


    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public LocalDate getGraded_at() {
        return graded_at;
    }

    public void setGraded_at(LocalDate graded_at) {
        this.graded_at = graded_at;
    }
}
