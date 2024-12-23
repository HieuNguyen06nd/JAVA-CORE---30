package service;

import entities.Course;
import enums.Mode;

import java.util.ArrayList;
import java.util.Scanner;

public class CourseService {

    public void inputCourse(AppContext appContext) {
        Scanner scanner = appContext.getScanner();
        ArrayList<Course> courses = appContext.getCourses();

        System.out.println("Nhập tên khóa học: ");
        String name = scanner.nextLine();

        System.out.println("Nhập mô tả khóa học: ");
        String description = scanner.nextLine();

        System.out.println("Chọn chế độ khóa học: ");
        System.out.println("1. Online");
        System.out.println("2. Offline");
        int modeChoice = Integer.parseInt(scanner.nextLine());
        Mode mode = (modeChoice == 1) ? Mode.ONLINE : Mode.OFFLINE;

        double price = -1;
        while (price < 0) {
            System.out.print("Nhập giá khóa học: ");
            try {
                price = Double.parseDouble(scanner.nextLine());
                if (price < 0) {
                    System.out.println("Giá khóa học không thể nhỏ hơn 0. Vui lòng nhập lại.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Giá không hợp lệ, vui lòng nhập lại.");
            }
        }

        Course course = new Course(name, description,price, mode);
        courses.add(course);

        System.out.println("Khóa học " + name + " đã được thêm thành công!");
    }

    public void changeInfoCourse() {
        AppContext appContext = AppContext.getInstance();
        Scanner scanner = appContext.getScanner();
        ArrayList<Course> courses = appContext.getCourses();

        System.out.println("Nhập ID khóa học cần sửa:");
        String courseId = scanner.nextLine();

        Course course =findById(courseId, courses);
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
                double newPrice = Double.parseDouble(newPriceStr);
                if (newPrice < 0) {
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

    public void deleteCourse(AppContext appContext) {
        Scanner scanner = appContext.getScanner();
        ArrayList<Course> courses = appContext.getCourses();

        System.out.println("Nhập ID khóa học cần xóa: ");
        String courseId = scanner.nextLine();
        Course course = findById(courseId, courses);
        if (course == null) {
            System.out.println("Khóa học không tồn tại với ID: " + courseId);
            return;
        }

        System.out.println("Bạn có chắc chắn muốn xóa không?");
        System.out.println("1. Xóa\n2. Hủy");
        int confirm = Integer.parseInt(scanner.nextLine());
        if (confirm == 1) {
            courses.remove(course);
            System.out.println(" Đã xóa thành công!" + courseId);
        } else {
            System.out.println("Hủy bỏ xóa.");
        }
    }

    public Course findById(String id,ArrayList<Course> courses){
        for (Course course : courses) {
            if (course.getId().equals(id)) {
                return course;
            }
        }
        return null;
    }

}
