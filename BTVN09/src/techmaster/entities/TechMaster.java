package techmaster.entities;

import java.util.ArrayList;

public class TechMaster {
    private String manageClass;
    private String teacher;
    private ArrayList<ClassRoom>classRooms;

    public TechMaster(String manageClass, String teacher, ArrayList<ClassRoom> classRooms) {
        this.manageClass = manageClass;
        this.teacher = teacher;
        this.classRooms = classRooms;
    }

    @Override
    public String toString() {
        return "TechMaster{" +
                "manageClass='" + manageClass + '\'' +
                ", teacher='" + teacher + '\'' +
                ", classRooms=" + classRooms +
                '}';
    }
}
