package view;

import entities.User;
import service.UserService;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    UserService userService= new UserService();
    public void displayMenu(Scanner scanner, ArrayList<User> users){
        System.out.println("1 - Đăng nhập\n" +
                "\n" +
                "2 - Đăng ký");
        System.out.println("Moi lua chon");
        selectDisplayMenu(scanner, users);
    }
    public void selectDisplayMenu(Scanner scanner, ArrayList<User> users){
        int choose = Integer.parseInt(scanner.nextLine());
        switch (choose){
            case 1:
                userService.signUp(scanner, users);
                break;
            case 2:
                System.out.println(users);
                break;
            default:
                System.out.println("lua chon khong hop le");
        }
    }
}
