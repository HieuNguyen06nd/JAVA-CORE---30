package service;

import entities.Classes;
import entities.User;
import enums.Role;
import exist.Exist;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class ClassService {
    Exist exist = new Exist();
    UserService userService = new UserService();
    public void inputClass(AppContext appContext) {
        ArrayList<Classes> classes = appContext.getClasses();
        Scanner scanner = appContext.getScanner();

        System.out.println("Nhập tên lớp: ");
        String name = scanner.nextLine();

        System.out.println("Nhập ID khóa học: ");
        String courseId = scanner.nextLine();

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
            System.out.println("Nhập ngày bắt đầu (yyyy-mm-dd): ");
            startDate = LocalDate.parse(scanner.nextLine());
            if (startDate.isAfter(LocalDate.now())) {
                break;
            } else {
                System.out.println("Ngày bắt đầu phải sau ngày hiện tại. Vui lòng nhập lại.");
            }
        }

        LocalDate endDate;
        while (true) {
            System.out.println("Nhập ngày kết thúc (yyyy-mm-dd): ");
            endDate = LocalDate.parse(scanner.nextLine());
            if (endDate.isAfter(startDate)) {
                break;
            } else {
                System.out.println("Ngày kết thúc phải sau ngày bắt đầu. Vui lòng nhập lại.");
            }
        }

        System.out.println("Nhập lịch học: ");
        String schedule = scanner.nextLine();

        Classes newClass = new Classes(name, courseId, teacherId, studentIdList, startDate, endDate, schedule);
        classes.add(newClass);

        System.out.println("Lớp học đã được thêm thành công!");
    }

    public void updateStudentToClass(AppContext appContext, String action) {
        ArrayList<Classes> classRooms = appContext.getClasses();
        ArrayList<User> users = appContext.getUsers();
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
        ArrayList<Classes> classes = appContext.getClasses();
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
        ArrayList<Classes> classes = appContext.getClasses();
        ArrayList<User> users = appContext.getUsers();
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

    public Classes findById(String classId, ArrayList<Classes> classes) {
        for (Classes classItem : classes) {
            if (classItem.getId().equals(classId)) {
                return classItem;
            }
        }
        return null;
    }



}
