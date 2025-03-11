package BTVN;

public class Student {

    public String fullName;
    public int age;
    public int GPA;

    public Student(String fullName, int age, int GPA) {
        this.fullName = fullName;
        this.age = age;
        this.GPA = GPA;
    }

    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" +
                "fullName='" + fullName + '\'' +
                ", age=" + age +
                ", GPA=" + GPA +
                '}';
    }
}
