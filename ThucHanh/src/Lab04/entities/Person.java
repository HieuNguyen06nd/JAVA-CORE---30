package Lab04.entities;

public abstract class Person {
    private static int autoId;

    private int id;
    private String name;
    private double salary;

    public Person(String name, double salary) {
        this.id = ++autoId;
        this.name = name;
        this.salary = salary;
    }

    public abstract double calSalary();
    public abstract double calTax();

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

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }


}
