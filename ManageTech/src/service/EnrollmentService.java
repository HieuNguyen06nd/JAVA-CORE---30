package service;

import entities.Course;
import entities.Enrollments;
import entities.User;
import enums.Role;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class EnrollmentService {
    private UserService userService = new UserService();
    private CourseService courseService = new CourseService();

    public void enrollCourse(String userId, AppContext context) {
        List<User> users = context.getList(User.class); // Lấy danh sách users từ AppContext
        Scanner scanner = context.getScanner();
        List<Course> courses = context.getList(Course.class); // Lấy danh sách courses từ AppContext
        List<Enrollments> enrollments = context.getList(Enrollments.class); // Lấy danh sách enrollments từ AppContext

        User user = userService.findById(userId, users);
        if (user == null) {
            System.out.println("Người dùng không tồn tại.");
            return;
        }

        System.out.println("Nhập ID khóa học:");
        String courseId = scanner.nextLine();

        Course course = courseService.findById(courseId, courses, Course::getId);
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
        List<User> users = context.getList(User.class); // Lấy danh sách users từ AppContext
        Scanner scanner = context.getScanner();
        List<Course> courses = context.getList(Course.class); // Lấy danh sách courses từ AppContext
        List<Enrollments> enrollments = context.getList(Enrollments.class); // Lấy danh sách enrollments từ AppContext

        User user = userService.findById(userId, users);
        if (user == null) {
            System.out.println("Người dùng không tồn tại.");
            return;
        }

        System.out.println("Nhập ID khóa học bạn đã đăng ký để thanh toán:");
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

        Course course = courseService.findById(courseId, courses, Course::getId);
        if (course == null) {
            System.out.println("Khóa học không tồn tại.");
            return;
        }

        if (user.getBudget().compareTo(course.getPrice()) < 0) {
            System.out.println("Ngân sách không đủ để thanh toán khóa học.");
            return;
        }

        user.setBudget(user.getBudget().subtract(course.getPrice()));
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
        List<User> users = context.getList(User.class); // Lấy danh sách users từ AppContext
        Scanner scanner = context.getScanner();

        User user = userService.findById(userId, users);
        if (user == null) {
            System.out.println("Người dùng không tồn tại.");
            return;
        }

        System.out.println("Nhập số tiền bạn muốn nạp vào ngân sách:");
        BigDecimal amount = scanner.nextBigDecimal();
        scanner.nextLine();

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Số tiền nạp vào phải lớn hơn 0.");
            return;
        }

        user.setBudget(user.getBudget().add(amount));
        System.out.println("Nạp tiền thành công! Số tiền trong ngân sách của bạn hiện tại: " + user.getBudget() + " VNĐ.");
    }
}