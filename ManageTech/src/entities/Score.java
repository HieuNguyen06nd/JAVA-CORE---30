package entities;

import java.time.LocalDate;

public class Score {
    private static int autoId;

    private String id;
    private String teacher_id;
    private String student_id;
    private String lesson_id;
    private double score;
    private LocalDate created_at;
    private LocalDate update_at;

    public Score( String student_id, String lesson_id, double score, String teacher_id, LocalDate created_at, LocalDate update_at) {
        this.id ="S"+ ++autoId;
        this.student_id = student_id;
        this.lesson_id = lesson_id;
        this.score = score;
        this.teacher_id = teacher_id;
        this.created_at = created_at;
        this.update_at = update_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(String lesson_id) {
        this.lesson_id = lesson_id;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }

    public LocalDate getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(LocalDate update_at) {
        this.update_at = update_at;
    }
}
