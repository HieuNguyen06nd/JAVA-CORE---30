package view;

import entities.User;
import enums.Role;
import service.AppContext;
import service.EnrollmentService;
import service.PrintService;

import java.util.Scanner;

public class MenuCustomer {
    PrintService printService = new PrintService();
    EnrollmentService enrollmentService = new EnrollmentService();
    MenuStudent menu = new MenuStudent();
    public void displayCustomer(AppContext context, User user){
        while (true){
            System.out.println("\n====== MENU CHỨC NĂNG ======");
            System.out.println("1. Quản lý thông tin cá nhân");
            System.out.println("2. Xem tất cả các khóa học");
            System.out.println("3. Đăng ký khóa học");
            System.out.println("4. Xem tất cả giảng viên");
            System.out.println("9. Quay lại");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");
            selectMenu(context, user);
        }
    }
    public void selectMenu(AppContext context, User user){
        Scanner scanner = context.getScanner();
        int choose = Integer.parseInt(scanner.nextLine());
        switch (choose){
            case 1:
                menu.loginMenu(context,user);
                break;
            case 2:
                printService.printAllCourse(context);
                break;
            case 3:
                enrollmentService.enrollAndAskForPayment(user.getId(), context);
                break;
            case 4:
                printService.printInfo(context, Role.TEACHER);
                break;
            case 5:

                break;
            case 9:
                return;
            case 0:
                System.exit(1);
            default:
                System.out.println("Lựa chọn không hợp lệ");
        }
    }
}
