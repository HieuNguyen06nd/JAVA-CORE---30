package Lab04.entities;

public class DepartmentHead extends Person {
    private double responsibilitySalary;

    public DepartmentHead(String name, double salary, double responsibilitySalary) {
        super(name, salary);
        this.responsibilitySalary = responsibilitySalary;
    }

    public DepartmentHead(String name, double salary) {
        super(name, salary);
    }

    public double getResponsibilitySalary() {
        return responsibilitySalary;
    }

    public void setResponsibilitySalary(double responsibilitySalary) {
        this.responsibilitySalary = responsibilitySalary;
    }
    @Override
    public double calSalary(){
        return super.getSalary()+this.responsibilitySalary;
    }

    @Override
    public double calTax() {
        if (calSalary() < 11000000) {
            return 0;
        } else {
            return calSalary() * 0.1;
        }
    }

    @Override
    public String toString() {
        return "DepartmentHead{" +
                "id= " + super.getId() +
                ", name " + super.getName()+
                ", salary= " + calSalary() +
                ", Tax= "+calTax()+
                "responsibilitySalary= " + responsibilitySalary +
                '}';
    }
}
