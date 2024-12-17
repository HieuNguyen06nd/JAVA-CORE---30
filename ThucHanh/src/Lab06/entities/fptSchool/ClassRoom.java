package Lab06.entities.fptSchool;

import java.util.List;

public class ClassRoom {
    private Teacher teacher;
    private List<Student> students;
    private List<Subject>subjects;

    public ClassRoom(List<Student> students, List<Subject> subjects, Teacher teacher) {
        this.students = students;
        this.subjects = subjects;
        this.teacher = teacher;
    }
}
