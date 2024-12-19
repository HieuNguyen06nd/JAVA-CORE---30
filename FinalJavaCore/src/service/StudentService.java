package service;

import entities.Student;
import validate.Validate;

import java.util.Scanner;

public class StudentService {
    Validate validate = new Validate();
    public Student inputStudent(Scanner scanner) {
        System.out.print("Nhập tên người dùng: ");
        String username = scanner.nextLine();

        String email;
        do {
            System.out.print("Nhập email: ");
            email = scanner.nextLine();
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

        System.out.print("Nhập trinh do hoc van: ");
        String education_level = scanner.nextLine();

        return new Student(email, username, phone, password, education_level);
    }
}
