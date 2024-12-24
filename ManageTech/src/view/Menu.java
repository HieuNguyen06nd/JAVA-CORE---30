package view;

import entities.*;
import enums.Role;
import service.AppContext;
import service.StudentService;
import service.UserService;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    UserService userService = new UserService();
    MenuAdmin menuAdmin = new MenuAdmin();
    MenuStudent menuStudent = new MenuStudent();
    MenuCustomer menuCustomer = new MenuCustomer();

    public void displayMenu() {
        AppContext appContext = AppContext.getInstance();
        System.out.println("1 - Đăng nhập\n" +
                "2 - Đăng ký\n" +
                "0 - Thoát chương trình");
        System.out.println("Mời lựa chọn");
        selectDisplayMenu(appContext);
    }

    public void selectDisplayMenu(AppContext appContext) {
        int choose = Integer.parseInt(appContext.getScanner().nextLine());
        switch (choose) {
            case 1:
                userService.singIn();
                break;
            case 2:
                userService.signUp();
                break;
            case 0:
                System.exit(1);
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ");
        }
    }

    public void loginMenu(AppContext appContext, User user) {
        Role role = user.getRole();

        if (role.equals(Role.ADMIN)) {
            menuAdmin.displayAdmin(appContext,user);
        } else if (role.equals(Role.STUDENT)) {
            menuStudent.displayStudent(appContext, user);
        } else if (role.equals(Role.TEACHER)) {

        } else if (role.equals(Role.CUSTOMER)) {
            menuCustomer.displayCustomer(appContext,user);
        }
    }
}
