package shape;

public class Rectangle extends ShapeService {
    private double length;
    private double width;


    @Override
    public double tinhDienTich() {
        return length*width;
    }

    @Override
    public double tinhChuVi() {
        return (length+width)*2;
    }

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }
}
