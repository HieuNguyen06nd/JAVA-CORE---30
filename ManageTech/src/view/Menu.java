package view;

import entities.User;
import enums.Role;
import service.AppContext;
import service.UserService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    private final UserService userService;
    private final MenuAdmin menuAdmin;
    private final MenuStudent menuStudent;
    private final MenuCustomer menuCustomer;
    private final MenuTeacher menuTeacher;

    public Menu() {
        this.userService = new UserService();
        this.menuAdmin = new MenuAdmin();
        this.menuStudent = new MenuStudent();
        this.menuCustomer = new MenuCustomer();
        this.menuTeacher = new MenuTeacher();
    }

    public void displayMenu() {
        AppContext appContext = AppContext.getInstance();
        System.out.println("1 - Đăng nhập\n" +
                "2 - Đăng ký\n" +
                "0 - Thoát chương trình");
        System.out.println("Mời lựa chọn");
        selectDisplayMenu(appContext);
    }

    public void selectDisplayMenu(AppContext appContext) {
        Scanner scanner = appContext.getScanner();
        try {
            int choose = Integer.parseInt(scanner.nextLine());
            switch (choose) {
                case 1:
                    userService.signIn();
                    break;
                case 2:
                    userService.signUp();
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
            case CUSTOMER:
                menuCustomer.displayCustomer(appContext, user);
                break;
            default:
                System.out.println("Lỗi: Vai trò không hợp lệ.");
        }
    }
}