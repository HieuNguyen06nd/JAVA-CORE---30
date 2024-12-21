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
    LessonService lessonService = new LessonService();
    ClassRoomService classRoomService = new ClassRoomService();
    BlogService blogService = new BlogService();


    public void displayAdmin(Scanner scanner,ArrayList<User> users,ArrayList<ClassRoom>classRooms,
                             ArrayList<Lesson>lessons, ArrayList<Blog>blogs, ArrayList<Courses>courses){
        while (true){
            System.out.println("\n====== MENU CHỨC NĂNG ======");
            System.out.println("1. Quản lý Học viên");
            System.out.println("2. Quản lý Khóa học");
            System.out.println("3. Quản lý Giáo viên");
            System.out.println("4. Quản lý Blog");
            System.out.println("5. Quản lý Category");
            System.out.println("6. Quản lý Classroom");
            System.out.println("7. Quản lý Lesson");
            System.out.println("8. Tìm kiếm");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    manageStudents(scanner,users);
                    break;
                case 2:
                    manageCourses(scanner,lessons,classRooms);
                    break;
                case 3:
                    manageTeacher(scanner,users);
                    break;
                case 4:
                    manageBlog(scanner,users,blogs);
                    break;
                case 5:

                    break;
                case 6:
                    mangeInfoClassroom(scanner,classRooms,users,lessons,courses);
                    break;
                case 7:
                   manageLesson(scanner,classRooms,lessons);
                    break;
                case 0:
                    System.exit(1);
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng chọn lại!");
            }
        }
    }
    public void manageCourses(Scanner scanner, ArrayList<Lesson>lessons, ArrayList<ClassRoom>classRooms) {
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
                    lessons.add(lessonService.inputLesson(scanner,classRooms,lessons));
                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:
                    System.out.println(lessons);
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
                    printService.printInfo(users,Role.STUDENT);
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
    public void mangeInfoClassroom(Scanner scanner, ArrayList<ClassRoom>classRooms,
                                   ArrayList<User>users,ArrayList<Lesson>lessons, ArrayList<Courses>courses){
        while (true){
            System.out.println("\n====== QUẢN LÝ LỚP HỌC ======");
            System.out.println("1. Tạo Lớp  học");
            System.out.println("2. Thêm học viên vào Lớp học");
            System.out.println("3. Thêm học courses vào Lớp học");
            System.out.println("4. Xóa học viên khỏi Lớp học");
            System.out.println("5. Xóa courses khỏi Lớp học");
            System.out.println("6. Xóa Lớp học");
            System.out.println("7. Hiển thị danh sách Lớp học");
            System.out.println("9. Quay lại");
            System.out.print("Chọn chức năng: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    classRooms.add(classRoomService.inputClassRoom(scanner, users, lessons,courses));
                    System.out.println("Tạo lớp học thanh công");
                    break;
                case 2:
                    classRoomService.updateStudentToClass(scanner, classRooms,users, "add");
                    break;
                case 3:
                    classRoomService.updateCourseToClass(scanner,classRooms,lessons,"add");
                    break;
                case 4:
                    classRoomService.updateStudentToClass(scanner, classRooms,users, "delete");
                    break;
                case 5:
                    classRoomService.updateCourseToClass(scanner,classRooms,lessons,"delete");
                    break;
                case 6:
                    classRoomService.deleteClassRoom(scanner,classRooms);
                    break;
                case 7:
                    printService.printInfoClass(classRooms,Role.ADMIN);
                    break;
                case 9:
                    return;
                default:
                    System.out.println("Chọn không hợp lệ. Vui lòng thử lại.");
            }
        }
    }
    public void manageBlog(Scanner scanner, ArrayList<User> users, ArrayList<Blog>blogs){
        while (true){
            System.out.println("\n====== QUẢN LÝ BLOG ======");
            System.out.println("1. Tạo Blog");
            System.out.println("2. Update Blog");
            System.out.println("3. Xóa Blog with id: ");
            System.out.println("4. Đăng / hủy đăng Blog");
            System.out.println("7. Hiển thị danh sách Blog");
            System.out.println("9. Quay lại");
            System.out.print("Chọn chức năng: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    blogs.add(blogService.inpuBlog(scanner,users));
                    System.out.println("Tạo Blog thanh công");
                    break;
                case 2:
                    changeInfoBlog(scanner,blogs);
                    break;
                case 3:
                    blogService.deleteBlog(scanner,blogs);
                    break;
                case 4:
                    blogService.changeBlogStatus(scanner,blogs);
                    break;
                case 5:

                    break;
                case 7:
                    printService.printBlogAll(blogs);
                    break;
                case 9:
                    return;
                default:
                    System.out.println("Chọn không hợp lệ. Vui lòng thử lại.");
            }
        }
    }

    public void manageLesson(Scanner scanner, ArrayList<ClassRoom> classRooms, ArrayList<Lesson>lessons){
        while (true){
            System.out.println("\n====== QUẢN LÝ LESSON ======");
            System.out.println("1. Tạo lesson");
            System.out.println("2. Update lesson");
            System.out.println("3. Xóa lesson with id: ");
            System.out.println("4. Đổi chô 2 order lesson");
            System.out.println("7. Hiển thị danh sách lesson with class");
            System.out.println("9. Quay lại");
            System.out.print("Chọn chức năng: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    lessons.add(lessonService.inputLesson(scanner,classRooms,lessons));
                    break;
                case 2:
                    changeInfoLesson(scanner,lessons,classRooms);
                    break;
                case 3:
                    lessonService.deleteLesson(scanner,lessons);
                    break;
                case 4:
                    lessonService.swapLessonOrders(scanner,lessons);
                    break;
                case 7:
                    printService.printLessonByClass(scanner,lessons);
                    break;
                case 9:
                    return;
                default:
                    System.out.println("Chọn không hợp lệ. Vui lòng thử lại.");
            }
        }
    }

    public void manageSearch(Scanner scanner){
        while (true){
            System.out.println("\n====== QUẢN LÝ BLOG ======");
            System.out.println("1. Tìm kiếm user");
            System.out.println("2. Tìm kiếm Blog");
            System.out.println("3. Tìm kiếm Classroom ");
            System.out.println("4. Tìm kiếm Course");
            System.out.println("9. Quay lại");
            System.out.print("Chọn chức năng: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 9:
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
                        userService.changePassword(scanner, user);
                        break;
                    case 4:
                        userService.changePhone(scanner, user);
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
    public void changeInfoCourse(Scanner scanner, ArrayList<Lesson> lessons, ArrayList<User> users){
        System.out.println("Nhâp id Courses muốn sửa");
        String id = scanner.nextLine();
        if (lessonService.findById(id, lessons) ==null){
            System.out.println("Không tồn tại Courses id: "+id);
        }else {
            Lesson lesson = lessonService.findById(id, lessons);
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
//                        lessonService.changeTeacher(scanner,course, users);
                        break;
                    case 2:
//                        lessonService.changeTitle(scanner, course);
                        break;
                    case 3:
//                        lessonService.changeDescription(scanner,course);
                        break;
                    case 4:
//                        lessonService.changePrice(scanner,course);
                        break;
                    case 5:
//                        lessonService.changeStartDate(scanner, course);
                        break;
                    case 6:
//                       lessonService.changeEndDate(scanner, course);
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
    public void changeInfoBlog(Scanner scanner, ArrayList<Blog>blogs){
        System.out.println("Nhâp id blog muốn sửa");
        String id = scanner.nextLine();
        if (blogService.findById(id, blogs) ==null){
            System.out.println("Không tồn tại blog id: "+id);
        }else {
            Blog blog =blogService.findById(id, blogs);
            while (true){
                System.out.println("" +
                        "1 - Thay đổi title\n" +
                        "2 - Thay đổi content\n" +
                        "3 - Thay đổi user_Id\n" +
                        "7 - Quay lại\n" +
                        "0 - Thoát chương trình");
                System.out.print("Chọn chức năng: ");
                int choose = Integer.parseInt(scanner.nextLine());
                switch (choose){
                    case 1:
                        blogService.changeBlogTitle(scanner,blog);
                        break;
                    case 2:
                        blogService.changeBlogContent(scanner,blog);
                        break;
                    case 3:

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
    public void changeInfoLesson(Scanner scanner, ArrayList<Lesson>lessons,ArrayList<ClassRoom>classRooms){
        System.out.println("Nhâp id lesson muốn sửa");
        String id = scanner.nextLine();
        if (lessonService.findById(id, lessons) ==null){
            System.out.println("Không tồn tại lesson id: "+id);
        }else {
            Lesson lesson =lessonService.findById(id, lessons);
            while (true){
                System.out.println("" +
                        "1 - Thay đổi title\n" +
                        "2 - Thay đổi nội dung\n" +
                        "3 - Thay đổi Lớp\n" +
                        "7 - Quay lại\n" +
                        "0 - Thoát chương trình");
                System.out.print("Chọn chức năng: ");
                int choose = Integer.parseInt(scanner.nextLine());
                switch (choose){
                    case 1:
                        lessonService.changeLessonTitle(scanner,lesson);
                        break;
                    case 2:
                        lessonService.changeLessonContent(scanner,lesson);
                        break;
                    case 3:
                        lessonService.changeLessonClass(scanner, lesson,classRooms,lessons);
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
