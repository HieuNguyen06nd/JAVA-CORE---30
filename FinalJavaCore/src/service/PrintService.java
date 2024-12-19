package service;

import entities.Student;
import entities.User;
import enums.Role;

import java.util.ArrayList;

public class PrintService {
    public void printInfo(ArrayList<User> users, Role role){
        boolean check = false;
        for (User user: users){
            if (user != null && user.getRole().equals(role)) {
                System.out.println("Thông tin "+role+" :");
                System.out.println("ID hoc vien: "+ user.getId());
                System.out.println("Tên người dùng: " + user.getUsername());
                System.out.println("Email: " + user.getEmail());
                System.out.println("Số điện thoại: " + user.getPhone());
                System.out.println("Mật khẩu: " + user.getPassword());
                System.out.println("Role: " + user.getRole());
                check = true;
            }
        }
        if (!check){
            System.out.println("Danh sách rỗng! ");
        }
    }


}
