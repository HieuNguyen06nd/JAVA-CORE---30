package service;

import entities.Blog;
import entities.ClassRoom;
import entities.Lesson;
import entities.User;
import enums.Role;

import java.util.ArrayList;
import java.util.Scanner;

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

    public void printInfoClass(ArrayList<ClassRoom> classRooms, Role role){
        boolean check = false;
        for (ClassRoom classRoom: classRooms){
            if (classRoom != null) {
                System.out.println("Thông tin lớp học:");
                System.out.println("ID Lớp học: " + classRoom.getId());
                System.out.println("Tên lớp học: " + classRoom.getName());
                System.out.println("Giảng viên: " + classRoom.getTeacherId());
                System.out.println("Danh sách học viên:");
                if (classRoom.getStudentId() != null ) {
                    for (String student : classRoom.getStudentId()) {
                        System.out.println("  - " + student);
                    }
                } else {
                    System.out.println("  Chưa có học viên nào.");
                }
                System.out.println("Danh sách môn học:");
                if (classRoom.getLessonId() != null) {
                    for (String course : classRoom.getLessonId()) {
                        System.out.println("  - " + course);
                    }
                } else {
                    System.out.println("  Chưa có môn học nào.");
                }
                check = true;
            }
        }
        if (!check){
            System.out.println("Danh sách rỗng! ");
        }
    }

    public void printBlogAll(ArrayList<Blog> blogs){
        boolean check = false;
        for (Blog blog: blogs){
            if (blog != null) {
                System.out.println("Blog ID: " + blog.getId());
                System.out.println("Title: " + blog.getTitle());
                System.out.println("Content: " + blog.getContent());
                System.out.println("User ID: " + blog.getUser_Id());
                System.out.println("Published At: " + blog.getPublished_at());
                System.out.println("Status: " + (blog.isStatus() ? "Đăng" : "Chưa đăng"));
            }
            check=true;
        }
        if (!check){
            System.out.println("Danh sách rỗng! ");
        }
    }

    public void printLessonByClass(Scanner scanner, ArrayList<Lesson> lessons) {
        System.out.print("Nhập ID lớp để hiển thị bài học: ");
        String classId = scanner.nextLine();
        System.out.println("Danh sách bài học cho lớp " + classId + ":");
        for (Lesson lesson : lessons) {
            if (lesson.getClass_id().equals(classId)) {
                System.out.println("ID: " + lesson.getId() + " | Tiêu đề: " + lesson.getTitle() + " | Thứ tự: " + lesson.getOrder());
            }
        }
    }


}
