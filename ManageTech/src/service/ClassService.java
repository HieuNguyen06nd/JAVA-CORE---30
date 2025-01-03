package service;

import entities.Classes;
import entities.Course;
import entities.User;
import enums.Constain;
import enums.Role;
import exist.Exist;
import exist.Utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClassService {
    Exist exist = new Exist();
    Utils utils = new Utils();
    UserService userService = new UserService();
    CourseService courseService = new CourseService();
    public void inputClass(AppContext appContext) {
        List<Classes> classes = appContext.getList(Classes.class);
        Scanner scanner = appContext.getScanner();
        List<Course>courses = appContext.getList(Course.class);

        System.out.println("Nhập tên lớp: ");
        String name = scanner.nextLine();

        String courseId;
        while (true) {
            System.out.println("Nhập ID khóa học: ");
            courseId = scanner.nextLine();
            Course course = courseService.findById(courseId, courses, Course::getId);
            if (course != null) {
                break;
            } else {
                System.out.println("Không tìm thấy khóa học. Vui lòng nhập lại.");
            }
        }

        String teacherId;
        while (true) {
            System.out.print("Nhập ID giảng viên: ");
            teacherId = scanner.nextLine();

            if (exist.isValidRole(teacherId, appContext, Role.TEACHER)) {
                break;
            } else {
                System.out.println("ID giảng viên không hợp lệ hoặc không có vai trò là Teacher. Vui lòng nhập lại.");
            }
        }

        System.out.print("Nhập số lượng học viên trong lớp: ");
        int studentCount = Integer.parseInt(scanner.nextLine());
        ArrayList<String> studentIdList = new ArrayList<>();
        for (int i = 0; i < studentCount; i++) {
            String studentId;
            while (true) {
                System.out.print("Nhập ID học viên " + (i + 1) + ": ");
                studentId = scanner.nextLine();
                if (exist.isValidRole(studentId,appContext, Role.STUDENT)) {
                    studentIdList.add(studentId);
                    break;
                } else {
                    System.out.println("ID học viên không hợp lệ. Vui lòng nhập lại.");
                }
            }
        }

        LocalDate startDate;
        while (true) {
            startDate= utils.inputDate(scanner, "Nhập ngày bắt đầu (yyyy-mm-dd): ");
            if (startDate.isAfter(LocalDate.now())) {
                break;
            } else {
                System.out.println("Ngày bắt đầu phải sau ngày hiện tại. Vui lòng nhập lại.");
            }
        }

        LocalDate endDate;
        while (true) {
            endDate = utils.inputDate(scanner, "Nhập ngày kết thúc (yyyy-mm-dd): ");
            if (endDate.isAfter(startDate)) {
                break;
            } else {
                System.out.println("Ngày kết thúc phải sau ngày bắt đầu. Vui lòng nhập lại.");
            }
        }

        String schedule = "";
        while (true) {
            System.out.println("Chọn lịch học cho lớp học: ");
            System.out.println("1. Lịch học: Thứ 2, 4, 6");
            System.out.println("2. Lịch học: Thứ 3, 5, 7");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case Constain.MON_WED_FRI:
                    schedule = "Thứ 2, 4, 6";
                    break;
                case Constain.TUE_THU_SAT:
                    schedule = "Thứ 3, 5, 7";
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng chọn lại.");
                    continue;
            }

            if (exist.isScheduleConflict(new Classes(name, courseId, teacherId, studentIdList, startDate, endDate, schedule), schedule, classes)) {
                System.out.println("Lịch học này bị xung đột với các lớp học khác. Vui lòng chọn lịch học khác.");
            } else {
                break;
            }
        }

        Classes newClass = new Classes(name, courseId, teacherId, studentIdList, startDate, endDate, schedule);
        classes.add(newClass);

        System.out.println("Lớp học đã được thêm thành công!");
    }

    public void updateStudentToClass(AppContext appContext, String action) {
        List<Classes> classRooms = appContext.getList(Classes.class);
        List<User> users = appContext.getList(User.class);
        Scanner scanner = appContext.getScanner();

        System.out.println("Nhập ID lớp học: ");
        String classId = scanner.nextLine();

        Classes classs = findById(classId, classRooms);
        if (classs == null) {
            System.out.println("Lớp học không tồn tại.");
            return;
        }

        System.out.println("Nhập ID học viên: ");
        String studentId = scanner.nextLine();

        User student = userService.findById(studentId, users);
        if (student == null || !student.getRole().equals(Role.STUDENT)) {
            System.out.println("Học viên không hợp lệ.");
            return;
        }

        if (action.equals("add")) {
            classs.getStudent_id().add(studentId);
            System.out.println("Học viên đã được thêm vào lớp.");
        } else if (action.equals("delete")) {
            classs.getStudent_id().remove(studentId);
            System.out.println("Học viên đã được xóa khỏi lớp.");
        }
    }

    public void deleteClassRoom(AppContext appContext) {
        List<Classes> classes = appContext.getList(Classes.class);
        Scanner scanner = appContext.getScanner();

        System.out.println("Nhập ID lớp học cần xóa:");
        String classId = scanner.nextLine();

        Classes classs = findById(classId, classes);
        if (classs == null) {
            System.out.println("Lớp học không tồn tại với ID: " + classId);
            return;
        }
        classes.remove(classs);
        System.out.println("Lớp học với ID " + classId + " đã được xóa thành công.");
    }

    public void changeTeacherInClass(AppContext appContext) {
        Scanner scanner = appContext.getScanner();
        List<Classes> classes = appContext.getList(Classes.class);
        System.out.println("Nhập ID lớp học cần thay đổi giáo viên:");
        String classId = scanner.nextLine();

        Classes classRoom = findById(classId, classes);
        if (classRoom == null) {
            System.out.println("Lớp học không tồn tại.");
            return;
        }

        String teacherId;
        while (true) {
            System.out.print("Nhập ID giảng viên: ");
            teacherId = scanner.nextLine();

            if (exist.isValidRole(teacherId, appContext, Role.TEACHER)) {
                break;
            } else {
                System.out.println("ID giảng viên không hợp lệ hoặc không có vai trò là Teacher. Vui lòng nhập lại.");
            }
        }
    }

    public Classes findById(String classId, List<Classes> classes) {
        for (Classes classItem : classes) {
            if (classItem.getId().equals(classId)) {
                return classItem;
            }
        }
        return null;
    }



}
