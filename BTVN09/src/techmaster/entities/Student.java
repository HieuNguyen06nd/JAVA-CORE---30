package techmaster.entities;

public class Student {
    private int id;
    private String name;
    private int age;
    private String hocLuc;

    public Student(int id, String name, int age, String hocLuc) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.hocLuc = hocLuc;
    }

    public Student() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getHocLuc() {
        return hocLuc;
    }

    public void setHocLuc(String hocLuc) {
        this.hocLuc = hocLuc;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", hocLuc='" + hocLuc + '\'' +
                '}';
    }
}
