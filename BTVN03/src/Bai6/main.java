package Bai6;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Mời nhập a");
        double a =Double.parseDouble(scanner.nextLine());
        System.out.println("Mời nhập b");
        double b =Double.parseDouble(scanner.nextLine());

        System.out.println("Mời nhập phép tính (+, - *, / %)");
        String choose =scanner.nextLine();

        switch (choose){
            case "+":
                System.out.println("a+b = "+ (a+b));
                break;
            case "-":
                System.out.println("a-b = "+ (a-b));
                break;
            case "*":
                System.out.println("a*b = "+ (a*b));
                break;
            case "/":
                if (b ==0){
                    System.out.println("Không thể chia cho 0");
                }else {
                    System.out.println("a/b = "+ (a/b));
                }
                break;
            case "%":
                if (b ==0){
                    System.out.println("Không thể chia cho 0");
                }else {
                    System.out.println("a%b = " + (a % b));
                }
                break;
            default:
                System.out.println("Phép toán không hợp lệ");
        }
    }
}
