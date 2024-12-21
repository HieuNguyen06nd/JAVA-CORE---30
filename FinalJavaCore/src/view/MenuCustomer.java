package view;

import entities.Courses;
import entities.Teacher;
import entities.User;
import enums.Role;
import service.UserService;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuCustomer {
    UserService userService= new UserService();
    public void displayCustomer(Scanner scanner, ArrayList<User> users, User user){
        System.out.println("1 - Thay đổi username\n" +
                "2 - Thay đổi email\n" +
                "3 - Thay đổi mật khẩu\n" +
                "4 - Đăng xuất (Sau khi đăng xuất quay về mục yêu cầu đăng nhập hoặc đăng ký)\n" +
                "0 - Thoát chương trình");
        selectLoginMenu(scanner, users, user);
    }
    public void selectLoginMenu(Scanner scanner,ArrayList<User> users, User user){
        int choose = Integer.parseInt(scanner.nextLine());
        switch (choose){
            case 1:
                userService.changeUsername(scanner,users, user);
                break;
            case 2:
                userService.changeEmail(scanner, users,user);
                break;
            case 3:
                userService.changePassword(scanner, user);
                break;
            case 4:
//                displayMenu(scanner, users);
                break;
            case 5:
                System.out.println(user);
                break;
            case 6:
                System.out.println(users);
                break;
            case 0:
                System.exit(1);
            default:
                System.out.println("Lựa chọn không hợp lệ");
        }
    }
}
