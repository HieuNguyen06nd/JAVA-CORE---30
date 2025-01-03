package service;

import entities.Course;
import enums.Mode;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class CourseService {

    /**
     * Nhập thông tin khóa học từ người dùng và thêm vào danh sách.
     */
    public void inputCourse(AppContext appContext) {
        Scanner scanner = appContext.getScanner();
        List<Course> courses = appContext.getList(Course.class);

        String name = inputName(scanner);
        String description = inputDescription(scanner);
        Mode mode = inputMode(scanner);
        BigDecimal price = inputPrice(scanner);

        Course course = new Course(name, description, price, mode);
        courses.add(course);

        System.out.println("Khóa học " + name + " đã được thêm thành công!");
    }

    /**
     * Thay đổi thông tin của một khóa học.
     */
    public void changeInfoCourse(AppContext appContext) {
        Scanner scanner = appContext.getScanner();
        List<Course> courses = appContext.getList(Course.class);

        System.out.println("Nhập ID khóa học cần sửa:");
        String courseId = scanner.nextLine();

        Course course = findById(courseId, courses, Course::getId);
        if (course == null) {
            System.out.println("Khóa học không tồn tại với ID: " + courseId);
            return;
        }

        System.out.println("Thông tin hiện tại của khóa học: ");
        System.out.println("Tên khóa học: " + course.getName());
        System.out.println("Mô tả khóa học: " + course.getDescription());
        System.out.println("Chế độ khóa học: " + course.getMode());
        System.out.println("Giá khóa học: " + course.getPrice());

        System.out.print("Nhập tên mới (hoặc để trống để giữ nguyên): ");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()) {
            course.setName(newName);
        }

        System.out.print("Nhập mô tả mới (hoặc để trống để giữ nguyên): ");
        String newDescription = scanner.nextLine();
        if (!newDescription.isEmpty()) {
            course.setDescription(newDescription);
        }

        System.out.print("Nhập chế độ mới (ONLINE/OFFLINE, hoặc để trống để giữ nguyên): ");
        String newModeStr = scanner.nextLine();
        if (!newModeStr.isEmpty()) {
            try {
                Mode newMode = Mode.valueOf(newModeStr.toUpperCase());
                course.setMode(newMode);
            } catch (IllegalArgumentException e) {
                System.out.println("Chế độ không hợp lệ. Giữ nguyên chế độ cũ.");
            }
        }

        System.out.print("Nhập giá mới (hoặc để trống để giữ nguyên): ");
        String newPriceStr = scanner.nextLine();
        if (!newPriceStr.isEmpty()) {
            try {
                BigDecimal newPrice = new BigDecimal(newPriceStr);
                if (newPrice.compareTo(BigDecimal.ZERO) < 0) {
                    System.out.println("Giá khóa học không thể nhỏ hơn 0. Giữ nguyên giá cũ.");
                } else {
                    course.setPrice(newPrice);
                }
            } catch (NumberFormatException e) {
                System.out.println("Giá không hợp lệ. Giữ nguyên giá cũ.");
            }
        }

        System.out.println("Thông tin khóa học đã được cập nhật.");
    }

    /**
     * Xóa một khóa học khỏi danh sách.
     */
    public void deleteCourse(AppContext appContext) {
        Scanner scanner = appContext.getScanner();
        List<Course> courses = appContext.getList(Course.class);

        System.out.println("Nhập ID khóa học cần xóa: ");
        String courseId = scanner.nextLine();
        Course course = findById(courseId, courses, Course::getId);
        if (course == null) {
            System.out.println("Khóa học không tồn tại với ID: " + courseId);
            return;
        }

        System.out.println("Bạn có chắc chắn muốn xóa không?");
        System.out.println("1. Xóa\n2. Hủy");
        int confirm = inputInt(scanner, "Lựa chọn của bạn: ");
        if (confirm == 1) {
            courses.remove(course);
            System.out.println("Đã xóa thành công khóa học với ID: " + courseId);
        } else {
            System.out.println("Hủy bỏ xóa.");
        }
    }

    public Course findById(String id, List<Course> courses, Function<Course, String> idExtractor) {
        for (Course course : courses) {
            if (idExtractor.apply(course).equalsIgnoreCase(id)) {
                return course;
            }
        }
        return null;
    }

    private String inputName(Scanner scanner) {
        System.out.println("Nhập tên khóa học: ");
        return scanner.nextLine();
    }


    private String inputDescription(Scanner scanner) {
        System.out.println("Nhập mô tả khóa học: ");
        return scanner.nextLine();
    }

    private Mode inputMode(Scanner scanner) {
        System.out.println("Chọn chế độ khóa học: ");
        System.out.println("1. Online");
        System.out.println("2. Offline");
        int modeChoice = inputInt(scanner, "Lựa chọn của bạn: ");
        return (modeChoice == 1) ? Mode.ONLINE : Mode.OFFLINE;
    }

    private BigDecimal inputPrice(Scanner scanner) {
        BigDecimal price = null;
        while (price == null || price.compareTo(BigDecimal.ZERO) < 0) {
            System.out.print("Nhập giá khóa học: ");
            try {
                price = new BigDecimal(scanner.nextLine());
                if (price.compareTo(BigDecimal.ZERO) < 0) {
                    System.out.println("Giá khóa học không thể nhỏ hơn 0. Vui lòng nhập lại.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Giá không hợp lệ, vui lòng nhập lại.");
            }
        }
        return price;
    }

    private int inputInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Giá trị nhập vào không phải là số. Vui lòng nhập lại.");
            }
        }
    }
}