package view;

import data.DataService;
import entities.*;
import entities.Course;
import enums.Role;
import service.*;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuAdmin {
    UserService userService = new UserService();
    StudentService studentService = new StudentService();
    TeacherService teacherService = new TeacherService();
    PrintService printService = new PrintService();
    CourseService courseService = new CourseService();
    LessonService lessonService = new LessonService();
    ClassService classService = new ClassService();
    BlogService blogService = new BlogService();

    public void displayAdmin(AppContext appContext, User user) {
        Scanner scanner = appContext.getScanner();  // Lấy Scanner từ AppContext

        while (true) {
            System.out.println("\n====== MENU CHỨC NĂNG ======");
            System.out.println("1. Quản lý Học viên");
            System.out.println("2. Quản lý Khóa học");
            System.out.println("3. Quản lý Giáo viên");
            System.out.println("4. Quản lý Blog");
            System.out.println("5. Quản lý Class");
            System.out.println("7. Tìm kiếm");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    manageStudents(appContext);
                    break;
                case 2:
                    manageCourses(appContext,user);
                    break;
                case 3:
                    manageTeacher(appContext);
                    break;
                case 4:
                    manageBlog(appContext, user);
                    break;
                case 5:
                    manageClass(appContext);
                    break;
                case 7:

                    break;
                case 0:
                    System.exit(1);  // Thoát chương trình
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng chọn lại!");
            }
        }
    }

    public void manageStudents(AppContext appContext) {
        Scanner scanner = appContext.getScanner();
        ArrayList<User> users = appContext.getUsers();

        while (true) {
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
                    users.add(studentService.inputStudent());
                    break;
                case 2:
                    changeInfo(appContext);
                    break;
                case 3:
                    userService.deleteUser();
                    break;
                case 4:
                    printService.printInfo(appContext, Role.STUDENT);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Chọn không hợp lệ. Vui lòng thử lại.");
            }
        }
    }
    public void manageTeacher(AppContext appContext) {
        Scanner scanner = appContext.getScanner();
        ArrayList<User> users = appContext.getUsers();
        while (true){
            System.out.println("\n====== QUẢN LÝ GIÁO VIÊN ======");
            System.out.println("1. Thêm giáo viên");
            System.out.println("2. Sửa giáo viên");
            System.out.println("3. Xóa giáo viên");
            System.out.println("4. Hiển thị danh sách giáo viên");
            System.out.println("5. Hiển thị danh sách lớp giảng viên đang dạy");
            System.out.println("6. Xếp lớp cho giảng viên");
            System.out.println("9. Quay lại");
            System.out.print("Chọn chức năng: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    users.add(teacherService.inputTeacher());
                    break;
                case 2:
                    changeInfo(appContext);
                    break;
                case 3:
                    userService.deleteUser();
                    break;
                case 4:
                    printService.printInfo(appContext,Role.TEACHER);
                    break;
                case 5:
                    printService.printTeachingSchedule(appContext);
                    break;
                case 6:

                    break;
                case 9:
                    return;
                default:
                    System.out.println("Chọn không hợp lệ. Vui lòng thử lại.");
            }
        }

    }
    public void manageCourses(AppContext appContext, User user) {
        Scanner scanner = appContext.getScanner();
        ArrayList<Course> courses = appContext.getCourses();
        while (true) {
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
                    courseService.inputCourse(appContext);
                    break;
                case 2:
                    courseService.changeInfoCourse();
                    break;
                case 3:
                    courseService.deleteCourse(appContext);
                    break;
                case 4:
                    printService.printAllCourse(appContext);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Chọn không hợp lệ. Vui lòng thử lại.");
            }
        }
    }
    public void manageLesson(AppContext appContext) {
        Scanner scanner = appContext.getScanner();
        ArrayList<Classes> classes = appContext.getClasses();
        ArrayList<Lesson> lessons = appContext.getLessons();

        while (true) {
            System.out.println("\n====== QUẢN LÝ LESSON ======");
            System.out.println("1. Tạo lesson");
            System.out.println("2. Cập nhật lesson");
            System.out.println("3. Xóa lesson theo ID");
            System.out.println("4. Đổi chỗ hai bài học theo thứ tự");
            System.out.println("7. Hiển thị danh sách lesson theo lớp");
            System.out.println("9. Quay lại");
            System.out.print("Chọn chức năng: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    lessonService.inputLesson(appContext);
                    break;
                case 2:
                    lessonService.changeLesson(appContext);
                    break;
                case 3:
                    lessonService.deleteLesson(appContext);
                    break;
                case 4:
                    lessonService.swapLessonOrders(appContext);
                    break;
                case 7:
                    printService.printLessonByClass(appContext);
                    break;
                case 9:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        }
    }
    public void manageClass(AppContext appContext) {
        Scanner scanner = appContext.getScanner();
        while (true) {
            System.out.println("\n====== QUẢN LÝ LỚP HỌC ======");
            System.out.println("1. Tạo Lớp học");
            System.out.println("2. Thêm học viên vào Lớp học");
            System.out.println("3. Thay đổi khóa học cho Lớp học");
            System.out.println("4. Xóa học viên khỏi Lớp học");
            System.out.println("5. Xóa Lớp học");
            System.out.println("6. Hiển thị danh sách Lớp học");
            System.out.println("7. Thay đổi giáo viên Lớp học");
            System.out.println("9. Quay lại");
            System.out.print("Chọn chức năng: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    // Tạo lớp học
                    classService.inputClass(appContext);
                    break;
                case 2:
                    // Thêm học viên vào lớp học
                    classService.updateStudentToClass(appContext, "add");
                    break;
                case 3:
                    break;
                case 4:
                    classService.updateStudentToClass( appContext, "delete");
                    break;
                case 5:
                    // Xóa lớp học
                    classService.deleteClassRoom(appContext);
                    break;
                case 6:
                    printService.printInfoClass(appContext);
                    break;
                case 7:
                    classService.changeTeacherInClass(appContext);
                    break;
                case 9:
                    return;
                default:
                    System.out.println("Chọn không hợp lệ. Vui lòng thử lại.");
            }
        }
    }

    public void manageBlog(AppContext appContext, User user) {
        Scanner scanner = appContext.getScanner();
        ArrayList<Blog> blogs = appContext.getBlogs();

        while (true) {
            System.out.println("\n====== QUẢN LÝ BLOG ======");
            System.out.println("1. Tạo blog");
            System.out.println("2. Đăng/ huy đăng blog");
            System.out.println("3. Xóa blog theo ID");
            System.out.println("7. Hiển thị danh sách blog");
            System.out.println("9. Quay lại");
            System.out.print("Chọn chức năng: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    blogs.add(blogService.inpuBlog(appContext,user));
                    break;
                case 2:
                    blogService.changeBlogStatus(appContext);
                    break;
                case 3:
                    blogService.deleteBlog(appContext);
                    break;
                case 7:
                    printService.printAllBlogs(appContext);
                    break;
                case 9:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        }
    }



    public void changeInfo(AppContext appContext) {
        Scanner scanner = appContext.getScanner();
        ArrayList<User> users = appContext.getUsers();

        System.out.println("Nhập id user muốn sửa");
        String id = scanner.nextLine();
        User user = userService.findById(id, users);

        if (user == null) {
            System.out.println("Không tồn tại user id: " + id);
        } else {
            while (true) {
                System.out.println("\n====== CẬP NHẬT THÔNG TIN ======");
                System.out.println("1 - Thay đổi username");
                System.out.println("2 - Thay đổi email");
                System.out.println("3 - Thay đổi mật khẩu");
                System.out.println("4 - Thay đổi số điện thoại");
                System.out.println("5 - Thay đổi Role");
                System.out.println("6 - Quay lại");
                System.out.println("0 - Thoát chương trình");
                System.out.print("Chọn chức năng: ");
                int choose = Integer.parseInt(scanner.nextLine());

                switch (choose) {
                    case 1:
                        userService.changeUsername(user);
                        break;
                    case 2:
                        userService.changeEmail(user);
                        break;
                    case 3:
                        userService.changePassword(user);
                        break;
                    case 5:
                        userService.changeRole(user);
                        break;
                    case 6:
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
