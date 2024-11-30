package Bai9;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String choose="";

        do {
            System.out.println("Nhập tên");
            String name = scanner.nextLine();
            System.out.println("Nhập địa chỉ");
            String address = scanner.nextLine();
            System.out.println("học sinh tên : " + name + "address : " +address);

            System.out.println("Do you want to continues? (Y/N)");
            choose = scanner.nextLine();
        }while (choose.equalsIgnoreCase("y"));
    }
}
