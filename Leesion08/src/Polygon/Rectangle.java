package Polygon;

public class Rectangle implements Polygon{
    private double length;
    private double width;


    @Override
    public void display() {
        System.out.println("dien tich: "+calArea());
    }

    @Override
    public double calArea() {
       return length*width;
    }

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }
}
