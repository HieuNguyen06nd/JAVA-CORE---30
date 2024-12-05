package shape;

public class Square extends ShapeService {
    private double canh;

    public Square() {
    }

    public Square(double canh) {
        this.canh = canh;
    }

    @Override
    public double tinhChuVi() {
        return canh*4;
    }

    @Override
    public double tinhDienTich() {
        return canh*canh;
    }
}
