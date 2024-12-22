package service;

import entities.*;
import enums.Role;

import java.util.ArrayList;
import java.util.Scanner;

public class PrintService {
    UserService userService = new UserService();
    CoursesService coursesService = new CoursesService();
    ClassRoomService classRoomService = new ClassRoomService();
    public void printInfo(ArrayList<User> users, Role role){
        System.out.println("=== Thông tin người dùng có vai trò " + role + " ===");
        boolean found = false;

        for (User user : users) {
            if (user != null && user.getRole().equals(role)) {
                System.out.println("--------------------------------");
                System.out.println("ID: " + user.getId());
                System.out.println("Tên người dùng: " + user.getUsername());
                System.out.println("Email: " + user.getEmail());
                System.out.println("Số điện thoại: " + user.getPhone());
                System.out.println("Role: " + user.getRole());
                found = true;
            }
        }

        if (!found) {
            System.out.println("Không có người dùng nào có vai trò " + role);
        }
    }

    public void printUserDetails(String userId, ArrayList<User> users) {
        User user = userService.findById(userId,users);

        if (user == null) {
            System.out.println("Không tìm thấy người dùng với ID: " + userId);
            return;
        }

        System.out.println("=== Chi tiết Người dùng ===");
        System.out.println("ID: " + user.getId());
        System.out.println("Tên người dùng: " + user.getUsername());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Số điện thoại: " + user.getPhone());
        System.out.println("Vai trò: " + user.getRole());
    }



    public void printInfoClass(ArrayList<ClassRoom> classRooms, Role role){
        System.out.println("=== Danh sách lớp học ===");
        boolean found = false;

        for (ClassRoom classRoom : classRooms) {
            if (classRoom != null) {
                System.out.println("--------------------------------");
                System.out.println("ID Lớp học: " + classRoom.getId());
                System.out.println("Tên lớp học: " + classRoom.getName());
                System.out.println("Giảng viên: " + classRoom.getTeacherId());

                System.out.println("Danh sách học viên:");
                if (classRoom.getStudentId() != null && !classRoom.getStudentId().isEmpty()) {
                    classRoom.getStudentId().forEach(student -> System.out.println("  - " + student));
                } else {
                    System.out.println("  Chưa có học viên nào.");
                }

                System.out.println("Danh sách môn học:");
                if (classRoom.getLessonId() != null && !classRoom.getLessonId().isEmpty()) {
                    classRoom.getLessonId().forEach(lesson -> System.out.println("  - " + lesson));
                } else {
                    System.out.println("  Chưa có môn học nào.");
                }

                found = true;
            }
        }

        if (!found) {
            System.out.println("Không có lớp học nào trong danh sách.");
        }
    }

    public void printClassRoomDetails(String classRoomId, ArrayList<ClassRoom> classRooms) {
        ClassRoom classRoom = classRoomService.findById(classRoomId,classRooms);

        if (classRoom == null) {
            System.out.println("Không tìm thấy lớp học với ID: " + classRoomId);
            return;
        }

        System.out.println("=== Chi tiết Lớp học ===");
        System.out.println("ID Lớp học: " + classRoom.getId());
        System.out.println("Tên lớp học: " + classRoom.getName());
        System.out.println("Giảng viên: " + classRoom.getTeacherId());
        System.out.println("Danh sách học viên:");
        if (classRoom.getStudentId() != null && !classRoom.getStudentId().isEmpty()) {
            classRoom.getStudentId().forEach(student -> System.out.println("  - " + student));
        } else {
            System.out.println("  Chưa có học viên nào.");
        }

        System.out.println("Danh sách môn học:");
        if (classRoom.getLessonId() != null && !classRoom.getLessonId().isEmpty()) {
            classRoom.getLessonId().forEach(lesson -> System.out.println("  - " + lesson));
        } else {
            System.out.println("  Chưa có môn học nào.");
        }
    }

    public void printAllClassRoomsByUser(String userId, ArrayList<ClassRoom> classRooms, ArrayList<User> users) {
        User user = userService.findById(userId,users);

        System.out.println("=== Danh sách Lớp học của người dùng ===");
        System.out.println("Thông tin người dùng:");
        System.out.println("ID: " + user.getId());
        System.out.println("Tên: " + user.getUsername());
        System.out.println("Vai trò: " + user.getRole());

        boolean hasClass = false;

        // Nếu người dùng là giáo viên, tìm các lớp mà họ là giảng viên
        if (user.getRole() == Role.TEACHER) {
            System.out.println("\nLớp học giảng dạy:");
            for (ClassRoom classRoom : classRooms) {
                if (classRoom.getTeacherId().equals(userId)) {
                    printClassRoomSummary(classRoom);
                    hasClass = true;
                }
            }
        }

        // Nếu người dùng là học viên, tìm các lớp mà họ là học viên
        if (user.getRole() == Role.STUDENT) {
            System.out.println("\nLớp học tham gia:");
            for (ClassRoom classRoom : classRooms) {
                if (classRoom.getStudentId() != null && classRoom.getStudentId().contains(userId)) {
                    printClassRoomSummary(classRoom);
                    hasClass = true;
                }
            }
        }

        if (!hasClass) {
            System.out.println("Ban không tham gia hoặc giảng dạy bất kỳ lớp học nào.");
        }
    }

    private void printClassRoomSummary(ClassRoom classRoom) {
        System.out.println("--------------------------------");
        System.out.println("ID Lớp học: " + classRoom.getId());
        System.out.println("Tên lớp học: " + classRoom.getName());
        System.out.println("Giảng viên: " + classRoom.getTeacherId());
        System.out.println("Số học viên: " + (classRoom.getStudentId() != null ? classRoom.getStudentId().size() : 0));
        System.out.println("Số môn học: " + (classRoom.getLessonId() != null ? classRoom.getLessonId().size() : 0));
        System.out.println("--------------------------------");
    }



