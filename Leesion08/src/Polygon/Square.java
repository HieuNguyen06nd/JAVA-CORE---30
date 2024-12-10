package Polygon;

public class Square implements Polygon{
    private double side;

    public Square(double side) {
        this.side = side;
    }

    @Override
    public void display() {
        System.out.println(calArea());
    }

    @Override
    public double calArea() {
        return side*side;
    }
}
