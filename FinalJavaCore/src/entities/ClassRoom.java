package entities;

import java.util.List;

public class ClassRoom {
    private static int autoId;

    private String id;
    private String name;
    private String teacherId;
    List<String> studentId;
    List<String> subjectId;

    public ClassRoom(String teacherId,String name, List<String> studentId, List<String> subjectId) {
        this.id = "CLASS" + ++autoId;
        this.name = name;
        this.teacherId = teacherId;
        this.studentId = studentId;
        this.subjectId = subjectId;
    }
}
