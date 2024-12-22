package service;

import entities.Courses;
import entities.User;
import validate.ExistsCheck;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class CoursesService {
    ExistsCheck existsCheck = new ExistsCheck();
    UserService userService = new UserService();
    public Courses inputCourse(Scanner scanner, User user) {

        System.out.print("Nhập tên khóa học: ");
        String title = scanner.nextLine();

        System.out.print("Nhập mô tả khóa học: ");
        String description = scanner.nextLine();

        double price;
        while (true) {
            System.out.print("Nhập giá khóa học: ");
            price = Double.parseDouble(scanner.nextLine());
            if (price > 0) {
                break;
            } else {
                System.out.println("Giá khóa học phải lớn hơn 0, vui lòng nhập lại.");
            }
        }

        String user_id=user.getId();

        System.out.print("Nhập ngày bắt đầu khóa học (yyyy-MM-dd): ");
        LocalDate startDate = LocalDate.parse(scanner.nextLine());

        System.out.print("Nhập ngày kết thúc khóa học (yyyy-MM-dd): ");
        LocalDate endDate = LocalDate.parse(scanner.nextLine());

        return new Courses(title, description, price, user_id,startDate, endDate);
    }


    public void changeTitle(Scanner scanner, Courses course){
        System.out.print("Nhập title mới: ");
        String newTitle = scanner.nextLine();
        course.setTitle(newTitle);
        System.out.println("Thay đổi tên thành công.");
    }
    public void changeDescription(Scanner scanner, Courses course){
        System.out.print("Nhập description mới: ");
        String newDescription = scanner.nextLine();
        course.setDescription(newDescription);
        System.out.println("Thay đổi Description thành công.");
    }
    public void changePrice(Scanner scanner, Courses course){
        System.out.print("Nhập price mới: ");
        double newPrice;
        while (true) {
            newPrice = Double.parseDouble(scanner.nextLine());
            if (newPrice > 0) {
                course.setPrice(newPrice);
                System.out.println("Thay đổi price thành công.");
                break;
            } else {
                System.out.println("Giá khóa học phải lớn hơn 0, vui lòng nhập lại.");
            }
        }
    }

    public void changeStartDate(Scanner scanner, Courses course){
        System.out.print("Nhập ngày kết thúc khóa học (yyyy-MM-dd): ");
        LocalDate startDate = LocalDate.parse(scanner.nextLine());
        course.setStart_date(startDate);
    }

    public void changeEndDate(Scanner scanner, Courses course){
        System.out.print("Nhập ngày kết thúc khóa học (yyyy-MM-dd): ");
        LocalDate endDate = LocalDate.parse(scanner.nextLine());
        course.setEnd_date(endDate);
    }


    public void deleteCourse(Scanner scanner,ArrayList<Courses> courses){
        System.out.print("Nhập id cần xóa: ");
        String id= scanner.nextLine();
        Courses course = findById(id,courses);
        if (course ==null){
            System.out.println("Không tồn tại course id: "+id);
        }else {
            courses.remove(course);
            System.out.println("Đã xóa course id: " + id);
        }

    }

    public Courses findById(String courseId, ArrayList<Courses>courses){
        for (Courses course : courses) {
            if (course.getId().equals(courseId)) {
                return course;
            }
        }
        return null;
    }




}