    public void printBlogAll(ArrayList<Blog> blogs){
        System.out.println("=== Danh sách Blog ===");
        boolean found = false;

        for (Blog blog : blogs) {
            if (blog != null) {
                System.out.println("--------------------------------");
                System.out.println("Blog ID: " + blog.getId());
                System.out.println("Tiêu đề: " + blog.getTitle());
                System.out.println("Nội dung: " + blog.getContent());
                System.out.println("Người tạo: " + blog.getUser_Id());
                System.out.println("Thời gian đăng: " + blog.getPublished_at());
                System.out.println("Trạng thái: " + (blog.isStatus() ? "Đăng" : "Chưa đăng"));
                found = true;
            }
        }

        if (!found) {
            System.out.println("Không có Blog nào để hiển thị.");
        }
    }
    public void printBlogDetails(String blogId, ArrayList<Blog> blogs) {
        Blog blog = blogs.stream()
                .filter(b -> b.getId().equals(blogId))
                .findFirst()
                .orElse(null);

        if (blog == null) {
            System.out.println("Không tìm thấy Blog với ID: " + blogId);
            return;
        }

        System.out.println("=== Chi tiết Blog ===");
        System.out.println("ID: " + blog.getId());
        System.out.println("Tiêu đề: " + blog.getTitle());
        System.out.println("Nội dung: " + blog.getContent());
        System.out.println("Người tạo: " + blog.getUser_Id());
        System.out.println("Thời gian đăng: " + blog.getPublished_at());
        System.out.println("Trạng thái: " + (blog.isStatus() ? "Đăng" : "Chưa đăng"));
    }

    public void printLessonByClass(Scanner scanner, ArrayList<Lesson> lessons) {
        System.out.print("Nhập ID lớp học để hiển thị bài học: ");
        String classId = scanner.nextLine();

        System.out.println("=== Danh sách bài học của lớp " + classId + " ===");
        boolean found = false;

        for (Lesson lesson : lessons) {
            if (lesson.getClass_id().equals(classId)) {
                System.out.println("ID: " + lesson.getId());
                System.out.println("Tiêu đề: " + lesson.getTitle());
                System.out.println("Thứ tự: " + lesson.getOrder());
                found = true;
            }
        }

        if (!found) {
            System.out.println("Không có bài học nào thuộc lớp " + classId + ".");
        }
    }

    public void printLessonDetails(String lessonId, ArrayList<Lesson> lessons) {
        Lesson lesson = lessons.stream()
                .filter(l -> l.getId().equals(lessonId))
                .findFirst()
                .orElse(null);

        if (lesson == null) {
            System.out.println("Không tìm thấy bài học với ID: " + lessonId);
            return;
        }

        System.out.println("=== Chi tiết Bài học ===");
        System.out.println("ID: " + lesson.getId());
        System.out.println("Tiêu đề: " + lesson.getTitle());
        System.out.println("Nội dung: " + lesson.getContent());
        System.out.println("Thứ tự: " + lesson.getOrder());
        System.out.println("Lớp: " + lesson.getClass_id());
    }



    public void printAllCourses(ArrayList<Courses> courses) {
        if (courses.isEmpty()) {
            System.out.println("Không có khóa học nào để hiển thị.");
            return;
        }

        System.out.println("=== Danh sách khóa học ===");
        for (Courses course : courses) {
            System.out.println("--------------------------------");
            System.out.println("ID: " + course.getId());
            System.out.println("Tên khóa học: " + course.getTitle());
            System.out.println("Mô tả: " + course.getDescription());
            System.out.println("Giá: " + course.getPrice());
            System.out.println("Người tạo: " + course.getUser_id());
            System.out.println("Ngày bắt đầu: " + course.getStart_date());
            System.out.println("Ngày kết thúc: " + course.getEnd_date());
        }
        System.out.println("--------------------------------");
    }

    public void printStudentScoreDetails(String studentId, String lessonId, ArrayList<Scores> scoresList) {
        Scores score = scoresList.stream()
                .filter(s -> s.getStudent_id().equals(studentId) && s.getLesson_id().equals(lessonId))
                .findFirst()
                .orElse(null);

        if (score == null) {
            System.out.println("Không tìm thấy điểm cho học sinh ID: " + studentId + " trong bài học ID: " + lessonId);
            return;
        }

        System.out.println("=== Chi tiết Điểm ===");
        System.out.println("Học sinh ID: " + score.getStudent_id());
        System.out.println("Bài học ID: " + score.getLesson_id());
        System.out.println("Lớp học ID: " + score.getClass_id());
        System.out.println("Điểm: " + score.getScore());
    }


    public void printCourseById(String courseId, ArrayList<Courses> courses) {
        Courses course = coursesService.findById(courseId, courses);
        if (course == null) {
            System.out.println("Không tìm thấy khóa học với ID: " + courseId);
        } else {
            System.out.println("=== Chi tiết khóa học ===");
            System.out.println("ID: " + course.getId());
            System.out.println("Tên khóa học: " + course.getTitle());
            System.out.println("Mô tả: " + course.getDescription());
            System.out.println("Giá: " + course.getPrice());
            System.out.println("Người tạo: " + course.getUser_id());
            System.out.println("Ngày bắt đầu: " + course.getStart_date());
            System.out.println("Ngày kết thúc: " + course.getEnd_date());
        }
    }



}
