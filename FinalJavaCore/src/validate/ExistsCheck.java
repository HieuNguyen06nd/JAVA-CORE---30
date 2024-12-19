package validate;

import entities.*;

import java.util.ArrayList;
import java.util.List;

public class ExistsCheck {
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

    public boolean isValidTeacher(String teacherId, ArrayList<Teacher> teachers) {
        for (Teacher teacher : teachers) {
            if (teacher.getId().equals(teacherId)) {
                return true;
            }
        }
        return false;
    }
}
