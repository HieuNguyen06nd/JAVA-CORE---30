package shape;

public class Main {
    public static void main(String[] args) {
        Square square = new Square(4);
        System.out.println("chu vi hinh vuong: "+ square.tinhChuVi());

        System.out.println("dien tich hinh vuong: "+ square.tinhDienTich());

        Rectangle rectangle = new Rectangle(3,4);
        System.out.println("chu vi hinh chu nhat: "+ rectangle.tinhChuVi());

        System.out.println("dien tich hinh chu nhat: "+ rectangle.tinhDienTich());

    }
}
