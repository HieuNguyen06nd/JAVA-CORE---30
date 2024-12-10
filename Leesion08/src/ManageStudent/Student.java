package ManageStudent;

public class Student implements IClassification{
    private String name;
    private int age;
    private  double marks;

    public Student(String name, int age, double marks) {
        this.name = name;
        this.age = age;
        this.marks = marks;
    }

    @Override
    public void display() {
        System.out.println("ten: "+name + "\t Tuoi: "+ age + "\t marks: "+ classify());
    }

    @Override
    public String classify() {
        if (marks>=8){
            return "A";
        } else if (marks>=6 && marks<8) {
            return "B";
        }else
            return "C";
    }
}
