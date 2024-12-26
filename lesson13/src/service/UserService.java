package service;

import entities.User;

import java.util.Scanner;

public class UserService {
    public User inputUser(Scanner scanner){
        System.out.println("nhap ten");
        String name = scanner.nextLine();

        System.out.println("password");
        String password = scanner.nextLine();

        System.out.println("nhap dia chi");
        String address = scanner.nextLine();

        return new User(name, password, address);

    }
}
