package service;

import entities.*;
import enums.Role;
import exist.Exist;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrintService {
    Exist exist = new Exist();
    UserService userService = new UserService();
    CourseService courseService = new CourseService();
    LessonService lessonService = new LessonService();
    ClassService classService = new ClassService();

    // User
    public void printInfo(AppContext appContext, Role role) {
        List<User> users = appContext.getList(User.class); // Lấy danh sách users từ AppContext
        System.out.println("=== Thông tin người dùng có vai trò " + role + " ===");
        boolean found = false;

        for (User user : users) {
            if (user != null && user.getRole().equals(role)) {
                System.out.println("--------------------------------");
                System.out.println("ID: " + user.getId());
                System.out.println("Tên người dùng: " + user.getUsername());
                System.out.println("Email: " + user.getEmail());
                System.out.println("Budget: " + user.getBudget());
                System.out.println("created_at: " + user.getCreated_at());
                System.out.println("Role: " + user.getRole());
                found = true;
            }
        }

        if (!found) {
            System.out.println("Không có người dùng nào có vai trò " + role);
        }
    }

    public void printUserDetails(String userId, AppContext context) {
        List<User> users = context.getList(User.class); // Lấy danh sách users từ AppContext

        User user = userService.findById(userId, users);

        if (user == null) {
            System.out.println("Không tìm thấy người dùng với ID: " + userId);
            return;
        }

        System.out.println("=== Chi tiết Người dùng ===");
        System.out.println("ID: " + user.getId());
        System.out.println("Tên người dùng: " + user.getUsername());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Budget: " + user.getBudget());
        System.out.println("Vai trò: " + user.getRole());
    }

    public void printTeachingSchedule(AppContext appContext) {
        Scanner scanner = appContext.getScanner();
        List<Classes> classes = appContext.getList(Classes.class); // Lấy danh sách classes từ AppContext

        System.out.print("Nhập ID giảng viên để xem lịch dạy: ");
        String teacherId = scanner.nextLine();

        List<Classes> teachingClasses = new ArrayList<>();
        for (Classes classItem : classes) {
            if (classItem.getTeacher_id().equals(teacherId)) {
                teachingClasses.add(classItem);
            }
        }

        if (teachingClasses.isEmpty()) {
            System.out.println("Giảng viên với ID " + teacherId + " không có lớp học nào.");
            return;
        }

        System.out.println("Lịch dạy của giảng viên " + teacherId + ":");
        for (Classes classItem : teachingClasses) {
            System.out.println("Lớp: " + classItem.getName());
            System.out.println("Mã khóa học: " + classItem.getCourse_id());
            System.out.println("Ngày bắt đầu: " + classItem.getStart_date());
            System.out.println("Ngày kết thúc: " + classItem.getEnd_date());
            String schedule = classItem.getSchedule();
            if ("1".equals(schedule)) {
                System.out.println("Lịch học: 1 (Thứ 2, 4, 6)");
            } else if ("2".equals(schedule)) {
                System.out.println("Lịch học: 2 (Thứ 3, 5, 7)");
            } else {
                System.out.println("Lịch học không xác định.");
            }
            System.out.println("------------------------------------------------");
        }
    }

    // Lesson
    public void printLessonByClass(AppContext appContext) {
        Scanner scanner = appContext.getScanner();
        List<Lesson> lessons = appContext.getList(Lesson.class); // Lấy danh sách lessons từ AppContext

        System.out.print("Nhập ID lớp học để hiển thị bài học: ");
        String classId = scanner.nextLine();

        System.out.println("=== Danh sách bài học của lớp " + classId + " ===");
        boolean found = false;
        for (Lesson lesson : lessons) {
            if (lesson.getClass_id().equals(classId)) {
                System.out.println("ID: " + lesson.getId());
                System.out.println("Tiêu đề: " + lesson.getTitle());
                System.out.println("Nội dung: " + lesson.getContent());
                System.out.println("Thứ tự: " + lesson.getOrder());
                found = true;
            }
        }
        if (!found) {
            System.out.println("Không có bài học nào thuộc lớp " + classId + ".");
        }
    }

    // Class
    public void printInfoClass(AppContext context) {
        List<Classes> classes = context.getList(Classes.class); // Lấy danh sách classes từ AppContext
        Scanner scanner = context.getScanner();

        System.out.println("Nhập khóa học ID:");
        String courseId = scanner.nextLine();

        System.out.println("=== Danh sách lớp học theo khóa học ID: " + courseId + " ===");
        boolean found = false;

        for (Classes classRoom : classes) {
            if (classRoom != null && classRoom.getCourse_id().equals(courseId)) {
                System.out.println("--------------------------------");
                System.out.println("ID Lớp học: " + classRoom.getId());
                System.out.println("ID KHÓA học: " + classRoom.getCourse_id());
                System.out.println("Tên lớp học: " + classRoom.getName());
                System.out.println("Giảng viên: " + classRoom.getTeacher_id());
                System.out.println("Ngày bắt đầu: " + classRoom.getStart_date());
                System.out.println("Ngày kết thúc: " + classRoom.getEnd_date());

                System.out.println("Danh sách học viên:");
                if (classRoom.getStudent_id() != null && !classRoom.getStudent_id().isEmpty()) {
                    classRoom.getStudent_id().forEach(student -> System.out.println("  - " + student));
                } else {
                    System.out.println("  Chưa có học viên nào.");
                }
                found = true;
            }
        }

        if (!found) {
            System.out.println("Không có lớp học nào thuộc khóa học ID: " + courseId);
        }
    }

    public void printAllClassRoomsByUser(String userId, AppContext context) {
        List<User> users = context.getList(User.class); // Lấy danh sách users từ AppContext
        List<Classes> classes = context.getList(Classes.class); // Lấy danh sách classes từ AppContext

        User user = userService.findById(userId, users);

        System.out.println("=== Danh sách Lớp học của người dùng ===");
        System.out.println("Thông tin người dùng:");
        System.out.println("ID: " + user.getId());
        System.out.println("Tên: " + user.getUsername());
        System.out.println("Vai trò: " + user.getRole());

        boolean hasClass = false;

        if (user.getRole() == Role.TEACHER) {
            System.out.println("\nLớp học giảng dạy:");
            for (Classes classRoom : classes) {
                if (classRoom.getTeacher_id().equals(userId)) {
                    printClassRoomSummary(classRoom);
                    hasClass = true;
                }
            }
        }
        if (user.getRole() == Role.STUDENT) {
            System.out.println("\nLớp học tham gia:");
            for (Classes classRoom : classes) {
                if (classRoom.getStudent_id() != null && classRoom.getStudent_id().contains(userId)) {
                    printClassRoomSummary(classRoom);
                    hasClass = true;
                }
            }
        }

        if (!hasClass) {
            System.out.println("Bạn không tham gia hoặc giảng dạy bất kỳ lớp học nào.");
        }
    }

    private void printClassRoomSummary(Classes classRoom) {
        System.out.println("--------------------------------");
        System.out.println("ID Lớp học: " + classRoom.getId());
        System.out.println("Tên lớp học: " + classRoom.getName());
        System.out.println("Giảng viên: " + classRoom.getTeacher_id());
        System.out.println("Số học viên: " + (classRoom.getStudent_id() != null ? classRoom.getStudent_id().size() : 0));
        System.out.println("--------------------------------");
    }

    // Course
    public void printAllCourse(AppContext context) {
        List<Course> courses = context.getList(Course.class); // Lấy danh sách courses từ AppContext
        for (Course course : courses) {
            System.out.println("=== Chi tiết khóa học ===");
            System.out.println("ID: " + course.getId());
            System.out.println("Tên khóa học: " + course.getName());
            System.out.println("Mô tả: " + course.getDescription());
            System.out.println("Giá: " + course.getPrice());
        }
    }

    public void printCourseById(String courseId, AppContext context) {
        List<Course> courses = context.getList(Course.class); // Lấy danh sách courses từ AppContext

        Course course = courseService.findById(courseId, (ArrayList<Course>) courses);
        if (course == null) {
            System.out.println("Không tìm thấy khóa học với ID: " + courseId);
        } else {
            System.out.println("=== Chi tiết khóa học ===");
            System.out.println("ID: " + course.getId());
            System.out.println("Tên khóa học: " + course.getName());
            System.out.println("Mô tả: " + course.getDescription());
            System.out.println("Giá: " + course.getPrice());
        }
    }

    public void printCourseByUserId(String user_id, AppContext context, int status) {
        List<Enrollments> enrollments = context.getList(Enrollments.class); // Lấy danh sách enrollments từ AppContext

        for (Enrollments enrollment : enrollments) {
            if (enrollment.getUser_id().equals(user_id) && enrollment.getStatus() == status) {
                printCourseById(enrollment.getCourse_id(), context);
            }
        }
    }

    public void printAllBlogs(AppContext context) {
        List<Blog> blogs = context.getList(Blog.class); // Lấy danh sách blogs từ AppContext

        if (blogs.isEmpty()) {
            System.out.println("Hiện tại không có blog nào.");
            return;
        }

        System.out.println("Danh sách các blog:");
        System.out.println("--------------------------------------------------");
        for (Blog blog : blogs) {
            System.out.println("ID Blog: " + blog.getId());
            System.out.println("Title: " + blog.getTitle());
            System.out.println("Content: " + blog.getContent());
            System.out.println("Tác giả (ID): " + blog.getUser_Id());
            System.out.println("Trạng thái: " + (blog.isStatus() ? "Đăng" : "Hủy Đăng"));
            System.out.println("--------------------------------------------------");
        }
    }
}