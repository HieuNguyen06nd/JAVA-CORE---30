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

    public void enrollAndAskForPayment(String userId, String courseId, AppContext context) {
        ArrayList<User> users = context.getUsers();
        Scanner scanner = context.getScanner();
        ArrayList<Course> courses = context.getCourses();
        ArrayList<Enrollments> enrollments = context.getEnrollments();

        User user = userService.findById(userId, users);
        if (user == null) {
            System.out.println("Người dùng không tồn tại.");
            return;
        }

        Course course = courseService.findById(courseId, courses);
        if (course == null) {
            System.out.println("Khóa học không tồn tại.");
            return;
        }
        printService.printCourseById(courseId, context);

        System.out.print("Bạn có muốn thanh toán và đăng ký khóa học này không? (yes/no): ");
        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("yes")) {
            if (user.getBudget() < course.getPrice()) {
                System.out.println("Ngân sách không đủ để thanh toán khóa học.");
                return;
            }

            user.setBudget(user.getBudget() - course.getPrice());
            System.out.println("Thanh toán thành công! Bạn đã đăng ký khóa học: " + course.getName());
            System.out.println("Ngân sách còn lại: " + user.getBudget() + " VNĐ");

            enrollCourse(userId, courseId, enrollments);

            user.setRole(Role.STUDENT);
            System.out.println("Role của bạn đã được thay đổi thành STUDENT.");
        } else {
            System.out.println("Đăng ký bị hủy. Bạn không thanh toán.");
        }
    }

    private void enrollCourse(String userId, String courseId, ArrayList<Enrollments> enrollments) {
        for (Enrollments enrollment : enrollments) {
            if (enrollment.getUser_id().equals(userId) && enrollment.getCourse_id().equals(courseId)) {
                System.out.println("Bạn đã đăng ký khóa học này rồi.");
                return;
            }
        }

        Enrollments newEnrollment = new Enrollments(userId, courseId, 0);
        enrollments.add(newEnrollment);
        System.out.println("Bạn đã đăng ký khóa học thành công, nhưng chưa thanh toán.");
    }
}
