package validate;

import entities.*;
import enums.Role;

import java.util.ArrayList;
import java.util.List;

public class ExistsCheck {
    public boolean isValidUser(String user_id, ArrayList<User> users) {
        for (User user : users) {
            if (user.getId().equals(user_id)) {
                return true;
            }
        }
        return false;
    }

    public boolean isValidStudent(String student_id, ArrayList<Student> students) {
        for (User student : students) {
            if (student.getId().equals(student_id)) {
                return true;
            }
        }
        return false;
    }

    public boolean isValidLesson(String lesson_id, List<Lesson> lessons) {
        for (Lesson lesson : lessons) {
            if (lesson.getId().equals(lesson_id)) {
                return true;
            }
        }
        return false;
    }

    public boolean isValidCourse(String course_id, List<Courses> courses) {
        for (Courses course : courses) {
            if (course.getId().equals(course_id)) {
                return true;
            }
        }
        return false;
    }
    public boolean isValidClasRoom(String class_id, List<ClassRoom> classRooms) {
        for (ClassRoom classRoom : classRooms) {
            if (classRoom.getId().equals(class_id)) {
                return true;
            }
        }
        return false;
    }

    public boolean isValidLesson(String lesson_id, ArrayList<Lesson> lessons) {
        for (Lesson lesson : lessons) {
            if (lesson.getId().equals(lesson_id)) {
                return true;
            }
        }
        return false;
    }

    public boolean isValidRole(String id, ArrayList<User> users, Role role) {
        for (User user : users) {
            if (user.getId().equals(id) && user.getRole().equals(role)) {
                return true;
            }
        }
        return false;
    }

    public boolean isClassIdExists(String classId, ArrayList<ClassRoom> classRooms) {
        for (ClassRoom classRoom : classRooms) {
            if (classRoom.getId().equals(classId)) {
                return true;
            }
        }
        return false;
    }

//    kiem tra ton tại chua
    public boolean isOrderUnique(String classId, String order, ArrayList<Lesson> lessons) {
        for (Lesson lesson : lessons) {
            if (lesson.getClass_id().equals(classId) && lesson.getOrder().equals(order)) {
                return false;
            }
        }
        return true;
    }


}
