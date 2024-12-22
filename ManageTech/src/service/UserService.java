package service;

import entities.User;
import enums.Role;
import view.Menu;

import java.util.ArrayList;
import java.util.Scanner;

public class UserService {
    ValidateUser validateUser = new ValidateUser();

    public void signUp(Scanner scanner, ArrayList<User> users){
        String username;
        while (true) {
            System.out.print("Nhập Username: ");
            username = scanner.nextLine();
            if (!validateUser.existUsername(username, users)) {
                break;
            } else {
                System.out.println("Username đã tồn tại, vui lòng nhập lại.");
            }
        }

        String password;
        do {
            System.out.print("Nhập mật khẩu : ");
            password = scanner.nextLine();
            if (!validateUser.checkPassword(password)) {
                System.out.println("Mật khẩu không hợp lệ, vui lòng nhập lại.");
            }
        } while (!validateUser.checkPassword(password));

        String email;
        while (true) {
            System.out.print("Nhập email: ");
            email = scanner.nextLine();
            if (validateUser.existEmail(email, users)) {
                System.out.println("Email đã tồn tại, vui lòng nhập lại.");
            } else if (!validateUser.checkEmail(email)) {
                System.out.println("Email không hợp lệ, vui lòng nhập lại.");
            } else {
                break;
            }
        }

        users.add(new User(username,password,email, Role.ADMIN));

        System.out.println("Đăng ký thành công!");
    }

    public void singIn(Scanner scanner,ArrayList<User> users){
        User user = null;

        while (user == null) {
            System.out.print("Nhập username (hoặc nhập 'exit' để thoát): ");
            String username = scanner.nextLine();
            if (exitProgramme(username)) {
                return;
            }
            user = findUserByUsername(username, users);
            if (user == null) {
                System.out.println("Kiểm tra lại username.");
            }
        }
        System.out.print("Nhập password: ");
        String password = scanner.nextLine();
        if (!user.getPassword().equals(password)){
            System.out.println(" Sai password");
            System.out.println("1 - Đăng nhập lại\n" +
                    "2 - Quên mật khẩu");
            System.out.println("Mời lụa chọn");
            int choose = Integer.parseInt(scanner.nextLine());
            switch (choose){
                case 1:
                    singIn(scanner , users);
                    break;
                case 2:
                    forgetPassword(scanner,users);
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ");
            }
        }else {
            System.out.println("“Chào mừng "+user.getUsername()+", bạn có thể thực hiện các công việc sau:”");
            Menu menu = new Menu();
            menu.loginMenu(scanner,users, user);
        }
    }

    public void forgetPassword(Scanner scanner, ArrayList<User> users){
        System.out.print("Nhập email: ");
        String email = scanner.nextLine();

        User user = findUserByEmail(email, users);
        if (user == null) {
            System.out.println("Tài khoản không tồn tại.");
        } else {
            System.out.print("Nhập mật khẩu mới: ");
            String newPassword = scanner.nextLine();

            if (!validateUser.checkPassword(newPassword)) {
                System.out.println("Mật khẩu không hợp lệ.");
            } else {
                user.setPassword(newPassword);
                System.out.println("Đổi mật khẩu thành công.");
            }
        }

    }

    public void changeUsername(Scanner scanner, ArrayList<User> users, User user){
        while (true) {
            System.out.print("Nhập username mới: ");
            String newUsername = scanner.nextLine();

            if (findUserByUsername(newUsername, users) != null) {
                System.out.println("Username đã tồn tại. Vui lòng nhập lại.");
            }else if (newUsername.equals(user.getUsername())) {
                System.out.println("Username mới không được trùng với username hiện tại. Vui lòng nhập lại.");
            }else {
                user.setUsername(newUsername);
                System.out.println("Thay đổi username thành công.");
                break;
            }
        }
    }

    public void changeEmail(Scanner scanner, ArrayList<User> users, User user){
        while (true) {
            System.out.print("Nhập email mới: ");
            String newEmail = scanner.nextLine();

            if (!validateUser.checkEmail(newEmail)) {
                System.out.println("Email không hợp lệ. Vui lòng thử lại.");
            }
            else if (validateUser.existEmail(newEmail, users)) {
                System.out.println("Email đã tồn tại. Vui lòng thử lại.");
            }
            else {
                user.setEmail(newEmail);
                System.out.println("Thay đổi email thành công.");
                break;
            }
        }
        Menu menu = new Menu();
        menu.loginMenu(scanner, users, user);
    }
    public void changePassword(Scanner scanner, ArrayList<User> users, User user){
        while (true) {
            System.out.print("Nhập mật khẩu mới: ");
            String newPassword = scanner.nextLine();

            if (!validateUser.checkPassword(newPassword)) {
                System.out.println("Mật khẩu cần dài từ 7 đến 15 ký tự, bao gồm ít nhất 1 ký tự in hoa và 1 ký tự đặc biệt (. , - _ ;).\n");
            }
            else if (newPassword.equals(user.getPassword())) {
                System.out.println("Mật khẩu mới không được trùng với mật khẩu hiện tại. Vui lòng nhập lại.\n");
            }
            else {
                user.setPassword(newPassword);
                System.out.println("Thay đổi mật khẩu thành công.");
                break;
            }
        }
    }

    public User findUserByUsername(String username, ArrayList<User> users) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public User findUserByEmail(String email, ArrayList<User> users) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }
    public boolean exitProgramme(String key) {
        if (key.equalsIgnoreCase("exit")) {
            System.out.println("Đã thoát khỏi chương trình.");
            return true;
        }
        return false;
    }
}
