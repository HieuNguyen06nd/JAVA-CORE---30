package service;

import entities.Student;
import entities.User;
import enums.Role;
import validate.Validate;
import view.MenuCustomer;

import java.util.ArrayList;
import java.util.Scanner;

public class UserService {
    Validate validate = new Validate();

    public void signUp(Scanner scanner, ArrayList<User> users){
        String username;
        do {
            System.out.print("Nhập tên người dùng: ");
            username = scanner.nextLine();
            validate.existUsername(username,users);
        } while (! validate.existUsername(username,users));

        String email;
        do {
            System.out.print("Nhập email: ");
            email = scanner.nextLine();
            validate.existEmail(email,users);
            if (!validate.isValidEmail(email)) {
                System.out.println("Email không hợp lệ, vui lòng nhập lại.");
            }
        } while (!validate.isValidEmail(email));

        String phone;
        do {
            System.out.print("Nhập số điện thoại: ");
            phone = scanner.nextLine();
            if (!validate.isValidPhone(phone)) {
                System.out.println("Số điện thoại không hợp lệ, vui lòng nhập lại.");
            }
        } while (!validate.isValidPhone(phone));

        String password;
        do {
            System.out.print("Nhập mật khẩu (ít nhất 8 ký tự, gồm chữ cái và số): ");
            password = scanner.nextLine();
            if (!validate.isValidPassword(password)) {
                System.out.println("Mật khẩu không hợp lệ, vui lòng nhập lại.");
            }
        } while (!validate.isValidPassword(password));

        Role role =Role.CUSTOMER;

        users.add(new User(username,email, phone,password, role));

        System.out.println("Đăng ký thành công!");
    }

    public void singIn(Scanner scanner,ArrayList<User> users){
        System.out.print("Nhập username: ");
        String username = scanner.nextLine();

        User user  = findUserByUsername(username, users);
        if (user == null) {
            System.out.println("Kiểm tra lại username.");
            return;
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
            MenuCustomer menu = new MenuCustomer();
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

            if (!validate.isValidPassword(newPassword)) {
                System.out.println("Mật khẩu không hợp lệ.");
            } else {
                user.setPassword(newPassword);
                System.out.println("Đổi mật khẩu thành công.");
            }
        }

    }

    public void changeUsername(Scanner scanner, ArrayList<User> users, User user){
        System.out.print("Nhập username mới: ");
        String newUsername = scanner.nextLine();

        if (findUserByUsername(newUsername, users) != null) {
            System.out.println("Username đã tồn tại. Vui lòng thử lại.");
        } else {
            user.setUsername(newUsername);
            System.out.println("Thay đổi username thành công.");

            System.out.println(users);
        }
    }

    public void changeEmail(Scanner scanner, ArrayList<User> users, User user){
        System.out.print("Nhập email mới: ");
        String newEmail = scanner.nextLine();

        if (!validate.isValidEmail(newEmail)) {
            System.out.println("Email không hợp lệ. Vui lòng thử lại.");
        } else if (validate.existEmail(newEmail, users)) {
            System.out.println("Email đã tồn tại. Vui lòng thử lại.");
        } else {
            user.setEmail(newEmail);
            System.out.println("Thay đổi email thành công.");
            MenuCustomer menu = new MenuCustomer();
            menu.loginMenu(scanner, users, user);
        }
    }
    public void changePassword(Scanner scanner, ArrayList<User> users, User user){
        System.out.print("Nhập mật khẩu mới: ");
        String newPassword = scanner.nextLine();

        if (!validate.isValidPassword(newPassword)) {
            System.out.println("Mật khẩu cần dài từ 7 đến 15 ký tự, bao gồm ít nhất 1 ký tự in hoa và 1 ký tự đặc biệt (. , - _ ;).\n");
        } else {
            user.setPassword(newPassword);
            System.out.println("Thay đổi mật khẩu thành công.");
        }
    }
    public void changePhone(Scanner scanner, ArrayList<User> users, User user){
        System.out.print("Nhập số điện thoại mới: ");
        String phone = scanner.nextLine();
        if (!validate.isValidPhone(phone)) {
            System.out.println("Số điện thoại không hợp lệ, vui lòng nhập lại.");
        }else {
            user.setPhone(phone);
            System.out.println("Thay đổi số điện thoại thành công.");
        }
    }
    public void changeRole(Scanner scanner, ArrayList<User> users, User user){
        System.out.print("Nhập Role mới: doi update ");
        String newRole = scanner.nextLine();

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
    public User findById(String id, ArrayList<User> users){
        for (User user : users){
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    public void deleteUser(Scanner scanner,ArrayList<User> users){
        System.out.print("Nhập id cần xóa: ");
        String id= scanner.nextLine();
        User user = findById(id,users);
        if (user ==null){
            System.out.println("Không tồn tại user id: "+id);
        }else {
            users.remove(user);
            System.out.println("Đã xóa user id: " + id);
        }

    }

    public  User findUserByRole(Role role,ArrayList<User> users){
        for (User user: users){
            if (user.getRole().equals(role)){
                return user;
            }
        }
        return null;
    }
}
