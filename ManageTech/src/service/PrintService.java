package service;

import entities.*;
import enums.Role;
import exist.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class PrintService {
    private Utils utils = new Utils();

    // User
    public void printInfo(AppContext appContext, Role role) {
        List<User> users = appContext.getList(User.class); // Lấy danh sách users từ AppContext
        System.out.println("=== Thông tin người dùng có vai trò " + role + " ===");
        boolean found = false;

        for (User user : users) {
            if (user != null && user.getRole().equals(role)) {
                if (role == Role.TEACHER) {
                    printTeacherInfo((Teacher) user); // Ép kiểu sang Teacher
                } else if (role == Role.STUDENT) {
                    printStudentInfo((Student) user); // Ép kiểu sang Student
                } else {
                    printUserInfo(user); // Hiển thị thông tin chung
                }
                found = true;
            }
        }

        if (!found) {
            System.out.println("Không có người dùng nào có vai trò " + role);
        }
    }

    private void printUserInfo(User user) {
        System.out.println("--------------------------------");
        System.out.println("ID: " + user.getId());
        System.out.println("Tên người dùng: " + user.getUsername());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Budget: " + user.getBudget());
        System.out.println("created_at: " + user.getCreated_at());
        System.out.println("Role: " + user.getRole());
    }

    private void printTeacherInfo(Teacher teacher) {
        printUserInfo(teacher); // Hiển thị thông tin chung
        System.out.println("Lương: " + teacher.getSalary());
        System.out.println("Kinh nghiệm: " + teacher.getExperience());
        System.out.println("Chuyên môn: " + teacher.getMajors());
    }

    private void printStudentInfo(Student student) {
        printUserInfo(student); // Hiển thị thông tin chung
        System.out.println("Trình độ học vấn: " + student.getEducation_level());
    }




    private void printUser(User user) {
        System.out.println("--------------------------------");
        System.out.println("ID: " + user.getId());
        System.out.println("Tên người dùng: " + user.getUsername());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Budget: " + user.getBudget());
        System.out.println("created_at: " + user.getCreated_at());
        System.out.println("Role: " + user.getRole());
    }

    public void printUserDetails(String userId, AppContext context) {
        List<User> users = context.getList(User.class); // Lấy danh sách users từ AppContext

        User user = findById(userId, users, User::getId);
        if (user == null) {
            System.out.println("Không tìm thấy người dùng với ID: " + userId);
            return;
        }

        System.out.println("=== Chi tiết Người dùng ===");
        if (user.getRole() == Role.TEACHER) {
            printTeacherInfo((Teacher) user); // Ép kiểu sang Teacher
        } else if (user.getRole() == Role.STUDENT) {
            printStudentInfo((Student) user); // Ép kiểu sang Student
        } else {
            printUserInfo(user); // Hiển thị thông tin chung
        }
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
            printClassInfo(classItem);
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
                printLessonInfo(lesson);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Không có bài học nào thuộc lớp " + classId + ".");
        }
    }

    private void printLessonInfo(Lesson lesson) {
        System.out.println("ID: " + lesson.getId());
        System.out.println("Tiêu đề: " + lesson.getTitle());
        System.out.println("Nội dung: " + lesson.getContent());
        System.out.println("Thứ tự: " + lesson.getOrder());
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
                printClassInfo(classRoom);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Không có lớp học nào thuộc khóa học ID: " + courseId);
        }
    }

    private void printClassInfo(Classes classRoom) {
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
    }

    public void printAllClassRoomsByUser(String userId, AppContext context) {
        List<User> users = context.getList(User.class); // Lấy danh sách users từ AppContext
        List<Classes> classes = context.getList(Classes.class); // Lấy danh sách classes từ AppContext

        User user = findById(userId, users, User::getId);
        if (user == null) {
            System.out.println("Người dùng không tồn tại.");
            return;
        }

        System.out.println("=== Danh sách Lớp học của người dùng ===");
        System.out.println("Thông tin người dùng:");
        printUserInfo(user);

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
            printCourseInfo(course);
        }
    }

    private void printCourseInfo(Course course) {
        System.out.println("=== Chi tiết khóa học ===");
        System.out.println("ID: " + course.getId());
        System.out.println("Tên khóa học: " + course.getName());
        System.out.println("Mô tả: " + course.getDescription());
        System.out.println("Giá: " + course.getPrice());
    }

    public void printCourseById(String courseId, AppContext context) {
        List<Course> courses = context.getList(Course.class); // Lấy danh sách courses từ AppContext

        Course course = findById(courseId, courses, Course::getId);
        if (course == null) {
            System.out.println("Không tìm thấy khóa học với ID: " + courseId);
        } else {
            printCourseInfo(course);
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

    // Blog
    public void printAllBlogs(AppContext context) {
        List<Blog> blogs = context.getList(Blog.class); // Lấy danh sách blogs từ AppContext

        if (blogs.isEmpty()) {
            System.out.println("Hiện tại không có blog nào.");
            return;
        }

        System.out.println("Danh sách các blog:");
        System.out.println("--------------------------------------------------");
        for (Blog blog : blogs) {
            printBlogInfo(blog);
        }
    }

    private void printBlogInfo(Blog blog) {
        System.out.println("ID Blog: " + blog.getId());
        System.out.println("Title: " + blog.getTitle());
        System.out.println("Content: " + blog.getContent());
        System.out.println("Tác giả (ID): " + blog.getUser_Id());
        System.out.println("Trạng thái: " + (blog.isStatus() ? "Đăng" : "Hủy Đăng"));
        System.out.println("--------------------------------------------------");
    }

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

            // Hiển thị điểm của từng học sinh trong bài học này
            for (String studentId : studentIds) {
                // Tìm học sinh theo ID
                User student = findById(studentId, users, User::getId);
                if (student == null) {
                    System.out.println("  Không tìm thấy học sinh với ID: " + studentId);
                    continue;
                }

                // Tìm điểm của học sinh trong bài học này
                Score studentScore = findScoreByStudentAndLesson(studentId, lesson.getId(), scores);
                if (studentScore == null) {
                    System.out.println("  - " + student.getUsername() + ": Chưa có điểm");
                } else {
                    System.out.println("  - " + student.getUsername() + ": " + studentScore.getScore());
                }
            }
            System.out.println("--------------------------------");
        }
    }

    public void printStudentScoresByUser(AppContext context, User user) {
        Scanner scanner = context.getScanner();
        List<Classes> classes = context.getList(Classes.class); // Lấy danh sách lớp học
        List<Lesson> lessons = context.getList(Lesson.class); // Lấy danh sách bài học
        List<Score> scores = context.getList(Score.class); // Lấy danh sách điểm

        // Nhập ID lớp học
        System.out.print("Nhập ID lớp học: ");
        String classId = scanner.nextLine();

        // Tìm lớp học theo ID
        Classes classRoom = findById(classId, classes, Classes::getId);
        if (classRoom == null) {
            System.out.println("Không tìm thấy lớp học với ID: " + classId);
            return;
        }

        // Kiểm tra xem học viên có thuộc lớp học này không
        if (!classRoom.getStudent_id().contains(user.getId())) {
            System.out.println("Bạn không thuộc lớp học này.");
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

        // Hiển thị điểm của học sinh theo từng bài học
        System.out.println("=== Điểm của học sinh theo bài học ===");
        for (Lesson lesson : classLessons) {
            System.out.println("Lesson " + lesson.getOrder() + ": " + lesson.getTitle());

            // Tìm điểm của học sinh trong bài học này
            Score studentScore = findScoreByStudentAndLesson(user.getId(), lesson.getId(), scores);
            if (studentScore == null) {
                System.out.println("  - " + user.getUsername() + ": Chưa có điểm");
            } else {
                System.out.println("  - " + user.getUsername() + ": " + studentScore.getScore());
            }
            System.out.println("--------------------------------");
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