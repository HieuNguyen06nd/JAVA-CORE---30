package service;

import entities.*;
import java.util.*;

public class AppContext {
    private static AppContext instance;
    private Scanner scanner;
    private Map<Class<?>, List<?>> dataStore; // Sử dụng Map để quản lý danh sách
    private final List<Object> services; // Danh sách các dịch vụ
    private User currentUser; // Biến lưu trữ người dùng hiện tại

    private AppContext() {
        this.scanner = new Scanner(System.in);
        this.dataStore = new HashMap<>();
        this.services = new ArrayList<>();
        this.currentUser = null; // Khởi tạo currentUser là null
    }

    public static AppContext getInstance() {
        if (instance == null) {
            instance = new AppContext();
        }
        return instance;
    }

    public Scanner getScanner() {
        return scanner;
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> getList(Class<T> clazz) {
        return (List<T>) dataStore.computeIfAbsent(clazz, k -> new ArrayList<>());
    }

    public <T> void clearList(Class<T> clazz) {
        dataStore.remove(clazz);
    }

    // Phương thức quản lý Users
    public void setUsers(List<User> users) {
        if (users == null) {
            throw new IllegalArgumentException("Danh sách users không được null.");
        }
        clearList(User.class);
        getList(User.class).addAll(users);
    }

    public List<User> getUsers() {
        return getList(User.class);
    }

    // Phương thức quản lý Courses
    public void setCourses(List<Course> courses) {
        if (courses == null) {
            throw new IllegalArgumentException("Danh sách courses không được null.");
        }
        clearList(Course.class);
        getList(Course.class).addAll(courses);
    }

    public List<Course> getCourses() {
        return getList(Course.class);
    }

    // Phương thức quản lý Classes
    public void setClasses(List<Classes> classes) {
        if (classes == null) {
            throw new IllegalArgumentException("Danh sách classes không được null.");
        }
        clearList(Classes.class);
        getList(Classes.class).addAll(classes);
    }

    public List<Classes> getClasses() {
        return getList(Classes.class);
    }

    // Phương thức quản lý Lessons
    public void setLessons(List<Lesson> lessons) {
        if (lessons == null) {
            throw new IllegalArgumentException("Danh sách lessons không được null.");
        }
        clearList(Lesson.class);
        getList(Lesson.class).addAll(lessons);
    }

    public List<Lesson> getLessons() {
        return getList(Lesson.class);
    }

    // Phương thức quản lý Enrollments
    public void setEnrollments(List<Enrollments> enrollments) {
        if (enrollments == null) {
            throw new IllegalArgumentException("Danh sách enrollments không được null.");
        }
        clearList(Enrollments.class);
        getList(Enrollments.class).addAll(enrollments);
    }

    public List<Enrollments> getEnrollments() {
        return getList(Enrollments.class);
    }

    // Phương thức quản lý Scores
    public void setScores(List<Score> scores) {
        if (scores == null) {
            throw new IllegalArgumentException("Danh sách scores không được null.");
        }
        clearList(Score.class);
        getList(Score.class).addAll(scores);
    }

    public List<Score> getScores() {
        return getList(Score.class);
    }

    // Phương thức quản lý Blogs
    public void setBlogs(List<Blog> blogs) {
        if (blogs == null) {
            throw new IllegalArgumentException("Danh sách blogs không được null.");
        }
        clearList(Blog.class);
        getList(Blog.class).addAll(blogs);
    }

    public void setATMCards(List<ATMCard> atmCards) {
        if (atmCards == null) {
            throw new IllegalArgumentException("Danh sách thẻ ATM không được null.");
        }
        clearList(ATMCard.class); // Xóa danh sách cũ (nếu có)
        getList(ATMCard.class).addAll(atmCards); // Thêm danh sách mới
    }

    public List<ATMCard> getATMCards() {
        return getList(ATMCard.class);
    }

    public List<Blog> getBlogs() {
        return getList(Blog.class);
    }

    // Phương thức lấy userId của người dùng hiện tại
    public String getCurrentUserId() {
        if (currentUser != null) {
            return currentUser.getId(); // Giả sử lớp User có phương thức getId()
        }
        return null; // Trả về null nếu chưa có người dùng đăng nhập
    }

    // Phương thức đặt người dùng hiện tại (sau khi đăng nhập thành công)
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }


}