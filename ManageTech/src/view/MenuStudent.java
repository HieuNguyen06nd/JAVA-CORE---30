package view;

import entities.User;
import exist.Utils;
import service.*;

import java.util.List;
import java.util.Scanner;

public class MenuStudent {
    AppContext appContext = AppContext.getInstance();
    UserService userService = new UserService();
    PrintService printService = new PrintService();
    EnrollmentService enrollmentService = new EnrollmentService();
    ATMCardService atmCardService= new ATMCardService();
    Utils utils = new Utils();


    public void displayStudent(AppContext context, User user) {
        Scanner scanner = context.getScanner();
        while (true) {
            System.out.println("\n====== MENU CHỨC NĂNG ======");
            System.out.println("1. Quản lý thông tin cá nhân");
            System.out.println("2. Xem Lớp học");
            System.out.println("3. Xem khóa học đã đăng ký");
            System.out.println("4. Xem điểm cá nhân theo lớp");
            System.out.println("5. Xem bài giảng");
            System.out.println("6. Đăng ký khóa học");
            System.out.println("7. Thanh toán khóa học");
            System.out.println("8. Thêm thẻ ATM");
            System.out.println("0. Thoát");
            int choose = utils.inputInt(scanner, " Mời lựa chọn: ");
            switch (choose) {
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
                    printService.printStudentScoresByUser(context, user);
                    break;
                case 5:
                    printService.printClassesAndLessonsForStudent(context, user);
                    break;
                case 6:
                    printService.printAllCourse(context);
                    enrollmentService.enrollCourse(context);
                    break;
                case 7:
                    printService.printCourseByUserId(user.getId(), context, 0);
                    enrollmentService.payForCourse(context);
                    break;
                case 8:
                    atmCardService.inputATMCardDetails();
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

        int choose = utils.inputInt(scanner, " Mời lựa chọn: ");
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
