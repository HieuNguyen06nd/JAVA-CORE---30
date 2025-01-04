package service;

import entities.*;
import enums.Major;
import enums.Role;
import exist.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PrintService {
    private Utils utils = new Utils();

    // User
    public void printInfo(AppContext appContext, Role role) {
        List<User> users = appContext.getList(User.class); // Lấy danh sách users từ AppContext

        // Tạo danh sách người dùng có vai trò tương ứng
        List<User> roleUsers = new ArrayList<>();
        for (User user : users) {
            if (user != null && user.getRole().equals(role)) {
                roleUsers.add(user);
            }
        }

        // Kiểm tra nếu không có người dùng nào có vai trò tương ứng
        if (roleUsers.isEmpty()) {
            System.out.println("Không có người dùng nào có vai trò " + role);
            return;
        }

        // Độ rộng của các cột
        int idWidth = 10;
        int nameWidth = 20;
        int emailWidth = 30;
        int roleWidth = 15;

        // In ra danh sách người dùng với định dạng bảng
        System.out.println("=== Thông tin người dùng có vai trò " + role + " ===");
        System.out.println("+" + "-".repeat(idWidth + 2) + "+" + "-".repeat(nameWidth + 2) + "+" + "-".repeat(emailWidth + 2) + "+" + "-".repeat(roleWidth + 2) + "+");
        System.out.printf("| %-" + idWidth + "s | %-" + nameWidth + "s | %-" + emailWidth + "s | %-" + roleWidth + "s |\n", "ID", "Tên", "Email", "Vai trò");
        System.out.println("+" + "-".repeat(idWidth + 2) + "+" + "-".repeat(nameWidth + 2) + "+" + "-".repeat(emailWidth + 2) + "+" + "-".repeat(roleWidth + 2) + "+");

        for (User user : roleUsers) {
            // Sử dụng phương thức từ lớp Utils
            String id = Utils.truncateString(user.getId(), idWidth);
            String name = Utils.wrapString(user.getUsername(), nameWidth);
            String email = Utils.wrapString(user.getEmail(), emailWidth);
            String roleStr = Utils.truncateString(user.getRole().toString(), roleWidth);

            // In từng dòng dữ liệu
            String[] nameLines = name.split("\n");
            String[] emailLines = email.split("\n");
            int maxLines = Math.max(nameLines.length, emailLines.length);

            for (int i = 0; i < maxLines; i++) {
                String nameLine = (i < nameLines.length) ? nameLines[i] : "";
                String emailLine = (i < emailLines.length) ? emailLines[i] : "";
                if (i == 0) {
                    System.out.printf("| %-" + idWidth + "s | %-" + nameWidth + "s | %-" + emailWidth + "s | %-" + roleWidth + "s |\n", id, nameLine, emailLine, roleStr);
                } else {
                    System.out.printf("| %-" + idWidth + "s | %-" + nameWidth + "s | %-" + emailWidth + "s | %-" + roleWidth + "s |\n", "", nameLine, emailLine, "");
                }
            }
            System.out.println("+" + "-".repeat(idWidth + 2) + "+" + "-".repeat(nameWidth + 2) + "+" + "-".repeat(emailWidth + 2) + "+" + "-".repeat(roleWidth + 2) + "+");
        }
    }

    private void printTeacherInfo(Teacher teacher, int labelWidth, int valueWidth) {
        // In thông tin chung từ lớp User
        printUserInfo(teacher, labelWidth, valueWidth);

        // Sử dụng phương thức từ lớp Utils
        String salary = Utils.truncateString(String.valueOf(teacher.getSalary()), valueWidth);
        String experience = Utils.truncateString(String.valueOf(teacher.getExperience()), valueWidth);

        // Xử lý danh sách các chuyên môn
        List<Major> majors = teacher.getMajors();
        String majorsString = (majors != null && !majors.isEmpty())
                ? majors.stream()
                .map(Major::name) // Chuyển đổi từng Major thành tên
                .collect(Collectors.joining(", ")) // Nối các tên bằng dấu phẩy
                : "Không có thông tin";

        // In từng dòng dữ liệu
        System.out.printf("| %-" + labelWidth + "s | %-" + valueWidth + "s |\n", "Lương", TeacherService.calculateSalary(teacher));
        System.out.printf("| %-" + labelWidth + "s | %-" + valueWidth + "s |\n", "Kinh nghiệm", experience);
        System.out.printf("| %-" + labelWidth + "s | %-" + valueWidth + "s |\n", "Chuyên môn", majorsString);
    }

    private void printStudentInfo(Student student, int labelWidth, int valueWidth) {
        // In thông tin chung từ lớp User
        printUserInfo(student, labelWidth, valueWidth);

        // Sử dụng phương thức từ lớp Utils
        String educationLevel = Utils.truncateString(student.getEducation_level(), valueWidth);

        // In từng dòng dữ liệu
        System.out.printf("| %-" + labelWidth + "s | %-" + valueWidth + "s |\n", "Trình độ học vấn", educationLevel);
        System.out.println("+" + "-".repeat(labelWidth + 2) + "+" + "-".repeat(valueWidth + 2) + "+");
    }


    public void printUserDetails(String userId, AppContext context) {
        List<User> users = context.getList(User.class); // Lấy danh sách users từ AppContext

        // Tìm người dùng theo ID
        User user = findById(userId, users, User::getId);
        if (user == null) {
            System.out.println("Không tìm thấy người dùng với ID: " + userId);
            return;
        }

        // Độ rộng của các cột
        int labelWidth = 20;
        int valueWidth = 40;

        // In ra thông tin chi tiết người dùng với định dạng bảng
        System.out.println("=== Chi tiết Người dùng ===");
        System.out.println("+" + "-".repeat(labelWidth + 2) + "+" + "-".repeat(valueWidth + 2) + "+");
        System.out.printf("| %-" + labelWidth + "s | %-" + valueWidth + "s |\n", "Trường", "Giá trị");
        System.out.println("+" + "-".repeat(labelWidth + 2) + "+" + "-".repeat(valueWidth + 2) + "+");

        // In thông tin cụ thể theo vai trò
        if (user.getRole() == Role.TEACHER && user instanceof Teacher) {
            printTeacherInfo((Teacher) user, labelWidth, valueWidth); // Ép kiểu sang Teacher
        } else if (user.getRole() == Role.STUDENT && user instanceof Student) {
            printStudentInfo((Student) user, labelWidth, valueWidth); // Ép kiểu sang Student
        }

        System.out.println("+" + "-".repeat(labelWidth + 2) + "+" + "-".repeat(valueWidth + 2) + "+");
    }


    private void printUserInfo(User user, int labelWidth, int valueWidth) {
        // Sử dụng phương thức từ lớp Utils
        String id = Utils.truncateString(user.getId(), valueWidth);
        String username = Utils.truncateString(user.getUsername(), valueWidth);
        String email = Utils.truncateString(user.getEmail(), valueWidth);
        String budget = Utils.truncateString(String.valueOf(user.getBudget()), valueWidth);
        String createdAt = Utils.truncateString(user.getCreated_at().toString(), valueWidth);
        String role = Utils.truncateString(user.getRole().toString(), valueWidth);

        // In từng dòng dữ liệu
        System.out.printf("| %-" + labelWidth + "s | %-" + valueWidth + "s |\n", "ID", id);
        System.out.printf("| %-" + labelWidth + "s | %-" + valueWidth + "s |\n", "Tên người dùng", username);
        System.out.printf("| %-" + labelWidth + "s | %-" + valueWidth + "s |\n", "Email", email);
        System.out.printf("| %-" + labelWidth + "s | %-" + valueWidth + "s |\n", "Budget", budget);
        System.out.printf("| %-" + labelWidth + "s | %-" + valueWidth + "s |\n", "Ngày tạo", createdAt);
        System.out.printf("| %-" + labelWidth + "s | %-" + valueWidth + "s |\n", "Vai trò", role);
        System.out.println("+" + "-".repeat(labelWidth + 2) + "+" + "-".repeat(valueWidth + 2) + "+");
    }

    public void printTeachingSchedule(AppContext context) {
        List<Classes> classes = context.getList(Classes.class); // Lấy danh sách lớp học từ AppContext
        Scanner scanner = context.getScanner();

        // Nhập ID giảng viên
        System.out.print("Nhập ID giảng viên để xem lịch dạy: ");
        String teacherId = scanner.nextLine();

        // Tìm các lớp học mà giảng viên đang dạy
        List<Classes> teachingClasses = new ArrayList<>();
        for (Classes classRoom : classes) {
            if (classRoom != null && classRoom.getTeacher_id().equals(teacherId)) {
                teachingClasses.add(classRoom);
            }
        }

        // Kiểm tra nếu không có lớp học nào
        if (teachingClasses.isEmpty()) {
            System.out.println("Giảng viên với ID " + teacherId + " không có lớp học nào.");
            return;
        }

        // Độ rộng của các cột
        int idWidth = 10;
        int nameWidth = 20;
        int teacherWidth = 15;
        int startDateWidth = 12;
        int endDateWidth = 12;
        int studentWidth = 20;

        // In ra danh sách lớp học với định dạng bảng
        System.out.println("=== Lịch dạy của giảng viên " + teacherId + " ===");
        System.out.println("+" + "-".repeat(idWidth + 2) + "+" + "-".repeat(nameWidth + 2) + "+" + "-".repeat(teacherWidth + 2) + "+" + "-".repeat(startDateWidth + 2) + "+" + "-".repeat(endDateWidth + 2) + "+" + "-".repeat(studentWidth + 2) + "+");
        System.out.printf("| %-" + idWidth + "s | %-" + nameWidth + "s | %-" + teacherWidth + "s | %-" + startDateWidth + "s | %-" + endDateWidth + "s | %-" + studentWidth + "s |\n", "ID Lớp", "Tên Lớp", "Giảng viên", "Ngày bắt đầu", "Ngày kết thúc", "Học viên");
        System.out.println("+" + "-".repeat(idWidth + 2) + "+" + "-".repeat(nameWidth + 2) + "+" + "-".repeat(teacherWidth + 2) + "+" + "-".repeat(startDateWidth + 2) + "+" + "-".repeat(endDateWidth + 2) + "+" + "-".repeat(studentWidth + 2) + "+");

        for (Classes classRoom : teachingClasses) {
            printClassInfo(classRoom, idWidth, nameWidth, teacherWidth, startDateWidth, endDateWidth, studentWidth);
        }
    }

    // Class
    public void printInfoClass(AppContext context) {
        List<Classes> classes = context.getList(Classes.class); // Lấy danh sách classes từ AppContext
        Scanner scanner = context.getScanner();

        System.out.print("Nhập khóa học ID: ");
        String courseId = scanner.nextLine();

        // Tìm các lớp học thuộc khóa học
        List<Classes> classRooms = new ArrayList<>();
        for (Classes classRoom : classes) {
            if (classRoom != null && classRoom.getCourse_id().equals(courseId)) {
                classRooms.add(classRoom);
            }
        }

        // Kiểm tra nếu không có lớp học nào
        if (classRooms.isEmpty()) {
            System.out.println("Không có lớp học nào thuộc khóa học ID: " + courseId);
            return;
        }

        // Độ rộng của các cột
        int idWidth = 10;
        int nameWidth = 20;
        int teacherWidth = 15;
        int startDateWidth = 12;
        int endDateWidth = 12;
        int studentWidth = 20;

        // In ra danh sách lớp học với định dạng bảng
        System.out.println("=== Danh sách lớp học theo khóa học ID: " + courseId + " ===");
        System.out.println("+" + "-".repeat(idWidth + 2) + "+" + "-".repeat(nameWidth + 2) + "+" + "-".repeat(teacherWidth + 2) + "+" + "-".repeat(startDateWidth + 2) + "+" + "-".repeat(endDateWidth + 2) + "+" + "-".repeat(studentWidth + 2) + "+");
        System.out.printf("| %-" + idWidth + "s | %-" + nameWidth + "s | %-" + teacherWidth + "s | %-" + startDateWidth + "s | %-" + endDateWidth + "s | %-" + studentWidth + "s |\n", "ID Lớp", "Tên Lớp", "Giảng viên", "Ngày bắt đầu", "Ngày kết thúc", "Học viên");
        System.out.println("+" + "-".repeat(idWidth + 2) + "+" + "-".repeat(nameWidth + 2) + "+" + "-".repeat(teacherWidth + 2) + "+" + "-".repeat(startDateWidth + 2) + "+" + "-".repeat(endDateWidth + 2) + "+" + "-".repeat(studentWidth + 2) + "+");

        for (Classes classRoom : classRooms) {
            printClassInfo(classRoom, idWidth, nameWidth, teacherWidth, startDateWidth, endDateWidth, studentWidth);
        }
    }

    public void printClassesForTeacher(AppContext context, User teacher) {
        Scanner scanner = context.getScanner();
        List<Classes> classes = context.getList(Classes.class); // Lấy danh sách lớp học từ AppContext

        // Tạo danh sách các lớp mà giáo viên đang dạy
        List<Classes> teacherClasses = new ArrayList<>();
        for (Classes classRoom : classes) {
            if (classRoom.getTeacher_id().equals(teacher.getId())) {
                teacherClasses.add(classRoom);
            }
        }

        // Kiểm tra nếu giáo viên không dạy lớp nào
        if (teacherClasses.isEmpty()) {
            System.out.println("Giáo viên " + teacher.getUsername() + " không dạy lớp học nào.");
            return;
        }

        // Độ rộng của các cột
        int indexWidth = 5;
        int idWidth = 10;
        int nameWidth = 30;

        // In ra danh sách các lớp mà giáo viên đang dạy
        System.out.println("=== Danh sách lớp học của giáo viên " + teacher.getUsername() + " ===");
        System.out.println("+" + "-".repeat(indexWidth + 2) + "+" + "-".repeat(idWidth + 2) + "+" + "-".repeat(nameWidth + 2) + "+");
        System.out.printf("| %-" + indexWidth + "s | %-" + idWidth + "s | %-" + nameWidth + "s |\n", "STT", "ID Lớp", "Tên Lớp");
        System.out.println("+" + "-".repeat(indexWidth + 2) + "+" + "-".repeat(idWidth + 2) + "+" + "-".repeat(nameWidth + 2) + "+");

        for (int i = 0; i < teacherClasses.size(); i++) {
            Classes classRoom = teacherClasses.get(i);
            String index = Utils.truncateString(String.valueOf(i + 1), indexWidth);
            String id = Utils.truncateString(classRoom.getId(), idWidth);
            String name = Utils.truncateString(classRoom.getName(), nameWidth);
            System.out.printf("| %-" + indexWidth + "s | %-" + idWidth + "s | %-" + nameWidth + "s |\n", index, id, name);
        }
        System.out.println("+" + "-".repeat(indexWidth + 2) + "+" + "-".repeat(idWidth + 2) + "+" + "-".repeat(nameWidth + 2) + "+");
    }

    private void printClassInfo(Classes classRoom, int idWidth, int nameWidth, int teacherWidth, int startDateWidth, int endDateWidth, int studentWidth) {
        // Sử dụng phương thức từ lớp Utils
        String id = Utils.truncateString(classRoom.getId(), idWidth);
        String name = Utils.truncateString(classRoom.getName(), nameWidth);
        String teacher = Utils.truncateString(classRoom.getTeacher_id(), teacherWidth);
        String startDate = Utils.truncateString(classRoom.getStart_date().toString(), startDateWidth);
        String endDate = Utils.truncateString(classRoom.getEnd_date().toString(), endDateWidth);
        String students = (classRoom.getStudent_id() != null && !classRoom.getStudent_id().isEmpty())
                ? Utils.truncateString(String.join(", ", classRoom.getStudent_id()), studentWidth)
                : "Chưa có học viên";

        // In từng dòng dữ liệu
        System.out.printf("| %-" + idWidth + "s | %-" + nameWidth + "s | %-" + teacherWidth + "s | %-" + startDateWidth + "s | %-" + endDateWidth + "s | %-" + studentWidth + "s |\n", id, name, teacher, startDate, endDate, students);
        System.out.println("+" + "-".repeat(idWidth + 2) + "+" + "-".repeat(nameWidth + 2) + "+" + "-".repeat(teacherWidth + 2) + "+" + "-".repeat(startDateWidth + 2) + "+" + "-".repeat(endDateWidth + 2) + "+" + "-".repeat(studentWidth + 2) + "+");
    }

    public void printAllClassRoomsByUser(String userId, AppContext context) {
        List<User> users = context.getList(User.class); // Lấy danh sách users từ AppContext
        List<Classes> classes = context.getList(Classes.class); // Lấy danh sách classes từ AppContext

        // Tìm người dùng theo ID
        User user = findById(userId, users, User::getId);
        if (user == null) {
            System.out.println("Người dùng không tồn tại.");
            return;
        }

        // Độ rộng của các cột
        int labelWidth = 20;
        int valueWidth = 40;

        // In thông tin người dùng
        System.out.println("=== Danh sách Lớp học của người dùng ===");
        System.out.println("Thông tin người dùng:");
        printUserInfo(user, labelWidth, valueWidth); // Gọi phương thức với tham số

        // Tìm các lớp học liên quan đến người dùng
        List<Classes> userClasses = new ArrayList<>();
        if (user.getRole() == Role.TEACHER) {
            System.out.println("\nLớp học giảng dạy:");
            for (Classes classRoom : classes) {
                if (classRoom.getTeacher_id().equals(userId)) {
                    userClasses.add(classRoom);
                }
            }
        } else if (user.getRole() == Role.STUDENT) {
            System.out.println("\nLớp học tham gia:");
            for (Classes classRoom : classes) {
                if (classRoom.getStudent_id() != null && classRoom.getStudent_id().contains(userId)) {
                    userClasses.add(classRoom);
                }
            }
        }

        // Kiểm tra nếu không có lớp học nào
        if (userClasses.isEmpty()) {
            System.out.println("Bạn không tham gia hoặc giảng dạy bất kỳ lớp học nào.");
            return;
        }

        // Độ rộng của các cột
        int idWidth = 10;
        int nameWidth = 20;
        int teacherWidth = 15;
        int studentCountWidth = 10;

        // In ra danh sách lớp học với định dạng bảng
        System.out.println("+" + "-".repeat(idWidth + 2) + "+" + "-".repeat(nameWidth + 2) + "+" + "-".repeat(teacherWidth + 2) + "+" + "-".repeat(studentCountWidth + 2) + "+");
        System.out.printf("| %-" + idWidth + "s | %-" + nameWidth + "s | %-" + teacherWidth + "s | %-" + studentCountWidth + "s |\n", "ID Lớp", "Tên Lớp", "Giảng viên", "Số HV");
        System.out.println("+" + "-".repeat(idWidth + 2) + "+" + "-".repeat(nameWidth + 2) + "+" + "-".repeat(teacherWidth + 2) + "+" + "-".repeat(studentCountWidth + 2) + "+");

        for (Classes classRoom : userClasses) {
            printClassRoomSummary(classRoom, idWidth, nameWidth, teacherWidth, studentCountWidth);
        }
    }

    private void printClassRoomSummary(Classes classRoom, int idWidth, int nameWidth, int teacherWidth, int studentCountWidth) {
        // Sử dụng phương thức từ lớp Utils
        String id = Utils.truncateString(classRoom.getId(), idWidth);
        String name = Utils.truncateString(classRoom.getName(), nameWidth);
        String teacher = Utils.truncateString(classRoom.getTeacher_id(), teacherWidth);
        String studentCount = Utils.truncateString(String.valueOf(classRoom.getStudent_id() != null ? classRoom.getStudent_id().size() : 0), studentCountWidth);

        // In từng dòng dữ liệu
        System.out.printf("| %-" + idWidth + "s | %-" + nameWidth + "s | %-" + teacherWidth + "s | %-" + studentCountWidth + "s |\n", id, name, teacher, studentCount);
        System.out.println("+" + "-".repeat(idWidth + 2) + "+" + "-".repeat(nameWidth + 2) + "+" + "-".repeat(teacherWidth + 2) + "+" + "-".repeat(studentCountWidth + 2) + "+");
    }

    // Course
    public void printAllCourse(AppContext context) {
        List<Course> courses = context.getList(Course.class); // Lấy danh sách courses từ AppContext

        // Kiểm tra nếu không có khóa học nào
        if (courses.isEmpty()) {
            System.out.println("Hiện tại không có khóa học nào.");
            return;
        }

        // Độ rộng của các cột
        int idWidth = 10;
        int nameWidth = 30;
        int descriptionWidth = 50;
        int priceWidth = 10;

        // In ra danh sách khóa học với định dạng bảng
        System.out.println("=== Danh sách khóa học ===");
        System.out.println("+" + "-".repeat(idWidth + 2) + "+" + "-".repeat(nameWidth + 2) + "+" + "-".repeat(descriptionWidth + 2) + "+" + "-".repeat(priceWidth + 2) + "+");
        System.out.printf("| %-" + idWidth + "s | %-" + nameWidth + "s | %-" + descriptionWidth + "s | %-" + priceWidth + "s |\n", "ID", "Tên khóa học", "Mô tả", "Giá");
        System.out.println("+" + "-".repeat(idWidth + 2) + "+" + "-".repeat(nameWidth + 2) + "+" + "-".repeat(descriptionWidth + 2) + "+" + "-".repeat(priceWidth + 2) + "+");

        for (Course course : courses) {
            printCourseInfo(course, idWidth, nameWidth, descriptionWidth, priceWidth);
        }
    }

    private void printCourseInfo(Course course, int idWidth, int nameWidth, int descriptionWidth, int priceWidth) {
        // Sử dụng phương thức từ lớp Utils
        String id = Utils.truncateString(course.getId(), idWidth);
        String name = Utils.truncateString(course.getName(), nameWidth);
        String description = Utils.wrapString(course.getDescription(), descriptionWidth);
        String price = Utils.truncateString(String.valueOf(course.getPrice()), priceWidth);

        // In từng dòng dữ liệu
        String[] descriptionLines = description.split("\n");
        int maxLines = descriptionLines.length;

        for (int i = 0; i < maxLines; i++) {
            String descriptionLine = descriptionLines[i];
            if (i == 0) {
                System.out.printf("| %-" + idWidth + "s | %-" + nameWidth + "s | %-" + descriptionWidth + "s | %-" + priceWidth + "s |\n", id, name, descriptionLine, price);
            } else {
                System.out.printf("| %-" + idWidth + "s | %-" + nameWidth + "s | %-" + descriptionWidth + "s | %-" + priceWidth + "s |\n", "", "", descriptionLine, "");
            }
        }
        System.out.println("+" + "-".repeat(idWidth + 2) + "+" + "-".repeat(nameWidth + 2) + "+" + "-".repeat(descriptionWidth + 2) + "+" + "-".repeat(priceWidth + 2) + "+");
    }

    public void printCourseById(String courseId, AppContext context) {
        List<Course> courses = context.getList(Course.class); // Lấy danh sách courses từ AppContext

        // Tìm khóa học theo ID
        Course course = findById(courseId, courses, Course::getId);
        if (course == null) {
            System.out.println("Không tìm thấy khóa học với ID: " + courseId);
            return;
        }

        // Độ rộng của các cột
        int idWidth = 10;
        int nameWidth = 30;
        int descriptionWidth = 50;
        int priceWidth = 10;

        // In ra thông tin chi tiết khóa học với định dạng bảng
        System.out.println("=== Chi tiết khóa học ===");
        System.out.println("+" + "-".repeat(idWidth + 2) + "+" + "-".repeat(nameWidth + 2) + "+" + "-".repeat(descriptionWidth + 2) + "+" + "-".repeat(priceWidth + 2) + "+");
        System.out.printf("| %-" + idWidth + "s | %-" + nameWidth + "s | %-" + descriptionWidth + "s | %-" + priceWidth + "s |\n", "ID", "Tên khóa học", "Mô tả", "Giá");
        System.out.println("+" + "-".repeat(idWidth + 2) + "+" + "-".repeat(nameWidth + 2) + "+" + "-".repeat(descriptionWidth + 2) + "+" + "-".repeat(priceWidth + 2) + "+");

        printCourseInfo(course, idWidth, nameWidth, descriptionWidth, priceWidth);
    }

    public void printCourseByUserId(String user_id, AppContext context, int status) {
        List<Enrollments> enrollments = context.getList(Enrollments.class); // Lấy danh sách enrollments từ AppContext

        // Kiểm tra nếu không có khóa học nào
        boolean found = false;
        for (Enrollments enrollment : enrollments) {
            if (enrollment.getUser_id().equals(user_id) && enrollment.getStatus() == status) {
                found = true;
                printCourseById(enrollment.getCourse_id(), context);
            }
        }

        if (!found) {
            System.out.println("Không tìm thấy khóa học nào cho người dùng với ID: " + user_id + " và trạng thái: " + status);
        }
    }

    // Blog
    public void printAllBlogs(AppContext context) {
        List<Blog> blogs = context.getList(Blog.class); // Lấy danh sách blogs từ AppContext

        // Kiểm tra nếu không có blog nào
        if (blogs.isEmpty()) {
            System.out.println("Hiện tại không có blog nào.");
            return;
        }

        // Độ rộng của các cột
        int idWidth = 10;
        int titleWidth = 30;
        int contentWidth = 40;
        int authorWidth = 15;
        int statusWidth = 15;

        // In ra danh sách blog với định dạng bảng
        System.out.println("=== Danh sách các blog ===");
        System.out.println("+" + "-".repeat(idWidth + 2) + "+" + "-".repeat(titleWidth + 2) + "+" + "-".repeat(contentWidth + 2) + "+" + "-".repeat(authorWidth + 2) + "+" + "-".repeat(statusWidth + 2) + "+");
        System.out.printf("| %-" + idWidth + "s | %-" + titleWidth + "s | %-" + contentWidth + "s | %-" + authorWidth + "s | %-" + statusWidth + "s |\n", "ID", "Tiêu đề", "Nội dung", "Tác giả (ID)", "Trạng thái");
        System.out.println("+" + "-".repeat(idWidth + 2) + "+" + "-".repeat(titleWidth + 2) + "+" + "-".repeat(contentWidth + 2) + "+" + "-".repeat(authorWidth + 2) + "+" + "-".repeat(statusWidth + 2) + "+");

        for (Blog blog : blogs) {
            printBlogInfo(blog, idWidth, titleWidth, contentWidth, authorWidth, statusWidth);
        }
    }

    private void printBlogInfo(Blog blog, int idWidth, int titleWidth, int contentWidth, int authorWidth, int statusWidth) {
        // Sử dụng phương thức từ lớp Utils
        String id = Utils.truncateString(blog.getId(), idWidth);
        String title = Utils.wrapString(blog.getTitle(), titleWidth);
        String content = Utils.wrapString(blog.getContent(), contentWidth);
        String author = Utils.truncateString(blog.getUser_Id(), authorWidth);
        String status = Utils.truncateString(blog.isStatus() ? "Đăng" : "Hủy Đăng", statusWidth);

        // In từng dòng dữ liệu
        String[] titleLines = title.split("\n");
        String[] contentLines = content.split("\n");
        int maxLines = Math.max(titleLines.length, contentLines.length);

        for (int i = 0; i < maxLines; i++) {
            String titleLine = (i < titleLines.length) ? titleLines[i] : "";
            String contentLine = (i < contentLines.length) ? contentLines[i] : "";
            if (i == 0) {
                System.out.printf("| %-" + idWidth + "s | %-" + titleWidth + "s | %-" + contentWidth + "s | %-" + authorWidth + "s | %-" + statusWidth + "s |\n", id, titleLine, contentLine, author, status);
            } else {
                System.out.printf("| %-" + idWidth + "s | %-" + titleWidth + "s | %-" + contentWidth + "s | %-" + authorWidth + "s | %-" + statusWidth + "s |\n", "", titleLine, contentLine, "", "");
            }
        }
        System.out.println("+" + "-".repeat(idWidth + 2) + "+" + "-".repeat(titleWidth + 2) + "+" + "-".repeat(contentWidth + 2) + "+" + "-".repeat(authorWidth + 2) + "+" + "-".repeat(statusWidth + 2) + "+");
    }

//score


    public void printStudentScoresByClassAndLesson(AppContext context, User currentUser) {
        Scanner scanner = context.getScanner();
        List<Classes> classes = context.getList(Classes.class); // Lấy danh sách lớp học
        List<Lesson> lessons = context.getList(Lesson.class); // Lấy danh sách bài học
        List<Score> scores = context.getList(Score.class); // Lấy danh sách điểm
        List<User> users = context.getList(User.class); // Lấy danh sách người dùng

        // Nhập ID lớp học
        System.out.print("Nhập ID lớp học: ");
        String classId = scanner.nextLine();

        // Tìm lớp học theo ID
        Classes classRoom = findById(classId, classes, Classes::getId);
        if (classRoom == null) {
            System.out.println("Không tìm thấy lớp học với ID: " + classId);
            return;
        }

        // Kiểm tra xem giáo viên hiện tại có dạy lớp này không
        if (!classRoom.getTeacher_id().equals(currentUser.getId())) {
            System.out.println("Bạn không dạy lớp học này.");
            return;
        }

        // Hiển thị thông tin lớp học
        System.out.println("=== Thông tin lớp học ===");
        System.out.println("ID Lớp học: " + classRoom.getId());
        System.out.println("Tên lớp học: " + classRoom.getName());
        System.out.println("Giảng viên: " + classRoom.getTeacher_id());
        System.out.println("Ngày bắt đầu: " + classRoom.getStart_date());
        System.out.println("Ngày kết thúc: " + classRoom.getEnd_date());

        // Lấy danh sách bài học trong lớp
        List<Lesson> classLessons = new ArrayList<>();
        for (Lesson lesson : lessons) {
            if (lesson.getClass_id().equals(classId)) {
                classLessons.add(lesson);
            }
        }

        if (classLessons.isEmpty()) {
            System.out.println("Lớp học không có bài học nào.");
            return;
        }

        // Độ rộng của các cột
        int studentWidth = 20;
        int scoreWidth = 10;

        // Hiển thị điểm của học sinh theo từng bài học
        System.out.println("=== Điểm của học sinh theo bài học ===");
        for (Lesson lesson : classLessons) {
            System.out.println("Lesson " + lesson.getOrder() + ": " + lesson.getTitle());

            // Lấy danh sách học sinh trong lớp
            List<String> studentIds = classRoom.getStudent_id();
            if (studentIds == null || studentIds.isEmpty()) {
                System.out.println("  Lớp học không có học sinh nào.");
                continue;
            }

            // In ra danh sách điểm với định dạng bảng
            System.out.println("+" + "-".repeat(studentWidth + 2) + "+" + "-".repeat(scoreWidth + 2) + "+");
            System.out.printf("| %-" + studentWidth + "s | %-" + scoreWidth + "s |\n", "Học sinh", "Điểm");
            System.out.println("+" + "-".repeat(studentWidth + 2) + "+" + "-".repeat(scoreWidth + 2) + "+");

            // Hiển thị điểm của từng học sinh trong bài học này
            for (String studentId : studentIds) {
                // Tìm học sinh theo ID
                User student = findById(studentId, users, User::getId);
                if (student == null) {
                    System.out.printf("| %-" + studentWidth + "s | %-" + scoreWidth + "s |\n", "Không tìm thấy học sinh", "");
                    continue;
                }

                // Tìm điểm của học sinh trong bài học này
                Score studentScore = findScoreByStudentAndLesson(studentId, lesson.getId(), scores);
                String studentName = Utils.truncateString(student.getUsername(), studentWidth);
                String score;
                if (studentScore == null) {
                    score = "Chưa chấm";
                } else if (studentScore.getScore() == 99) {
                    score = "Nghỉ học";
                } else {
                    score = Utils.truncateString(String.valueOf(studentScore.getScore()), scoreWidth);
                }

                System.out.printf("| %-" + studentWidth + "s | %-" + scoreWidth + "s |\n", studentName, score);
            }
            System.out.println("+" + "-".repeat(studentWidth + 2) + "+" + "-".repeat(scoreWidth + 2) + "+");
            System.out.println("--------------------------------");
        }
    }

    public void printStudentScoresByUser(AppContext context, User student) {
        Scanner scanner = context.getScanner();
        List<Classes> classes = context.getList(Classes.class); // Lấy danh sách lớp học từ AppContext
        List<Score> scores = context.getList(Score.class); // Lấy danh sách điểm từ AppContext

        // Tạo danh sách các lớp mà sinh viên đang học
        List<Classes> studentClasses = new ArrayList<>();
        for (Classes classRoom : classes) {
            if (classRoom.getStudent_id() != null && classRoom.getStudent_id().contains(student.getId())) {
                studentClasses.add(classRoom);
            }
        }

        // Kiểm tra nếu sinh viên không tham gia lớp nào
        if (studentClasses.isEmpty()) {
            System.out.println("Sinh viên " + student.getUsername() + " không tham gia lớp học nào.");
            return;
        }

        // Độ rộng của các cột
        int indexWidth = 5;
        int idWidth = 10;
        int nameWidth = 30;

        // In ra danh sách các lớp mà sinh viên đang học
        System.out.println("=== Danh sách lớp học của sinh viên " + student.getUsername() + " ===");
        System.out.println("+" + "-".repeat(indexWidth + 2) + "+" + "-".repeat(idWidth + 2) + "+" + "-".repeat(nameWidth + 2) + "+");
        System.out.printf("| %-" + indexWidth + "s | %-" + idWidth + "s | %-" + nameWidth + "s |\n", "STT", "ID Lớp", "Tên Lớp");
        System.out.println("+" + "-".repeat(indexWidth + 2) + "+" + "-".repeat(idWidth + 2) + "+" + "-".repeat(nameWidth + 2) + "+");

        for (int i = 0; i < studentClasses.size(); i++) {
            Classes classRoom = studentClasses.get(i);
            String index = Utils.truncateString(String.valueOf(i + 1), indexWidth);
            String id = Utils.truncateString(classRoom.getId(), idWidth);
            String name = Utils.truncateString(classRoom.getName(), nameWidth);
            System.out.printf("| %-" + indexWidth + "s | %-" + idWidth + "s | %-" + nameWidth + "s |\n", index, id, name);
        }
        System.out.println("+" + "-".repeat(indexWidth + 2) + "+" + "-".repeat(idWidth + 2) + "+" + "-".repeat(nameWidth + 2) + "+");

        // Yêu cầu sinh viên chọn lớp
        int choice = utils.inputInt(scanner, "Chọn lớp học để xem điểm (nhập số thứ tự): ");

        // Kiểm tra nếu lựa chọn không hợp lệ
        if (choice < 1 || choice > studentClasses.size()) {
            System.out.println("Lựa chọn không hợp lệ.");
            return;
        }

        // Lấy lớp được chọn
        Classes selectedClass = studentClasses.get(choice - 1);

        // Hiển thị thông tin lớp học
        System.out.println("=== Thông tin lớp học ===");
        System.out.println("ID Lớp học: " + selectedClass.getId());
        System.out.println("Tên lớp học: " + selectedClass.getName());
        System.out.println("Giảng viên: " + selectedClass.getTeacher_id());
        System.out.println("Ngày bắt đầu: " + selectedClass.getStart_date());
        System.out.println("Ngày kết thúc: " + selectedClass.getEnd_date());

        // Lấy danh sách bài học trong lớp
        List<Lesson> lessons = context.getList(Lesson.class);
        List<Lesson> classLessons = new ArrayList<>();
        for (Lesson lesson : lessons) {
            if (lesson.getClass_id().equals(selectedClass.getId())) {
                classLessons.add(lesson);
            }
        }

        if (classLessons.isEmpty()) {
            System.out.println("Lớp học không có bài học nào.");
            return;
        }

        // Độ rộng của các cột
        int lessonWidth = 30;
        int scoreWidth = 10;

        // Hiển thị điểm của học sinh theo từng bài học
        System.out.println("=== Điểm của học sinh theo bài học ===");
        System.out.println("+" + "-".repeat(lessonWidth + 2) + "+" + "-".repeat(scoreWidth + 2) + "+");
        System.out.printf("| %-" + lessonWidth + "s | %-" + scoreWidth + "s |\n", "Bài học", "Điểm");
        System.out.println("+" + "-".repeat(lessonWidth + 2) + "+" + "-".repeat(scoreWidth + 2) + "+");

        for (Lesson lesson : classLessons) {
            String lessonTitle = Utils.truncateString(lesson.getTitle(), lessonWidth);

            // Tìm điểm của học sinh trong bài học này
            Score studentScore = findScoreByStudentAndLesson(student.getId(), lesson.getId(), scores);
            String score = (studentScore == null) ? "Chưa có điểm" : Utils.truncateString(String.valueOf(studentScore.getScore()), scoreWidth);

            System.out.printf("| %-" + lessonWidth + "s | %-" + scoreWidth + "s |\n", lessonTitle, score);
        }
        System.out.println("+" + "-".repeat(lessonWidth + 2) + "+" + "-".repeat(scoreWidth + 2) + "+");
    }

    public void displayLessonsWithStudentsAndScores(AppContext context, String classId) {
        // Lấy danh sách bài học, học sinh và điểm từ AppContext
        List<Lesson> lessons = context.getList(Lesson.class);
        List<User> users = context.getList(User.class);
        List<Score> scores = context.getList(Score.class);

        // Tìm lớp học theo classId
        Classes classRoom = findById(classId, context.getList(Classes.class), Classes::getId);
        if (classRoom == null) {
            System.out.println("Không tìm thấy lớp học với ID: " + classId);
            return;
        }

        // Lấy danh sách học sinh trong lớp từ trường student_id
        List<String> studentIds = classRoom.getStudent_id();
        if (studentIds == null || studentIds.isEmpty()) {
            System.out.println("Lớp học không có học sinh nào.");
            return;
        }

        // Lọc danh sách bài học thuộc lớp học được chọn
        List<Lesson> classLessons = new ArrayList<>();
        for (Lesson lesson : lessons) {
            if (lesson.getClass_id().equals(classId)) {
                classLessons.add(lesson);
            }
        }

        // Kiểm tra nếu lớp không có bài học nào
        if (classLessons.isEmpty()) {
            System.out.println("Lớp học không có bài học nào.");
            return;
        }

        // Độ rộng của các cột
        int lessonIdWidth = 10;
        int titleWidth = 30;
        int studentIdWidth = 10;
        int studentNameWidth = 20;
        int scoreWidth = 10;

        // Duyệt qua từng bài học và hiển thị thông tin
        for (Lesson lesson : classLessons) {
            System.out.println("\n=== Thông tin bài học: " + lesson.getTitle() + " ===");
            System.out.println("ID Bài học: " + lesson.getId());
            System.out.println("Tiêu đề: " + lesson.getTitle());

            // Hiển thị danh sách học sinh và điểm
            System.out.println("+" + "-".repeat(studentIdWidth + 2) + "+" + "-".repeat(studentNameWidth + 2) + "+" + "-".repeat(scoreWidth + 2) + "+");
            System.out.printf("| %-" + studentIdWidth + "s | %-" + studentNameWidth + "s | %-" + scoreWidth + "s |\n", "ID Học sinh", "Tên Học sinh", "Điểm");
            System.out.println("+" + "-".repeat(studentIdWidth + 2) + "+" + "-".repeat(studentNameWidth + 2) + "+" + "-".repeat(scoreWidth + 2) + "+");

            // Duyệt qua từng học sinh trong lớp
            for (String studentId : studentIds) {
                // Tìm học sinh theo ID
                User student = findById(studentId, users, User::getId);
                if (student == null) {
                    System.out.println("Không tìm thấy học sinh với ID: " + studentId);
                    continue;
                }

                // Tìm điểm của học sinh trong bài học này
                Score score = findScoreByStudentAndLesson(studentId, lesson.getId(), scores);
                String studentIdDisplay = Utils.truncateString(student.getId(), studentIdWidth);
                String studentName = Utils.truncateString(student.getUsername(), studentNameWidth);
                String scoreValue;

                if (score == null) {
                    scoreValue = "Chưa chấm"; // Học sinh chưa có điểm
                } else if (score.getScore() == 99) {
                    scoreValue = "Nghỉ học"; // Học sinh nghỉ học
                } else {
                    scoreValue = Utils.truncateString(String.valueOf(score.getScore()), scoreWidth); // Hiển thị điểm
                }

                // Hiển thị thông tin học sinh và điểm
                System.out.printf("| %-" + studentIdWidth + "s | %-" + studentNameWidth + "s | %-" + scoreWidth + "s |\n", studentIdDisplay, studentName, scoreValue);
            }
            System.out.println("+" + "-".repeat(studentIdWidth + 2) + "+" + "-".repeat(studentNameWidth + 2) + "+" + "-".repeat(scoreWidth + 2) + "+");
        }
    }

    private Score findScoreByStudentAndLesson(String studentId, String lessonId, List<Score> scores) {
        for (Score score : scores) {
            if (score.getStudent_id().equals(studentId) && score.getLesson_id().equals(lessonId)) {
                return score;
            }
        }
        return null;
    }

//

    public void printClassesAndLessonsForStudent(AppContext context, User student) {
        Scanner scanner = context.getScanner();
        List<Classes> classes = context.getList(Classes.class); // Lấy danh sách lớp học từ AppContext

        // Tạo danh sách các lớp mà sinh viên đang học
        List<Classes> studentClasses = new ArrayList<>();
        for (Classes classRoom : classes) {
            if (classRoom.getStudent_id() != null && classRoom.getStudent_id().contains(student.getId())) {
                studentClasses.add(classRoom);
            }
        }

        // Kiểm tra nếu sinh viên không tham gia lớp nào
        if (studentClasses.isEmpty()) {
            System.out.println("Sinh viên " + student.getUsername() + " không tham gia lớp học nào.");
            return;
        }

        // Độ rộng của các cột
        int indexWidth = 5;
        int idWidth = 10;
        int nameWidth = 30;

        // In ra danh sách các lớp mà sinh viên đang học
        System.out.println("=== Danh sách lớp học của sinh viên " + student.getUsername() + " ===");
        System.out.println("+" + "-".repeat(indexWidth + 2) + "+" + "-".repeat(idWidth + 2) + "+" + "-".repeat(nameWidth + 2) + "+");
        System.out.printf("| %-" + indexWidth + "s | %-" + idWidth + "s | %-" + nameWidth + "s |\n", "STT", "ID Lớp", "Tên Lớp");
        System.out.println("+" + "-".repeat(indexWidth + 2) + "+" + "-".repeat(idWidth + 2) + "+" + "-".repeat(nameWidth + 2) + "+");

        for (int i = 0; i < studentClasses.size(); i++) {
            Classes classRoom = studentClasses.get(i);
            String index = Utils.truncateString(String.valueOf(i + 1), indexWidth);
            String id = Utils.truncateString(classRoom.getId(), idWidth);
            String name = Utils.truncateString(classRoom.getName(), nameWidth);
            System.out.printf("| %-" + indexWidth + "s | %-" + idWidth + "s | %-" + nameWidth + "s |\n", index, id, name);
        }
        System.out.println("+" + "-".repeat(indexWidth + 2) + "+" + "-".repeat(idWidth + 2) + "+" + "-".repeat(nameWidth + 2) + "+");

        // Yêu cầu sinh viên chọn lớp
        int choice = utils.inputInt(scanner, "Chọn lớp học (nhập số thứ tự): ");

        // Kiểm tra nếu lựa chọn không hợp lệ
        if (choice < 1 || choice > studentClasses.size()) {
            System.out.println("Lựa chọn không hợp lệ.");
            return;
        }

        // Lấy lớp được chọn
        Classes selectedClass = studentClasses.get(choice - 1);

        // In ra danh sách bài giảng của lớp được chọn
        printLessonsForClass(context, selectedClass.getId());
    }

    public void printClassesAndLessonsForTeacher(AppContext context, User teacher) {
        Scanner scanner = context.getScanner();
        List<Classes> classes = context.getList(Classes.class); // Lấy danh sách lớp học từ AppContext

        // Tạo danh sách các lớp mà giáo viên đang dạy
        List<Classes> teacherClasses = new ArrayList<>();
        for (Classes classRoom : classes) {
            if (classRoom.getTeacher_id().equals(teacher.getId())) {
                teacherClasses.add(classRoom);
            }
        }

        // Kiểm tra nếu giáo viên không dạy lớp nào
        if (teacherClasses.isEmpty()) {
            System.out.println("Giáo viên " + teacher.getUsername() + " không dạy lớp học nào.");
            return;
        }

        // Độ rộng của các cột
        int indexWidth = 5;
        int idWidth = 10;
        int nameWidth = 30;

        // In ra danh sách các lớp mà giáo viên đang dạy
        System.out.println("=== Danh sách lớp học của giáo viên " + teacher.getUsername() + " ===");
        System.out.println("+" + "-".repeat(indexWidth + 2) + "+" + "-".repeat(idWidth + 2) + "+" + "-".repeat(nameWidth + 2) + "+");
        System.out.printf("| %-" + indexWidth + "s | %-" + idWidth + "s | %-" + nameWidth + "s |\n", "STT", "ID Lớp", "Tên Lớp");
        System.out.println("+" + "-".repeat(indexWidth + 2) + "+" + "-".repeat(idWidth + 2) + "+" + "-".repeat(nameWidth + 2) + "+");

        for (int i = 0; i < teacherClasses.size(); i++) {
            Classes classRoom = teacherClasses.get(i);
            String index = Utils.truncateString(String.valueOf(i + 1), indexWidth);
            String id = Utils.truncateString(classRoom.getId(), idWidth);
            String name = Utils.truncateString(classRoom.getName(), nameWidth);
            System.out.printf("| %-" + indexWidth + "s | %-" + idWidth + "s | %-" + nameWidth + "s |\n", index, id, name);
        }
        System.out.println("+" + "-".repeat(indexWidth + 2) + "+" + "-".repeat(idWidth + 2) + "+" + "-".repeat(nameWidth + 2) + "+");

        // Yêu cầu giáo viên chọn lớp
        int choice = utils.inputInt(scanner, "Chọn lớp học (nhập số thứ tự): ");

        // Kiểm tra nếu lựa chọn không hợp lệ
        if (choice < 1 || choice > teacherClasses.size()) {
            System.out.println("Lựa chọn không hợp lệ.");
            return;
        }

        // Lấy lớp được chọn
        Classes selectedClass = teacherClasses.get(choice - 1);

        // In ra danh sách bài giảng của lớp được chọn
        printLessonsForClass(context, selectedClass.getId());
    }

    public void printLessonsForClass(AppContext context, String classId) {
        List<Lesson> lessons = context.getList(Lesson.class); // Lấy danh sách bài học từ AppContext

        // Tạo danh sách bài học trong lớp
        List<Lesson> classLessons = new ArrayList<>();
        for (Lesson lesson : lessons) {
            if (lesson.getClass_id().equals(classId)) {
                classLessons.add(lesson);
            }
        }

        // Kiểm tra nếu lớp không có bài học nào
        if (classLessons.isEmpty()) {
            System.out.println("Lớp học không có bài học nào.");
            return;
        }

        // Độ rộng của các cột
        int idWidth = 10;
        int titleWidth = 30;
        int contentWidth = 20;
        int orderWidth = 6;

        // In ra danh sách bài học với định dạng bảng
        System.out.println("=== Danh sách bài giảng của lớp " + classId + " ===");
        System.out.println("+" + "-".repeat(idWidth + 2) + "+" + "-".repeat(titleWidth + 2) + "+" + "-".repeat(contentWidth + 2) + "+" + "-".repeat(orderWidth + 2) + "+");
        System.out.printf("| %-" + idWidth + "s | %-" + titleWidth + "s | %-" + contentWidth + "s | %-" + orderWidth + "s |\n", "ID", "Tiêu đề", "Nội dung", "Thứ tự");
        System.out.println("+" + "-".repeat(idWidth + 2) + "+" + "-".repeat(titleWidth + 2) + "+" + "-".repeat(contentWidth + 2) + "+" + "-".repeat(orderWidth + 2) + "+");

        for (Lesson lesson : classLessons) {
            // Sử dụng phương thức từ lớp Utils
            String id = Utils.truncateString(lesson.getId(), idWidth);
            String title = Utils.wrapString(lesson.getTitle(), titleWidth);
            String content = Utils.wrapString(lesson.getContent(), contentWidth);
            String order = Utils.truncateString(String.valueOf(lesson.getOrder()), orderWidth);

            // In từng dòng dữ liệu
            String[] titleLines = title.split("\n");
            String[] contentLines = content.split("\n");
            int maxLines = Math.max(titleLines.length, contentLines.length);

            for (int i = 0; i < maxLines; i++) {
                String titleLine = (i < titleLines.length) ? titleLines[i] : "";
                String contentLine = (i < contentLines.length) ? contentLines[i] : "";
                if (i == 0) {
                    System.out.printf("| %-" + idWidth + "s | %-" + titleWidth + "s | %-" + contentWidth + "s | %-" + orderWidth + "s |\n", id, titleLine, contentLine, order);
                } else {
                    System.out.printf("| %-" + idWidth + "s | %-" + titleWidth + "s | %-" + contentWidth + "s | %-" + orderWidth + "s |\n", "", titleLine, contentLine, "");
                }
            }
            System.out.println("+" + "-".repeat(idWidth + 2) + "+" + "-".repeat(titleWidth + 2) + "+" + "-".repeat(contentWidth + 2) + "+" + "-".repeat(orderWidth + 2) + "+");
        }
    }


                // Utility method
    public <T> T findById(String id, List<T> list, Function<T, String> idExtractor) {
        for (T item : list) {
            if (idExtractor.apply(item).equalsIgnoreCase(id)) {
                return item;
            }
        }
        return null;
    }
}