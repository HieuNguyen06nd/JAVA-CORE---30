package view;

import entities.User;
import service.AppContext;
import service.EnrollmentService;
import service.PrintService;
import service.UserService;

import java.util.List;
import java.util.Scanner;

public class MenuStudent {
    AppContext appContext = AppContext.getInstance();
    UserService userService = new UserService();
    PrintService printService = new PrintService();
    EnrollmentService enrollmentService = new EnrollmentService();

    public void displayStudent(AppContext context, User user) {
        Scanner scanner = context.getScanner();
        while (true) {
            System.out.println("\n====== MENU CHỨC NĂNG ======");
            System.out.println("1. Quản lý thông tin cá nhân");
            System.out.println("2. Xem Lớp học");
            System.out.println("3. Xem khóa học đã đăng ký");
            System.out.println("4. Xem điểm cá nhân theo lớp");
            System.out.println("6. Đăng ký khóa học");
            System.out.println("7. Thanh toán khóa học");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    loginMenu(context, user);
                    break;
                case 2:
                    String id = user.getId();
                    printService.printAllClassRoomsByUser(id, context);
                    break;
                case 3:
                    printService.printCourseByUserId(user.getId(), context, 1);
                    break;
                case 4:
//                    printService.viewScoreByClass(context);
                    break;
                case 6:
                    printService.printAllCourse(context);
                    enrollmentService.enrollCourse(user.getId(), context);
                    break;
                case 7:
                    printService.printCourseByUserId(user.getId(), context, 0);
                    enrollmentService.makePayment(user.getId(), context);
                    break;
                case 0:
                    System.exit(1);
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng chọn lại!");
            }
        }
    }

    public void loginMenu(AppContext context, User user) {
        Scanner scanner = context.getScanner();
        System.out.println("1 - Thay đổi username\n" +
                "2 - Thay đổi email\n" +
                "3 - Thay đổi mật khẩu\n" +
                "4 - Hiển thị thông tin cá nhân\n" +
                "9 - Quay Lại\n" +
                "0 - Thoát chương trình");
        selectLoginMenu( context, user);
    }
    public void selectLoginMenu(AppContext context, User user) {
        Scanner scanner = context.getScanner();

        int choose = Integer.parseInt(scanner.nextLine());
        switch (choose) {
            case 1:
                userService.changeUsername(user);
                break;
            case 2:
                userService.changeEmail(user);
                break;
            case 3:
                userService.changePassword(user);
                break;
            case 4:
                printService.printUserDetails(user.getId(), context);
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
