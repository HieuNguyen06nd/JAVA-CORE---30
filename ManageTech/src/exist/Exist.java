package exist;

import entities.*;
import enums.Role;
import service.AppContext;

import java.util.ArrayList;
import java.util.List;

public class Exist {
    public boolean isClassExist(String classId, List<Classes> classRooms) {
        for (Classes classes : classRooms) {
            if (classes.getId().equals(classId)) {
                return true;
            }
        }
        return false;
    }
    public boolean isOrderExist(String classId, int order, List<Lesson> lessons) {
        for (Lesson lesson : lessons) {
            if (lesson.getClass_id().equals(classId) && lesson.getOrder() == order) {
                return true;
            }
        }
        return false;
    }

    public boolean isCourseExist(String courseId, AppContext appContext) {
        List<Course> courses = appContext.getList(Course.class);

        for (Course course : courses) {
            if (course.getId().equals(courseId)) {
                return true;
            }
        }
        return false;
    }

    public boolean isStudentInClass(String studentId, AppContext appContext) {
        List<Classes> classes = appContext.getList(Classes.class);

        for (Classes classItem : classes) {
            if (classItem.getStudent_id().contains(studentId)) {
                return true;
            }
        }
        return false;
    }

    public boolean isStudent(String studentId, AppContext appContext) {
        List<User> users = appContext.getList(User.class);

        for (User user : users) {
            if (user.getId().equals(studentId)) {
                if (user.getRole().equals(Role.STUDENT)) {
                    return true;
                } else {
                    System.out.println("User với ID " + studentId + " không phải là STUDENT.");
                    return false;
                }
            }
        }
        System.out.println("Không tìm thấy user với ID " + studentId);
        return false;
    }

    public boolean isValidRole(String id,AppContext appContext, Role role) {
        List<User> users = appContext.getList(User.class);
        for (User user : users) {
            if (user.getId().equals(id) && user.getRole().equals(role)) {
                return true;
            }
        }
        return false;
    }

    public boolean isAlreadyEnrolled(String userId, String courseId, ArrayList<Enrollments> enrollments) {
        for (Enrollments enrollment : enrollments) {
            if (enrollment.getUser_id().equals(userId) && enrollment.getCourse_id().equals(courseId)) {
                return true;
            }
        }
        return false;
    }

    public boolean isScheduleConflict(Classes classRoom, String newSchedule, List<Classes> classes) {
        for (Classes otherClass : classes) {
            if (!otherClass.getId().equals(classRoom.getId())) {
                if (otherClass.getSchedule().equals(newSchedule)) {
                    if (otherClass.getTeacher_id().equals(classRoom.getTeacher_id())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }




}

