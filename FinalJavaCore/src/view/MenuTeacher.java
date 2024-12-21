package view;

import java.util.Scanner;

public class MenuTeacher {
    public void  displayTeacher(Scanner scanner){
        while (true){
            System.out.println("\n====== MENU CHỨC NĂNG ======");
            System.out.println("1. Quản lý thông tin cá nhân");
            System.out.println("2. Quản lý Lớp học");
            System.out.println("3. Quản lý thông tin cá nhân");
            System.out.println("4. Xem Blog");
            System.out.println("6. Quản lý Score");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 0:
                    System.exit(1);
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng chọn lại!");
            }
        }
    }
}
