package entities;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Classes {
    private static int autoId;

    private String id;
    private String name;
    private String course_id;
    private String teacher_id;
    private List<String> student_id;
    private LocalDate start_date;
    private LocalDate end_date;
    private String schedule;

    public Classes(String name, String course_id, String teacher_id, List<String> student_id, LocalDate start_date, LocalDate end_date, String schedule) {
        this.id ="CLASS"+  ++autoId;
        this.name = name;
        this.course_id = course_id;
        this.teacher_id = teacher_id;
        this.student_id = student_id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.schedule = schedule;
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

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public List<String> getStudent_id() {
        return student_id;
    }

    public void setStudent_id(List<String> student_id) {
        this.student_id = student_id;
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

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
}
