package service;

import entities.*;
import enums.Role;
import validate.ExistsCheck;

import java.util.ArrayList;
import java.util.Scanner;

public class ClassRoomService {
    ExistsCheck existsCheck = new ExistsCheck();
    public ClassRoom inputClassRoom(Scanner scanner, ArrayList<User>users,ArrayList<Lesson>lessons, ArrayList<Courses>courses) {

        System.out.print("Nhập tên lớp học: ");
        String name = scanner.nextLine();

        String teacherId;
        while (true) {
            System.out.print("Nhập teacherId: ");
            teacherId = scanner.nextLine();
            if (existsCheck.isValidRole(teacherId,users, Role.TEACHER)) {
                break;
            } else {
                System.out.println("teacherId không hợp lệ. Vui lòng nhập lại.");
            }
        }

        String courseId;
        while (true) {
            System.out.print("Nhập courseId: ");
            courseId = scanner.nextLine();
            if (existsCheck.isValidCourse(courseId,courses)) {
                break;
            } else {
                System.out.println("courseId không hợp lệ. Vui lòng nhập lại.");
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
                if (existsCheck.isValidRole(studentId,users,Role.STUDENT)) {
                    studentIdList.add(studentId);
                    break;
                } else {
                    System.out.println("ID học viên không hợp lệ. Vui lòng nhập lại.");
                }
            }
        }

        System.out.print("Nhập số lượng môn học trong lớp: ");
        int lessonCount = Integer.parseInt(scanner.nextLine());
        ArrayList<String> lessonIdList = new ArrayList<>();
        for (int i = 0; i < lessonCount; i++) {

            String lessonId;
            while (true) {
                System.out.print("Nhập ID môn học " + (i + 1) + ": ");
                lessonId = scanner.nextLine();
                if (existsCheck.isValidLesson(lessonId,lessons)) {
                    lessonIdList.add(lessonId);
                    break;
                } else {
                    System.out.println("ID khoá học không hợp lệ. Vui lòng nhập lại.");
                }
            }
        }

        return new ClassRoom(teacherId, name,courseId, studentIdList, lessonIdList);
    }

    public void updateStudentToClass(Scanner scanner,ArrayList<ClassRoom> classRooms, ArrayList<User> users,String key) {
        System.out.println("Nhập ID lớp học");
        String id = scanner.nextLine();
        ClassRoom classRoom =findById(id, classRooms);

        if (classRoom ==null){
            System.out.println("Không tìm thấy lớp học id: "+id);
        }else {
            System.out.println("Danh sách học viên hiện tại trong lớp: " + classRoom.getStudentId());

            System.out.print("Nhập ID học viên: ");
            String studentId = scanner.nextLine();

            if (existsCheck.isValidRole(studentId, users, Role.STUDENT)) {

                if (key.equals("add")){
                    if (!classRoom.getStudentId().contains(studentId)) {
                            classRoom.getStudentId().add(studentId);
                            System.out.println("Học viên " + studentId + " đã đượcb thêm vào lớp học.");

                    } else {
                        System.out.println("Học viên đã có trong lớp.");
                    }
                }else {
                    classRoom.getStudentId().remove(studentId);
                    System.out.println("Học viên " + studentId + " đã được xóa khỏi lớp học.");
                }

            } else {
                System.out.println("ID học viên không hợp lệ hoặc không phải là học viên.");
            }
        }

    }

    public void updateCourseToClass(Scanner scanner, ArrayList<ClassRoom> classRooms, ArrayList<Lesson> lessons, String key) {
        System.out.println("Nhập ID lớp học");
        String id = scanner.nextLine();
        ClassRoom classRoom = findById(id, classRooms);

        if (classRoom == null) {
            System.out.println("Không tìm thấy lớp học id: " + id);
        } else {
            System.out.println("Danh sách môn học hiện tại trong lớp: " + classRoom.getLessonId());

            System.out.print("Nhập ID môn học: ");
            String courseId = scanner.nextLine();

            if (existsCheck.isValidLesson(courseId,lessons)) {

                if (key.equals("add")){
                    if (!classRoom.getLessonId().contains(courseId)) {
                        classRoom.getLessonId().add(courseId);
                        System.out.println("Môn học " + courseId + " đã được thêm vào lớp học.");
                    } else {
                        System.out.println("Môn học đã có trong lớp.");
                    }
                }else {
                    classRoom.getLessonId().remove(courseId);
                    System.out.println("Môn học " + courseId + " đã được xóa khỏi lớp học.");
                }
            } else {
                System.out.println("ID môn học không hợp lệ.");
            }
        }
    }

    public void deleteClassRoom(Scanner scanner, ArrayList<ClassRoom> classRooms){
        System.out.print("Nhập id cần xóa: ");
        String id= scanner.nextLine();
        ClassRoom classRoom =findById(id,classRooms);
        if (classRoom==null){
            System.out.println("Không tìm thấy lớp học id: " + id);
        }else {
            classRooms.remove(classRoom);
            System.out.println("Xóa thành công lớp học id: "+id);
        }
    }


    public ClassRoom findById (String id, ArrayList <ClassRoom> classRooms){
        for (ClassRoom classRoom : classRooms) {
            if (classRoom.getId().equals(id)) {
                return classRoom;
            }
        }
        return null;
    }

}
