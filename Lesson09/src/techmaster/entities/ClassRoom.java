package techmaster.entities;

import java.util.ArrayList;

public class ClassRoom {
    private String subject;
    private ArrayList<Student> students;

    public ClassRoom(String subject, ArrayList<Student> students) {
        this.subject = subject;
        this.students = students;
    }

}
