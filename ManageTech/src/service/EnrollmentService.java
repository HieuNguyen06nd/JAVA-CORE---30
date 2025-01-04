package service;

import entities.ATMCard;
import entities.Course;
import entities.Enrollments;
import entities.User;
import enums.Role;
import exist.Exist;
import exist.Utils;
import view.Menu;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class EnrollmentService {
    private UserService userService = new UserService();
    private CourseService courseService = new CourseService();
    Utils utils = new Utils();
    Exist exist = new Exist();

    public void enrollCourse(AppContext context) {
        // Kiểm tra xem người dùng đã đăng nhập hay chưa
        String userId = context.getCurrentUserId();

        // Lấy thông tin người dùng
        User user = userService.findById(userId, context.getUsers());
        if (user == null) {
            System.out.println("Người dùng không tồn tại.");
            return;
        }

        // Nhập ID khóa học
        String courseId = context.getScanner().nextLine();
        if (courseId == null || courseId.isEmpty()) {
            System.out.println("ID khóa học không hợp lệ.");
            return;
        }

        // Tìm khóa học
        Course course = courseService.findById(courseId, context.getCourses(), Course::getId);
        if (course == null) {
            System.out.println("Khóa học không tồn tại.");
            return;
        }

        // Kiểm tra xem người dùng đã đăng ký khóa học này chưa
        if (exist.isAlreadyEnrolled(userId, courseId, context.getEnrollments())) {
            System.out.println("Bạn đã đăng ký khóa học này rồi.");
            return;
        }

        // Tạo đăng ký mới
        Enrollments newEnrollment = createEnrollment(userId, courseId, context);
        if (newEnrollment == null) {
            System.out.println("Không thể tạo đăng ký mới.");
            return;
        }

        System.out.println("Bạn đã đăng ký khóa học thành công, nhưng chưa thanh toán.");

        // Hỏi người dùng có muốn thanh toán ngay không
        if (promptForPaymentConfirmation(context.getScanner())) {
            // Xử lý thanh toán
            boolean paymentSuccess = processPaymentMethod(userId, course.getPrice(), context);

            // Cập nhật trạng thái thanh toán nếu thành công
            if (paymentSuccess) {
                newEnrollment.setStatus(1); // 1: đã thanh toán
                System.out.println("Thanh toán thành công!");
            } else {
                System.out.println("Thanh toán thất bại. Bạn có thể thanh toán sau.");
            }
        } else {
            System.out.println("Bạn có thể thanh toán sau.");
        }
    }
    public void payForCourse(AppContext context) {
        Scanner scanner = context.getScanner();
        List<Enrollments> enrollments = context.getList(Enrollments.class); // Lấy danh sách đăng ký
        List<Course> courses = context.getList(Course.class); // Lấy danh sách khóa học

        // Lấy userId từ context (ví dụ: từ phiên đăng nhập hiện tại)
        String userId = context.getCurrentUserId();

        // Nhập ID khóa học cần thanh toán
        System.out.print("Nhập ID khóa học cần thanh toán: ");
        String courseId = scanner.nextLine();

        // Tìm đăng ký khóa học chưa thanh toán của người dùng
        Enrollments enrollment = findUnpaidEnrollment(userId, courseId, enrollments);
        if (enrollment == null) {
            System.out.println("Không tìm thấy khóa học chưa thanh toán với ID: " + courseId);
            return;
        }

        // Tìm thông tin khóa học
        Course course = courseService.findById(courseId, courses, Course::getId);
        if (course == null) {
            System.out.println("Khóa học không tồn tại.");
            return;
        }

        // Hiển thị thông tin khóa học
        System.out.println("Thông tin khóa học:");
        System.out.println("Tên khóa học: " + course.getName());
        System.out.println("Giá tiền: " + course.getPrice());

        // Yêu cầu người dùng xác nhận thanh toán
        System.out.print("Bạn có muốn thanh toán khóa học này không? (yes/no): ");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("yes")) {
            // Gọi phương thức xử lý thanh toán
            boolean paymentSuccess = processPaymentMethod(userId, course.getPrice(), context);

            // Cập nhật trạng thái thanh toán nếu thành công
            if (paymentSuccess) {
                enrollment.setStatus(1); // 1: đã thanh toán
                System.out.println("Thanh toán thành công!");
            } else {
                System.out.println("Thanh toán thất bại.");
            }
        } else {
            System.out.println("Thanh toán đã bị hủy.");
        }
    }
    private boolean processPaymentMethod(String userId, BigDecimal amount, AppContext context) {
        Scanner scanner = context.getScanner();

        // Hiển thị menu lựa chọn phương thức thanh toán
        System.out.println("Chọn phương thức thanh toán:");
        System.out.println("1. Thanh toán bằng thẻ ATM");
        System.out.println("2. Thanh toán bằng số dư ví");
        int paymentMethod = utils.inputInt(scanner, "Nhập lựa chọn của bạn: ");

        // Xử lý lựa chọn
        switch (paymentMethod) {
            case 1:
                // Thanh toán bằng thẻ ATM
                return makePaymentWithATM(userId, amount, context);
            case 2:
                // Thanh toán bằng số dư ví
                return makePaymentWithWallet(userId, amount, context);
            default:
                System.out.println("Lựa chọn không hợp lệ.");
                return false;
        }
    }

    // Phương thức thanh toán bằng ATM
    private boolean makePaymentWithATM(String userId, BigDecimal amount, AppContext context) {
        List<ATMCard> atmCards = context.getList(ATMCard.class);

        // Tìm thẻ ATM của người dùng
        ATMCard userCard = null;
        for (ATMCard card : atmCards) {
            if (card.getUser_id().equals(userId)) {
                userCard = card;
                break;
            }
        }

        if (userCard == null) {
            System.out.println("Bạn chưa có thẻ ATM. Vui lòng liên kết thẻ ATM trước.");
            return false;
        }

        // Nhập mã PIN để xác thực
        System.out.print("Nhập mã PIN (4 chữ số): ");
        String pin = context.getScanner().nextLine();

        if (!userCard.validatePin(Integer.parseInt(pin))) {
            System.out.println("Mã PIN không đúng. Thanh toán thất bại.");
            return false;
        }

        // Kiểm tra số dư thẻ ATM
        if (userCard.getBalance().compareTo(amount) < 0) {
            System.out.println("Số dư thẻ ATM không đủ. Thanh toán thất bại.");
            return false;
        }

        // Trừ tiền từ thẻ ATM
        userCard.setBalance(userCard.getBalance().subtract(amount));
        System.out.println("Thanh toán bằng thẻ ATM thành công.");
        return true;
    }

    // Phương thức thanh toán bằng số dư ví
    private boolean makePaymentWithWallet(String userId, BigDecimal amount, AppContext context) {
        List<User> users = context.getList(User.class);

        // Tìm người dùng
        User user = userService.findById(userId, users);
        if (user == null) {
            System.out.println("Người dùng không tồn tại.");
            return false;
        }

        // Kiểm tra số dư ví
        if (user.getBudget().compareTo(amount) < 0) {
            System.out.println("Số dư ví không đủ. Thanh toán thất bại.");
            return false;
        }

        // Trừ tiền từ ví
        user.setBudget(user.getBudget().subtract(amount));
        System.out.println("Thanh toán bằng số dư ví thành công.");
        return true;
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

    private Enrollments createEnrollment(String userId, String courseId, AppContext context) {
        List<Enrollments> enrollments = context.getList(Enrollments.class);
        Enrollments newEnrollment = new Enrollments(userId, courseId, 0); // 0: chưa thanh toán
        enrollments.add(newEnrollment);
        return newEnrollment;
    }
    private boolean promptForPaymentConfirmation(Scanner scanner) {
        System.out.print("Bạn có muốn thanh toán ngay bây giờ không? (yes/no): ");
        String choice = scanner.nextLine().trim();
        return choice.equalsIgnoreCase("yes");
    }
    private Enrollments findUnpaidEnrollment(String userId, String courseId, List<Enrollments> enrollments) {
        for (Enrollments enrollment : enrollments) {
            // Kiểm tra xem đăng ký có khớp với userId, courseId và chưa thanh toán không
            if (enrollment.getUser_id().equals(userId) &&
                    enrollment.getCourse_id().equals(courseId) &&
                    enrollment.getStatus() == 0) { // 0: chưa thanh toán
                return enrollment;
            }
        }
        return null; // Không tìm thấy đăng ký chưa thanh toán
    }
}