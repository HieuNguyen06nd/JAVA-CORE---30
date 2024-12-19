package service;

import entities.User;

import java.util.ArrayList;
import java.util.Scanner;

public class UserService {
    ValidateUser validateUser = new ValidateUser();

    public void signUp(Scanner scanner, ArrayList<User> users){
        System.out.println("Moi nhap userName");
        String userName = scanner.nextLine();

        System.out.println("Moi nhap password");
        String password = scanner.nextLine();

        System.out.println("Moi nhap email");
        String email = scanner.nextLine();

        validateUser.existEmail(email,users);
        validateUser.existUsername(userName,users);

        if (!validateUser.checkEmail(email)){
            System.out.println("Email không hợp lệ. Vui lòng nhập lại.");
        }
        if (!validateUser.checkPassword(password)) {
            System.out.println("Mật khẩu không hợp lệ. Mật khẩu cần dài từ 7 đến 15 ký tự, bao gồm ít nhất 1 ký tự in hoa và 1 ký tự đặc biệt (. , - _ ;).");

        }

        users.add(new User(userName,password,email));

        System.out.println("Đăng ký thành công!");
    }
}
