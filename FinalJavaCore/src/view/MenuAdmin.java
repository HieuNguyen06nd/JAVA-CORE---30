package view;

import entities.*;
import enums.Role;
import service.*;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuAdmin {
    PrintService printService= new PrintService();

    StudentService studentService = new StudentService();
    TeacherService teacherService = new TeacherService();
    UserService userService = new UserService();
    CoursesService coursesService = new CoursesService();


    public void displayAdmin(Scanner scanner,ArrayList<User> users, ArrayList<Teacher> teachers, ArrayList<Courses>courses){
        while (true){
            System.out.println("\n====== MENU CHỨC NĂNG ======");
            System.out.println("1. Quản lý Học viên");
            System.out.println("2. Quản lý Khóa học");
            System.out.println("3. Quản lý Giáo viên");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    manageStudents(scanner,users);
                    break;
                case 2:
                    manageCourses(scanner,courses,teachers);
                    break;
                case 3:
                    manageTeacher(scanner,users);
                    break;
                case 0:
                    System.exit(1);
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng chọn lại!");
            }
        }
    }
    public void manageCourses(Scanner scanner, ArrayList<Courses>courses, ArrayList<Teacher> teachers) {
        while (true){
            System.out.println("\n====== QUẢN LÝ KHÓA HỌC ======");
            System.out.println("1. Thêm khóa học");
            System.out.println("2. Sửa khóa học");
            System.out.println("3. Xóa khóa học");
            System.out.println("4. Hiển thị danh sách khóa học");
            System.out.println("5. Quay lại");
            System.out.print("Chọn chức năng: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    courses.add(coursesService.inputCourse(scanner, teachers));
                    break;
                case 2:
                    changeInfoCourse(scanner,courses,teachers);
                    break;
                case 3:
                    coursesService.deleteCourse(scanner,courses);
                    break;
                case 4:
                    System.out.println(courses);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Chọn không hợp lệ. Vui lòng thử lại.");
            }
        }
    }
    public void manageStudents(Scanner scanner,ArrayList<User> users) {
        while (true){
            System.out.println("\n====== QUẢN LÝ HỌC VIÊN ======");
            System.out.println("1. Thêm học viên");
            System.out.println("2. Sửa học viên");
            System.out.println("3. Xóa học viên");
            System.out.println("4. Hiển thị danh sách học viên");
            System.out.println("5. Quay lại");
            System.out.print("Chọn chức năng: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    users.add(studentService.inputStudent(scanner));
                    break;
                case 2:
                    changeInfo(scanner,users);
                    break;
                case 3:
                    userService.deleteUser(scanner,users);
                    break;
                case 4:
                    printService.printInfo(users,Role.ADMIN);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Chọn không hợp lệ. Vui lòng thử lại.");
            }
        }

    }
    public void manageTeacher(Scanner scanner,ArrayList<User> users) {
        while (true){
            System.out.println("\n====== QUẢN LÝ GIÁO VIÊN ======");
            System.out.println("1. Thêm giáo viên");
            System.out.println("2. Sửa giáo viên");
            System.out.println("3. Xóa giáo viên");
            System.out.println("4. Hiển thị danh sách giáo viên");
            System.out.println("5. Quay lại");
            System.out.print("Chọn chức năng: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    users.add(teacherService.inputTeacher(scanner));
                    break;
                case 2:
                    changeInfo(scanner,users);
                    break;
                case 3:
                    userService.deleteUser(scanner,users);
                    break;
                case 4:
                    printService.printInfo(users,Role.TEACHER);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Chọn không hợp lệ. Vui lòng thử lại.");
            }
        }

    }

    public void changeInfo(Scanner scanner, ArrayList<User> users){
        System.out.println("Nhâp id user muốn sửa");
        String id = scanner.nextLine();
        if (userService.findById(id, users) ==null){
            System.out.println("Không tồn tại user id: "+id);
        }else {
            User user =userService.findById(id, users);
            while (true){
                System.out.println("" +
                        "1 - Thay đổi username\n" +
                        "2 - Thay đổi email\n" +
                        "3 - Thay đổi mật khẩu\n" +
                        "4 - Thay đổi s điện thoại\n" +
                        "5 - Thay đổi Role\n" +
                        "6 - Thay đổi status\n" +
                        "7 - Quay lại\n" +
                        "0 - Thoát chương trình");
                System.out.print("Chọn chức năng: ");
                int choose = Integer.parseInt(scanner.nextLine());
                switch (choose){
                    case 1:
                        userService.changeUsername(scanner,users, user);
                        break;
                    case 2:
                        userService.changeEmail(scanner, users,user);
                        break;
                    case 3:
                        userService.changePassword(scanner, users, user);
                        break;
                    case 4:
                        userService.changePhone(scanner, users, user);
                        break;
                    case 5:
                        userService.changeRole(scanner, users, user);
                        break;
                    case 6:
                        System.out.println(users);
                        break;
                    case 7:
                        return;
                    case 0:
                        System.exit(1);
                    default:
                        System.out.println("Lựa chọn không hợp lệ");
                }
            }
        }

    }
    public void changeInfoCourse(Scanner scanner, ArrayList<Courses> courses, ArrayList<Teacher> teachers){
        System.out.println("Nhâp id Courses muốn sửa");
        String id = scanner.nextLine();
        if (coursesService.findById(id, courses) ==null){
            System.out.println("Không tồn tại Courses id: "+id);
        }else {
            Courses course =coursesService.findById(id, courses);
            while (true){
                System.out.println("" +
                        "1 - Thay đổi teacherId\n" +
                        "2 - Thay đổi tên khóa học \n" +
                        "3 - Thay đổi mô tả khóa học\n" +
                        "4 - Thay đổi giá khóa học\n" +
                        "5 - Thay đổingày bắt đầu khóa học (yyyy-MM-dd)\n" +
                        "6 - Thay đổi ngày kết thúc khóa học (yyyy-MM-dd)\n" +
                        "7 - Quay lại\n" +
                        "0 - Thoát chương trình");
                System.out.print("Chọn chức năng: ");
                int choose = Integer.parseInt(scanner.nextLine());
                switch (choose){
                    case 1:
                        coursesService.changeTeacher(scanner,course, teachers);
                        break;
                    case 2:
                        coursesService.changeTitle(scanner, course);
                        break;
                    case 3:
                        coursesService.changeDescription(scanner,course);
                        break;
                    case 4:
                        coursesService.changePrice(scanner,course);
                        break;
                    case 5:
                        coursesService.changeStartDate(scanner, course);
                        break;
                    case 6:
                       coursesService.changeEndDate(scanner, course);
                        break;
                    case 7:
                        return;
                    case 0:
                        System.exit(1);
                    default:
                        System.out.println("Lựa chọn không hợp lệ");
                }
            }
        }

    }


}
