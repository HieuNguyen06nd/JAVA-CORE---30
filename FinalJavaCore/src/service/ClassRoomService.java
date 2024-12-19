package service;

import entities.ClassRoom;
import entities.Courses;
import entities.Student;
import entities.Teacher;
import validate.ExistsCheck;

import java.util.ArrayList;
import java.util.Scanner;

public class ClassRoomService {
    ExistsCheck existsCheck = new ExistsCheck();
    public ClassRoom inputClassRoom(Scanner scanner, ArrayList<Teacher>teachers,
                                    ArrayList<Student> students, ArrayList<Courses>courses) {

        System.out.print("Nhập tên lớp học: ");
        String name = scanner.nextLine();

        String teacherId;
        while (true) {
            System.out.print("Nhập teacherId: ");
            teacherId = scanner.nextLine();
            if (existsCheck.isValidTeacher(teacherId, teachers)) {
                break;
            } else {
                System.out.println("teacherId không hợp lệ. Vui lòng nhập lại.");
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
                if (existsCheck.isValidStudent(studentId,students)) {
                    studentIdList.add(studentId);
                    break;
                } else {
                    System.out.println("ID học viên không hợp lệ. Vui lòng nhập lại.");
                }
            }

            studentIdList.add(scanner.nextLine());
        }

        System.out.print("Nhập số lượng môn học trong lớp: ");
        int coursesCount = Integer.parseInt(scanner.nextLine());
        ArrayList<String> coursesIdList = new ArrayList<>();
        for (int i = 0; i < coursesCount; i++) {

            String courseId;
            while (true) {
                System.out.print("Nhập ID môn học " + (i + 1) + ": ");
                courseId = scanner.nextLine();
                if (existsCheck.isValidStudent(courseId,students)) {
                    coursesIdList.add(courseId);
                    break;
                } else {
                    System.out.println("ID học viên không hợp lệ. Vui lòng nhập lại.");
                }
            }
        }

        return new ClassRoom(teacherId, name, studentIdList, coursesIdList);
    }
}
