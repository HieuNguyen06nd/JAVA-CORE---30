package data;

import entities.*;

import java.util.List;

public class DataWrapper {
    private List<User> users;
    private List<Course> courses;
    private List<Teacher> teachers;
    private List<Student> students;
    private List<Lesson> lessons;
    private List<Classes> classes;
    private List<Blog> blogs;
    private List<Enrollments> enrollments;
    private List<Score> scores;

    // Constructor, getters và setters
    public DataWrapper(List<User> users, List<Course> courses, List<Teacher> teachers, List<Student> students,
                       List<Lesson> lessons, List<Classes> classes, List<Blog> blogs,
                       List<Enrollments> enrollments, List<Score> scores) {
        this.users = users;
        this.courses = courses;
        this.teachers = teachers;
        this.students = students;
        this.lessons = lessons;
        this.classes = classes;
        this.blogs = blogs;
        this.enrollments = enrollments;
        this.scores = scores;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public List<Classes> getClasses() {
        return classes;
    }

    public void setClasses(List<Classes> classes) {
        this.classes = classes;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    public List<Enrollments> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollments> enrollments) {
        this.enrollments = enrollments;
    }

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }
}