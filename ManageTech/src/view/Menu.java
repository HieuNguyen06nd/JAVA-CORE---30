package view;

import entities.Enrollments;
import entities.User;
import enums.Role;
import exist.Utils;
import service.AppContext;
import service.EnrollmentService;
import service.PrintService;
import service.UserService;

import java.util.Scanner;

public class Menu {

    private final UserService userService;
    private final MenuAdmin menuAdmin;
    private final MenuStudent menuStudent;
    private final MenuTeacher menuTeacher;
    private final PrintService printService;
    private final EnrollmentService enrollmentService;
    Utils utils = new Utils();

    public Menu() {
        this.userService = new UserService();
        this.menuAdmin = new MenuAdmin();
        this.menuStudent = new MenuStudent();
        this.menuTeacher = new MenuTeacher();
        this.printService = new PrintService();
        this.enrollmentService = new EnrollmentService();
    }

    public void displayMenu() {
        AppContext appContext = AppContext.getInstance();
        System.out.println("1 - Đăng nhập\n" +
                "2 - Đăng ký\n" +
                "3 - Xem khóa hoc của trung tâm\n" +
                "4 - Xem blog\n" +
                "5 - Đăng ký khóa học\n" +
                "0 - Thoát chương trình");
        selectDisplayMenu(appContext);
    }

    public void selectDisplayMenu(AppContext appContext) {
        Scanner scanner = appContext.getScanner();
        try {
            int choose = utils.inputInt(scanner, " Mời lựa chọn: ");
            switch (choose) {
                case 1:
                    userService.signIn();
                    break;
                case 2:
                    userService.signUp();
                    break;
                case 3:
                    printService.printAllCourse(appContext);
                    break;
                case 4:
                    printService.printAllBlogs(appContext);
                    break;
                case 5:
                    printService.printAllCourse(appContext);
                    enrollmentService.enrollCourse(appContext);
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                    displayMenu(); // Hiển thị lại menu
            }
        } catch (NumberFormatException e) {
            System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập số.");
            displayMenu(); // Hiển thị lại menu
        }
    }

    public void loginMenu(AppContext appContext, User user) {
        if (user == null) {
            System.out.println("Lỗi: Người dùng không tồn tại.");
            return;
        }

        Role role = user.getRole();
        if (role == null) {
            System.out.println("Lỗi: Vai trò của người dùng không xác định.");
            return;
        }

        switch (role) {
            case ADMIN:
                menuAdmin.displayAdmin(appContext, user);
                break;
            case STUDENT:
                menuStudent.displayStudent(appContext, user);
                break;
            case TEACHER:
                menuTeacher.displayTeacher(appContext, user);
                break;
            default:
                System.out.println("Lỗi: Vai trò không hợp lệ.");
        }
    }
}