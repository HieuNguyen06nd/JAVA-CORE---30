public class StudentBiz extends TechStudent {
    private double marketting, sales;

    public StudentBiz(String name, String study, double marketting, double sales) {
        super(name, study);
        this.marketting = marketting;
        this.sales = sales;
    }

    @Override
    public double getScore() {
        return (2*marketting + sales)/3;
    }

}
