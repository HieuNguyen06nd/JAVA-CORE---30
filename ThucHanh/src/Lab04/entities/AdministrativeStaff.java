package Lab04.entities;

public class AdministrativeStaff extends Person {

    public AdministrativeStaff(String name, double salary) {
        super(name, salary);
    }

    @Override
    public double calSalary() {
        return super.getSalary();
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
        return "AdministrativeStaff{}"+
                "id=" + super.getId() +
                ", name " + super.getName()+
                ", salary= " + calSalary()+
                ", Tax= "+calTax();
    }
}
