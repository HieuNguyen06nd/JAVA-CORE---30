package service;

import entities.*;
import enums.Role;
import exist.Exist;

import java.util.ArrayList;
import java.util.Scanner;

public class PrintService {
    Exist exist = new Exist();
    UserService userService = new UserService();
    CourseService courseService = new CourseService();
//   user
    public void printInfo(AppContext appContext, Role role) {
        ArrayList<User> users = appContext.getUsers();
        System.out.println("=== Thông tin người dùng có vai trò " + role + " ===");
        boolean found = false;

        for (User user : users) {
            if (user != null && user.getRole().equals(role)) {
                System.out.println("--------------------------------");
                System.out.println("ID: " + user.getId());
                System.out.println("Tên người dùng: " + user.getUsername());
                System.out.println("Email: " + user.getEmail());
                System.out.println("Budget: " + user.getBudget());
                System.out.println("created_at: " + user.getCreated_at());
                System.out.println("Role: " + user.getRole());
                found = true;
            }
        }

        if (!found) {
            System.out.println("Không có người dùng nào có vai trò " + role);
        }
    }

    public void printUserDetails(String userId, AppContext  context) {
        ArrayList<User>users = context.getUsers();

        User user = userService.findById(userId,users);

        if (user == null) {
            System.out.println("Không tìm thấy người dùng với ID: " + userId);
            return;
        }

        System.out.println("=== Chi tiết Người dùng ===");
        System.out.println("ID: " + user.getId());
        System.out.println("Tên người dùng: " + user.getUsername());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Budget: " + user.getBudget());
        System.out.println("Vai trò: " + user.getRole());
    }

//   Lesson
    public void printLessonByClass(AppContext appContext) {
        Scanner scanner = appContext.getScanner();
        ArrayList<Lesson> lessons = appContext.getLessons();


        System.out.print("Nhập ID lớp học để hiển thị bài học: ");
        String classId = scanner.nextLine();

        System.out.println("=== Danh sách bài học của lớp " + classId + " ===");
        boolean found = false;
        for (Lesson lesson : lessons) {
            if (lesson.getClass_id().equals(classId)) {
                System.out.println("ID: " + lesson.getId());
                System.out.println("Tiêu đề: " + lesson.getTitle());
                System.out.println("Nooij dung: " + lesson.getContent());
                System.out.println("Thứ tự: " + lesson.getOrder());
                found = true;
            }
        }
        if (!found) {
            System.out.println("Không có bài học nào thuộc lớp " + classId + ".");
        }
    }

//    class
    public void printInfoClass(AppContext context) {
        ArrayList<Classes> classes = context.getClasses();
        Scanner scanner =context.getScanner();

        System.out.println("Nhập khóa học ID:");
        String courseId = scanner.nextLine();

        System.out.println("=== Danh sách lớp học theo khóa học ID: " + courseId + " ===");
        boolean found = false;

        for (Classes classRoom : classes) {

            if (classRoom != null && classRoom.getCourse_id().equals(courseId)) {
                System.out.println("--------------------------------");
                System.out.println("ID Lớp học: " + classRoom.getId());
                System.out.println("ID KHÓA học: " + classRoom.getCourse_id());
                System.out.println("Tên lớp học: " + classRoom.getName());
                System.out.println("Giảng viên: " + classRoom.getTeacher_id());

                System.out.println("Danh sách học viên:");
                if (classRoom.getStudent_id() != null && !classRoom.getStudent_id().isEmpty()) {
                    classRoom.getStudent_id().forEach(student -> System.out.println("  - " + student));
                } else {
                    System.out.println("  Chưa có học viên nào.");
                }

                found = true;
            }
        }

        if (!found) {
            System.out.println("Không có lớp học nào thuộc khóa học ID: " + courseId);
        }
    }

    public void printAllClassRoomsByUser(String userId, AppContext context) {
        ArrayList<User> users = context.getUsers();
        ArrayList<Classes> classes = context.getClasses();

        User user = userService.findById(userId,users);

        System.out.println("=== Danh sách Lớp học của người dùng ===");
        System.out.println("Thông tin người dùng:");
        System.out.println("ID: " + user.getId());
        System.out.println("Tên: " + user.getUsername());
        System.out.println("Vai trò: " + user.getRole());

        boolean hasClass = false;

        // Nếu người dùng là giáo viên, tìm các lớp mà họ là giảng viên
        if (user.getRole() == Role.TEACHER) {
            System.out.println("\nLớp học giảng dạy:");
            for (Classes classRoom : classes) {
                if (classRoom.getTeacher_id().equals(userId)) {
                    printClassRoomSummary(classRoom);
                    hasClass = true;
                }
            }
        }

        // Nếu người dùng là học viên, tìm các lớp mà họ là học viên
        if (user.getRole() == Role.STUDENT) {
            System.out.println("\nLớp học tham gia:");
            for (Classes classRoom : classes) {
                if (classRoom.getStudent_id() != null && classRoom.getStudent_id().contains(userId)) {
                    printClassRoomSummary(classRoom);
                    hasClass = true;
                }
            }
        }

        if (!hasClass) {
            System.out.println("Ban không tham gia hoặc giảng dạy bất kỳ lớp học nào.");
        }
    }
    private void printClassRoomSummary(Classes classRoom) {
        System.out.println("--------------------------------");
        System.out.println("ID Lớp học: " + classRoom.getId());
        System.out.println("Tên lớp học: " + classRoom.getName());
        System.out.println("Giảng viên: " + classRoom.getTeacher_id());
        System.out.println("Số học viên: " + (classRoom.getStudent_id() != null ? classRoom.getStudent_id().size() : 0));
        System.out.println("--------------------------------");
    }


//    Course

    public void printAllCourse(AppContext context){
        ArrayList<Course> courses = context.getCourses();
        for (Course course: courses){
            System.out.println("=== Chi tiết khóa học ===");
            System.out.println("ID: " + course.getId());
            System.out.println("Tên khóa học: " + course.getName());
            System.out.println("Mô tả: " + course.getDescription());
            System.out.println("Giá : " + course.getPrice());
        }
    }

    public void printCourseById(String courseId, AppContext context) {
        ArrayList<Course> courses = context.getCourses();

        Course course = courseService.findById(courseId, courses);
        if (course == null) {
            System.out.println("Không tìm thấy khóa học với ID: " + courseId);
        } else {
            System.out.println("=== Chi tiết khóa học ===");
            System.out.println("ID: " + course.getId());
            System.out.println("Tên khóa học: " + course.getName());
            System.out.println("Mô tả: " + course.getDescription());
            System.out.println("Giá : " + course.getPrice());
        }
    }

    public void printCourseByUserId(String user_id, AppContext context){
        ArrayList<Enrollments>enrollments= context.getEnrollments();

        for (Enrollments enrollment: enrollments){
            if (enrollment.getUser_id().equals(user_id)){
                printCourseById(enrollment.getCourse_id(),context);
            }
        }
    }

}
