package entities;

import java.util.List;

public class ClassRoom {
    private static int autoId;

    private String id;
    private String name;
    private String teacherId;
    private String courseId;
    List<String> studentId;
    List<String> lessonId;

    public ClassRoom(String teacherId,String name, String courseId, List<String> studentId, List<String> lessonId) {
        this.id = "CLASS" + ++autoId;
        this.name = name;
        this.teacherId = teacherId;
        this.courseId=courseId;
        this.studentId = studentId;
        this.lessonId = lessonId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public List<String> getStudentId() {
        return studentId;
    }

    public void setStudentId(List<String> studentId) {
        this.studentId = studentId;
    }

    public List<String> getLessonId() {
        return lessonId;
    }

    public void setLessonId(List<String> lessonId) {
        this.lessonId = lessonId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}
