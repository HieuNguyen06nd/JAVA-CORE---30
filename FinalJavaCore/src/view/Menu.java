package view;

import entities.*;
import enums.Role;
import service.PrintService;
import service.UserService;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    MenuAdmin menuAdmin = new MenuAdmin();
    MenuCustomer menuCustomer = new MenuCustomer();
    MenuStudent menuStudent = new MenuStudent();
    MenuTeacher menuTeacher   = new MenuTeacher();

    UserService userService= new UserService();
    public void displayMenu(Scanner scanner, ArrayList<User> users, ArrayList<Lesson>lessons, User user,
                            ArrayList<ClassRoom>classRooms, ArrayList<Blog>blogs, ArrayList<Courses>courses){
        System.out.println("1 - Đăng nhập\n" +
                "2 - Đăng ký");
        System.out.println("Mời lụa chọn");
        selectDisplayMenu(scanner, users, lessons, user,classRooms,blogs,courses );
    }
    public void selectDisplayMenu(Scanner scanner, ArrayList<User> users, ArrayList<Lesson>lessons, User user,
                                  ArrayList<ClassRoom>classRooms, ArrayList<Blog>blogs, ArrayList<Courses>courses){
        int choose = Integer.parseInt(scanner.nextLine());
        switch (choose){
            case 1:
                userService.singIn(scanner, users, lessons,classRooms,blogs,courses);
                break;
            case 2:
                userService.signUp(scanner, users);
                break;
            case 3:
                System.out.println(users);
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ");
        }
    }

    public void loginMenu(Scanner scanner, ArrayList<User> users, ArrayList<Lesson>lessons, User user,
                          ArrayList<ClassRoom>classRooms, ArrayList<Blog>blogs, ArrayList<Courses>courses){
        Role role = userService.findUserByRole(user.getRole(),users).getRole();
        if (role.equals(Role.ADMIN)){
            menuAdmin.displayAdmin(scanner,users,classRooms, lessons, blogs,courses);
        }else if (role.equals(Role.TEACHER)){
            menuTeacher.displayTeacher(scanner);
        }else if (role.equals(Role.STUDENT)){
            menuStudent.displayStudent(scanner, users, user);
        }else if (role.equals(Role.CUSTOMER)){
            menuCustomer.displayCustomer(scanner,users, user);
        }
    }

}