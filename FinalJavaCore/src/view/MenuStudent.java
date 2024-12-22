package view;

import entities.ClassRoom;
import entities.User;
import service.PrintService;
import service.UserService;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuStudent {
    UserService userService = new UserService();
    PrintService printService = new PrintService();
    public void  displayStudent(Scanner scanner, ArrayList<User>users, User user, ArrayList<ClassRoom>classRooms){
        while (true){
            System.out.println("\n====== MENU CHỨC NĂNG ======");
            System.out.println("1. Quản lý thông tin cá nhân");
            System.out.println("2. Xem Lớp học");
            System.out.println("3. Xem khóa học cá nhân");
            System.out.println("4. Xem điểm cá nhân theo lớp");
            System.out.println("7. Xem thông tin cá nhân");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    loginMenu(scanner,users, user);
                    break;
                case 2:
                    String id = user.getId();
                    printService.printAllClassRoomsByUser(id,classRooms,users);
                    break;
                case 3:

                    break;
                case 0:
                    System.exit(1);
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng chọn lại!");
            }
        }
    }
    public void loginMenu(Scanner scanner, ArrayList<User> users, User user){
        System.out.println("1 - Thay đổi username\n" +
                "2 - Thay đổi email\n" +
                "3 - Thay đổi mật khẩu\n" +
                "4 - Hiển thị thông tin cá nhân\n" +
                "9 - Quay Lại\n" +
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
