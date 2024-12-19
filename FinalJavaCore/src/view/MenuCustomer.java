package view;

import entities.User;
import service.UserService;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuCustomer {

//    tesst
    MenuAdmin menuAdmin = new MenuAdmin();



    UserService userService= new UserService();
    public void displayMenu(Scanner scanner, ArrayList<User> users){
        System.out.println("1 - Đăng nhập\n" +
                "2 - Đăng ký");
        System.out.println("Mời lụa chọn");
        selectDisplayMenu(scanner, users);
    }
    public void selectDisplayMenu(Scanner scanner, ArrayList<User> users){
        int choose = Integer.parseInt(scanner.nextLine());
        switch (choose){
            case 1:
                userService.singIn(scanner, users);
                break;
            case 2:
                userService.signUp(scanner, users);
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ");
        }
    }

    public void loginMenu(Scanner scanner, ArrayList<User> users, User user){
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
                userService.changePassword(scanner, users, user);
                break;
            case 4:
                displayMenu(scanner, users);
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