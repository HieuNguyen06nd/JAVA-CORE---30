package service;

import entities.Student;
import enums.Role;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentService {
    public Student inputStudent() {
        AppContext appContext = AppContext.getInstance();
        Scanner scanner = appContext.getScanner();
        ArrayList<Student> students = appContext.getStudents();

        String username;
        while (true) {
            System.out.print("Nhập Username cho sinh viên: ");
            username = scanner.nextLine();
            boolean exists = false;
            for (Student student : students) {
                if (student.getUsername().equals(username)) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                break; // Nếu không trùng, thoát khỏi vòng lặp
            } else {
                System.out.println("Username đã tồn tại, vui lòng nhập lại.");
            }
        }

        String password;
        do {
            System.out.print("Nhập mật khẩu: ");
            password = scanner.nextLine();
            if (password.length() < 6) {
                System.out.println("Mật khẩu phải có ít nhất 6 ký tự.");
            }
        } while (password.length() < 6);

        String email;
        while (true) {
            System.out.print("Nhập email cho sinh viên: ");
            email = scanner.nextLine();
            if (email.contains("@") && email.contains(".")) {
                break; // Nếu email hợp lệ, thoát khỏi vòng lặp
            } else {
                System.out.println("Email không hợp lệ. Vui lòng thử lại.");
            }
        }

        String educationLevel;
        System.out.print("Nhập trình độ học vấn của sinh viên: ");
        educationLevel = scanner.nextLine();

        Student student = new Student(username, password, email, Role.STUDENT, educationLevel);
        return student;
    }
}
