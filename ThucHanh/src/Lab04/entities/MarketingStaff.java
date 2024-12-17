package Lab04.entities;

public class MarketingStaff extends Person {
    private double sales;
    private double rate;

    public MarketingStaff(String name, double salary, double sales, double rate) {
        super(name, salary);
        this.sales = sales;
        this.rate = rate;
    }

    public MarketingStaff(String name, double salary) {
        super(name, salary);
    }

    public double getSales() {
        return sales;
    }

    public void setSales(double sales) {
        this.sales = sales;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public double calSalary(){
        return  super.getSalary()+this.sales*this.rate;
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
        return "MarketingStaff{" +
                "id= " + super.getId() +
                ", name= " + super.getName()+
                ", salary= " + calSalary() +
                ", Tax= "+calTax()+
                ", sales= " + sales +
                ", rate= " + rate +
                '}';
    }
}
