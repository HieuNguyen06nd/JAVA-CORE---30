package service;

import entities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppContext {
    private static AppContext instance;
    private Scanner scanner;
    private ArrayList<User> users;
    private ArrayList<Teacher> teachers;
    private ArrayList<Score> scores;
    private ArrayList<Course>courses;
    private ArrayList<Student> students;
    private ArrayList<Lesson> lessons;
    private ArrayList<Classes>classes;
    private ArrayList<Blog> blogs;
    private ArrayList<Enrollments> enrollments;


    private AppContext() {
        this.scanner = new Scanner(System.in);
        this.users = new ArrayList<>();
        this.teachers = new ArrayList<>();
        this.students = new ArrayList<>();
        this.lessons = new ArrayList<>();
        this.courses = new ArrayList<>();
        this.classes= new ArrayList<>();
        this.blogs = new ArrayList<>();
        this.enrollments = new ArrayList<>();
        this.scores = new ArrayList<>();
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

    public ArrayList<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }
    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }
    public ArrayList<Student> getStudents() {
        return students;
    }
    public ArrayList<Classes> getClasses() {
        return classes;
    }
    public ArrayList<Course> getCourses() {
        return courses;
    }
    public ArrayList<Lesson> getLessons() {
        return lessons;
    }

    public ArrayList<Blog> getBlogs() {
        return blogs;
    }
    public ArrayList<Enrollments> getEnrollments() {
        return enrollments;
    }
    public ArrayList<Score> getScores() {
        return scores;
    }

    private final List<Object> services = new ArrayList<>();

    public void registerService(Object service) {
        services.add(service);
    }

    public <T> T getService(Class<T> serviceClass) {
        for (Object service : services) {
            if (serviceClass.isInstance(service)) {
                return serviceClass.cast(service);
            }
        }
        throw new RuntimeException("Dịch vụ không tồn tại: " + serviceClass.getSimpleName());
    }

}
