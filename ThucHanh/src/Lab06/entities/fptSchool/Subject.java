package Lab06.entities.fptSchool;

import Lab06.enums.TypeSubject;

public class Subject{

    private int id;
    private String name;
    private int numberOfCredits;
    private TypeSubject type;

    public Subject(int id, String name, int numberOfCredits, TypeSubject type) {
        this.id = id;
        this.name = name;
        this.numberOfCredits = numberOfCredits;
        this.type = type;
    }
}
