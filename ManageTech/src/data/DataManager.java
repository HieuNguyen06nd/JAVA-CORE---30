package data;

import com.fasterxml.jackson.core.type.TypeReference;
import entities.*;
import enums.Constain;
import enums.Role;
import service.AppContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DataManager {
    private static AppContext appContext = AppContext.getInstance();

    /**
     * Khởi tạo dữ liệu từ các tệp JSON.
     *
     * @param appContext Đối tượng AppContext để lưu trữ dữ liệu.
     * @throws IOException Nếu có lỗi khi đọc tệp.
     */
    public static void initializeData(AppContext appContext) throws IOException {
        List<User> users = new ArrayList<>();

        // Đọc danh sách giáo viên từ tệp teachers.json
        if (JsonHandler.isFileReadable(Constain.TEACHERS_FILE_PATH)) {
            List<Teacher> teachers = JsonHandler.readFromFile(Constain.TEACHERS_FILE_PATH, new TypeReference<>() {});
            if (teachers != null) {
                teachers.removeIf(teacher -> teacher.getUsername() == null || teacher.getUsername().isEmpty());
                users.addAll(teachers);
            }
        } else {
            System.out.println("File " + Constain.TEACHERS_FILE_PATH + " không tồn tại hoặc không thể đọc. Tạo tệp mới.");
            JsonHandler.writeToFile(Constain.TEACHERS_FILE_PATH, new ArrayList<Teacher>(), true);
        }

        // Đọc danh sách học sinh từ tệp students.json
        if (JsonHandler.isFileReadable(Constain.STUDENTS_FILE_PATH)) {
            List<Student> students = JsonHandler.readFromFile(Constain.STUDENTS_FILE_PATH, new TypeReference<>() {});
            if (students != null) {
                students.removeIf(student -> student.getUsername() == null || student.getUsername().isEmpty());
                users.addAll(students);
            }
        } else {
            System.out.println("File " + Constain.STUDENTS_FILE_PATH + " không tồn tại hoặc không thể đọc. Tạo tệp mới.");
            JsonHandler.writeToFile(Constain.STUDENTS_FILE_PATH, new ArrayList<Student>(), true);
        }

        // Đọc danh sách người dùng (Admin và Customer) từ tệp users.json
        if (JsonHandler.isFileReadable(Constain.USERS_FILE_PATH)) {
            List<User> adminAndCustomers = JsonHandler.readFromFile(Constain.USERS_FILE_PATH, new TypeReference<>() {});
            if (adminAndCustomers != null) {
                adminAndCustomers.removeIf(user -> user.getUsername() == null || user.getUsername().isEmpty());
                users.addAll(adminAndCustomers);
            }
        } else {
            System.out.println("File " + Constain.USERS_FILE_PATH + " không tồn tại hoặc không thể đọc. Tạo tệp mới.");
            JsonHandler.writeToFile(Constain.USERS_FILE_PATH, new ArrayList<User>(), true);
        }

        // Thêm người dùng admin cứng nếu chưa tồn tại
        if (users.stream().noneMatch(user -> user.getRole() == Role.ADMIN)) {
            users.add(new User("admin", "admin123", "admin@example.com", Role.ADMIN));
        }

        // Lưu danh sách người dùng vào AppContext
        appContext.setUsers(users);

        // Đọc danh sách khóa học từ tệp courses.json
        if (JsonHandler.isFileReadable(Constain.COURSES_FILE_PATH)) {
            List<Course> courses = JsonHandler.readFromFile(Constain.COURSES_FILE_PATH, new TypeReference<>() {});
            if (courses != null) {
                appContext.setCourses(courses);
            }
        } else {
            System.out.println("File " + Constain.COURSES_FILE_PATH + " không tồn tại hoặc không thể đọc. Tạo tệp mới.");
            JsonHandler.writeToFile(Constain.COURSES_FILE_PATH, new ArrayList<Course>(), true);
        }

        // Đọc danh sách lớp học từ tệp classes.json
        if (JsonHandler.isFileReadable(Constain.CLASSES_FILE_PATH)) {
            List<Classes> classes = JsonHandler.readFromFile(Constain.CLASSES_FILE_PATH, new TypeReference<>() {});
            if (classes != null) {
                appContext.setClasses(classes);
            }
        } else {
            System.out.println("File " + Constain.CLASSES_FILE_PATH + " không tồn tại hoặc không thể đọc. Tạo tệp mới.");
            JsonHandler.writeToFile(Constain.CLASSES_FILE_PATH, new ArrayList<Classes>(), true);
        }

        // Đọc danh sách bài học từ tệp lessons.json
        if (JsonHandler.isFileReadable(Constain.LESSONS_FILE_PATH)) {
            List<Lesson> lessons = JsonHandler.readFromFile(Constain.LESSONS_FILE_PATH, new TypeReference<>() {});
            if (lessons != null) {
                appContext.setLessons(lessons);
            }
        } else {
            System.out.println("File " + Constain.LESSONS_FILE_PATH + " không tồn tại hoặc không thể đọc. Tạo tệp mới.");
            JsonHandler.writeToFile(Constain.LESSONS_FILE_PATH, new ArrayList<Lesson>(), true);
        }

        // Đọc danh sách đăng ký từ tệp enrollments.json
        if (JsonHandler.isFileReadable(Constain.ENROLLMENTS_FILE_PATH)) {
            List<Enrollments> enrollments = JsonHandler.readFromFile(Constain.ENROLLMENTS_FILE_PATH, new TypeReference<>() {});
            if (enrollments != null) {
                appContext.setEnrollments(enrollments);
            }
        } else {
            System.out.println("File " + Constain.ENROLLMENTS_FILE_PATH + " không tồn tại hoặc không thể đọc. Tạo tệp mới.");
            JsonHandler.writeToFile(Constain.ENROLLMENTS_FILE_PATH, new ArrayList<Enrollments>(), true);
        }

        // Đọc danh sách điểm từ tệp scores.json
        if (JsonHandler.isFileReadable(Constain.SCORES_FILE_PATH)) {
            List<Score> scores = JsonHandler.readFromFile(Constain.SCORES_FILE_PATH, new TypeReference<>() {});
            if (scores != null) {
                appContext.setScores(scores);
            }
        } else {
            System.out.println("File " + Constain.SCORES_FILE_PATH + " không tồn tại hoặc không thể đọc. Tạo tệp mới.");
            JsonHandler.writeToFile(Constain.SCORES_FILE_PATH, new ArrayList<Score>(), true);
        }

        // Đọc danh sách blog từ tệp blogs.json
        if (JsonHandler.isFileReadable(Constain.BLOGS_FILE_PATH)) {
            List<Blog> blogs = JsonHandler.readFromFile(Constain.BLOGS_FILE_PATH, new TypeReference<>() {});
            if (blogs != null) {
                appContext.setBlogs(blogs);
            }
        } else {
            System.out.println("File " + Constain.BLOGS_FILE_PATH + " không tồn tại hoặc không thể đọc. Tạo tệp mới.");
            JsonHandler.writeToFile(Constain.BLOGS_FILE_PATH, new ArrayList<Blog>(), true);
        }
        // Đọc danh sách thẻ ATM từ tệp atm_cards.json
        if (JsonHandler.isFileReadable(Constain.ATM_CARDS_FILE_PATH)) {
            List<ATMCard> atmCards = JsonHandler.readFromFile(Constain.ATM_CARDS_FILE_PATH, new TypeReference<>() {});
            if (atmCards != null) {
                appContext.setATMCards(atmCards);
            }
        } else {
            System.out.println("File " + Constain.ATM_CARDS_FILE_PATH + " không tồn tại hoặc không thể đọc. Tạo tệp mới.");
            JsonHandler.writeToFile(Constain.ATM_CARDS_FILE_PATH, new ArrayList<>(), true);
        }


    }

    /**
     * Lưu dữ liệu vào các tệp JSON trước khi thoát chương trình.
     *
     * @throws IOException Nếu có lỗi khi ghi tệp.
     */
    public static void saveDataBeforeExit() throws IOException {
        System.out.println("Đang lưu dữ liệu trước khi thoát...");

        // Lấy danh sách người dùng từ AppContext
        List<User> users = appContext.getUsers();

        // Tách danh sách giáo viên, học sinh, admin và customer
        List<Teacher> teachers = users.stream()
                .filter(user -> user instanceof Teacher)
                .map(user -> (Teacher) user)
                .collect(Collectors.toList());

        List<Student> students = users.stream()
                .filter(user -> user instanceof Student)
                .map(user -> (Student) user)
                .collect(Collectors.toList());

        List<User> adminAndCustomers = users.stream()
                .filter(user -> user.getRole() == Role.ADMIN || user.getRole() == Role.CUSTOMER)
                .collect(Collectors.toList());

        // Ghi danh sách giáo viên vào teachers.json (nếu không rỗng)
        if (!teachers.isEmpty()) {
            JsonHandler.writeToFile(Constain.TEACHERS_FILE_PATH, teachers, true);
        } else {
            System.out.println("Danh sách giáo viên trống, không ghi vào tệp.");
        }

        // Ghi danh sách học sinh vào students.json (nếu không rỗng)
        if (!students.isEmpty()) {
            JsonHandler.writeToFile(Constain.STUDENTS_FILE_PATH, students, true);
        } else {
            System.out.println("Danh sách học sinh trống, không ghi vào tệp.");
        }

        // Ghi danh sách admin và customer vào users.json (nếu không rỗng)
        if (!adminAndCustomers.isEmpty()) {
            JsonHandler.writeToFile(Constain.USERS_FILE_PATH, adminAndCustomers, true);
        } else {
            System.out.println("Danh sách admin và customer trống, không ghi vào tệp.");
        }

        // Ghi danh sách khóa học vào courses.json (nếu không rỗng)
        List<Course> courses = appContext.getCourses();
        if (!courses.isEmpty()) {
            JsonHandler.writeToFile(Constain.COURSES_FILE_PATH, courses, true);
        } else {
            System.out.println("Danh sách khóa học trống, không ghi vào tệp.");
        }

        // Ghi danh sách lớp học vào classes.json (nếu không rỗng)
        List<Classes> classes = appContext.getClasses();
        if (!classes.isEmpty()) {
            JsonHandler.writeToFile(Constain.CLASSES_FILE_PATH, classes, true);
        } else {
            System.out.println("Danh sách lớp học trống, không ghi vào tệp.");
        }

        // Ghi danh sách bài học vào lessons.json (nếu không rỗng)
        List<Lesson> lessons = appContext.getLessons();
        if (!lessons.isEmpty()) {
            JsonHandler.writeToFile(Constain.LESSONS_FILE_PATH, lessons, true);
        } else {
            System.out.println("Danh sách bài học trống, không ghi vào tệp.");
        }

        // Ghi danh sách đăng ký vào enrollments.json (nếu không rỗng)
        List<Enrollments> enrollments = appContext.getEnrollments();
        if (!enrollments.isEmpty()) {
            JsonHandler.writeToFile(Constain.ENROLLMENTS_FILE_PATH, enrollments, true);
        } else {
            System.out.println("Danh sách đăng ký trống, không ghi vào tệp.");
        }

        // Ghi danh sách điểm vào scores.json (nếu không rỗng)
        List<Score> scores = appContext.getScores();
        if (!scores.isEmpty()) {
            JsonHandler.writeToFile(Constain.SCORES_FILE_PATH, scores, true);
        } else {
            System.out.println("Danh sách điểm trống, không ghi vào tệp.");
        }

        // Ghi danh sách blog vào blogs.json (nếu không rỗng)
        List<Blog> blogs = appContext.getBlogs();
        if (!blogs.isEmpty()) {
            JsonHandler.writeToFile(Constain.BLOGS_FILE_PATH, blogs, true);
        } else {
            System.out.println("Danh sách blog trống, không ghi vào tệp.");
        }

        List<ATMCard> atmCards = appContext.getList(ATMCard.class);
        if (!atmCards.isEmpty()) {
            JsonHandler.writeToFile(Constain.ATM_CARDS_FILE_PATH, atmCards, true);
        } else {
            System.out.println("Danh sách thẻ ATM trống, không ghi vào tệp.");
        }

        System.out.println("Lưu dữ liệu thành công!");
    }


}