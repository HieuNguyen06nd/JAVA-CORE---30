package service;

import entities.Course;
import entities.Enrollments;
import entities.User;
import enums.Role;
import exist.Exist;

import java.util.ArrayList;
import java.util.Scanner;

public class EnrollmentService {
    Exist exist = new Exist();
    UserService userService = new UserService();
    CourseService courseService = new CourseService();
    PrintService printService = new PrintService();
    public void enrollCourse(String userId, AppContext context) {
        ArrayList<User> users = context.getUsers();
        Scanner scanner = context.getScanner();
        ArrayList<Course> courses = context.getCourses();
        ArrayList<Enrollments> enrollments = context.getEnrollments();

        User user = userService.findById(userId, users);
        if (user == null) {
            System.out.println("Người dùng không tồn tại.");
            return;
        }

        System.out.println("Nhập ID khoá học:");
        String courseId = scanner.nextLine();

        Course course = courseService.findById(courseId, courses);
        if (course == null) {
            System.out.println("Khóa học không tồn tại.");
            return;
        }

        for (Enrollments enrollment : enrollments) {
            if (enrollment.getUser_id().equals(userId) && enrollment.getCourse_id().equals(courseId)) {
                System.out.println("Bạn đã đăng ký khóa học này rồi.");
                return;
            }
        }

        Enrollments newEnrollment = new Enrollments(userId, courseId, 0);
        enrollments.add(newEnrollment);
        System.out.println("Bạn đã đăng ký khóa học thành công, nhưng chưa thanh toán.");
        System.out.print("Bạn có muốn thanh toán ngay bây giờ không? (yes/no): ");
        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("yes")) {
            makePayment(userId, context);
        } else {
            System.out.println("Bạn có thể thanh toán sau.");
        }
    }

    public void makePayment(String userId, AppContext context) {
        ArrayList<User> users = context.getUsers();
        Scanner scanner = context.getScanner();
        ArrayList<Course> courses = context.getCourses();
        ArrayList<Enrollments> enrollments = context.getEnrollments();

        User user = userService.findById(userId, users);
        if (user == null) {
            System.out.println("Người dùng không tồn tại.");
            return;
        }

        System.out.println("Nhập ID khoá học bạn đã đăng ký để thanh toán:");
        String courseId = scanner.nextLine();


        boolean isEnrolled = false;
        for (Enrollments enrollment : enrollments) {
            if (enrollment.getUser_id().equals(userId) && enrollment.getCourse_id().equals(courseId)) {
                isEnrolled = true;
                break;
            }
        }

        if (!isEnrolled) {
            System.out.println("Bạn chưa đăng ký khóa học này.");
            return;
        }

        Course course = courseService.findById(courseId, courses);
        if (course == null) {
            System.out.println("Khóa học không tồn tại.");
            return;
        }

        if (user.getBudget() < course.getPrice()) {
            System.out.println("Ngân sách không đủ để thanh toán khóa học.");
            return;
        }

        user.setBudget(user.getBudget() - course.getPrice());
        System.out.println("Thanh toán thành công! Bạn đã thanh toán cho khóa học: " + course.getName());
        System.out.println("Ngân sách còn lại: " + user.getBudget() + " VNĐ");


        for (Enrollments enrollment : enrollments) {
            if (enrollment.getUser_id().equals(userId) && enrollment.getCourse_id().equals(courseId)) {
                enrollment.setStatus(1);
                break;
            }
        }

        System.out.println("Đăng ký và thanh toán thành công.");
        user.setRole(Role.STUDENT);
    }

    public void rechargeBudget(String userId, AppContext context) {
        ArrayList<User> users = context.getUsers();
        Scanner scanner = context.getScanner();

        User user = userService.findById(userId, users);
        if (user == null) {
            System.out.println("Người dùng không tồn tại.");
            return;
        }

        System.out.println("Nhập số tiền bạn muốn nạp vào ngân sách:");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        if (amount <= 0) {
            System.out.println("Số tiền nạp vào phải lớn hơn 0.");
            return;
        }

        user.setBudget(user.getBudget() + amount);
        System.out.println("Nạp tiền thành công! Số tiền trong ngân sách của bạn hiện tại: " + user.getBudget() + " VNĐ.");
    }


}
