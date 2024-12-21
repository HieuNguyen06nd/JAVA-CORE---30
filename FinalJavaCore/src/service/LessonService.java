package service;

import entities.ClassRoom;
import entities.Lesson;
import validate.ExistsCheck;

import java.util.ArrayList;
import java.util.Scanner;

public class LessonService {
    ExistsCheck existsCheck = new ExistsCheck();
    public Lesson inputLesson(Scanner scanner, ArrayList<ClassRoom>classRooms, ArrayList<Lesson>lessons) {
        System.out.println("=== Nhập thông tin bài học ===");

        String classId;
        while (true) {
            System.out.print("Nhập ID lớp: ");
            classId = scanner.nextLine();
            if (existsCheck.isValidClasRoom(classId, classRooms)) {
                break;
            } else {
                System.out.println("ID lớp không hợp lệ hoặc không tồn tại. Vui lòng nhập lại.");
            }
        }

        System.out.print("Nhập tiêu đề bài học: ");
        String title = scanner.nextLine();

        System.out.print("Nhập nội dung bài học: ");
        String content = scanner.nextLine();

        String order;
        while (true) {
            System.out.print("Nhập thứ tự bài học trong khóa học: ");
            order = scanner.nextLine();
            if (existsCheck.isOrderUnique(classId, order,lessons )) {
                break;
            } else {
                System.out.println("Thứ tự bài học đã tồn tại trong khóa học. Vui lòng nhập lại.");
            }
        }

        Lesson lesson = new Lesson(classId, title, content, order);
        System.out.println("Bài học đã được tạo: " + lesson);
        return lesson;
    }
    public void deleteLesson(Scanner scanner, ArrayList<Lesson> lessons) {
        System.out.print("Nhập ID bài học cần xóa: ");
        String lessonId = scanner.nextLine();
        Lesson lesson = findById(lessonId, lessons);
        if (lesson != null) {
            lessons.remove(lesson);
            System.out.println("Đã xóa bài học với ID: " + lessonId);
        } else {
            System.out.println("Không tìm thấy bài học với ID: " + lessonId);
        }
    }
    public void changeLessonTitle(Scanner scanner, Lesson lesson) {
        System.out.print("Nhập tiêu đề mới: ");
        String newTitle = scanner.nextLine();
        lesson.setTitle(newTitle);
        System.out.println("Tiêu đề bài học đã được thay đổi thành: " + newTitle);
    }

    public void changeLessonContent(Scanner scanner, Lesson lesson) {
        System.out.print("Nhập nội dung mới: ");
        String newContent = scanner.nextLine();
        lesson.setContent(newContent);
        System.out.println("Nội dung bài học đã được thay đổi.");
    }

    public void changeLessonClass(Scanner scanner, Lesson lesson, ArrayList<ClassRoom> classRooms, ArrayList<Lesson> lessons) {
        System.out.print("Nhập ID lớp mới: ");
        String newClassId = scanner.nextLine();
        if (existsCheck.isClassIdExists(newClassId, classRooms)) {

            String oldOrder = lesson.getOrder();
            if (!existsCheck.isOrderUnique(newClassId, oldOrder, lessons)) {
                System.out.println("Thứ tự bài học đã tồn tại trong lớp. Vui lòng nhập thứ tự mới.");
                System.out.print("Nhập thứ tự mới: ");
                String newOrder = scanner.nextLine();
                lesson.setOrder(newOrder);
            }

            lesson.setClass_id(newClassId);
            System.out.println("Lớp học của bài học đã được thay đổi.");
        } else {
            System.out.println("Lớp học không hợp lệ. Vui lòng thử lại.");
        }
    }
    public void swapLessonOrders(Scanner scanner, ArrayList<Lesson> lessons) {
        System.out.println("Nhập ID của bài học 1: ");
        String lessonId1 = scanner.nextLine();
        System.out.println("Nhập ID của bài học 2: ");
        String lessonId2 = scanner.nextLine();

        Lesson lesson1 = findById(lessonId1, lessons);
        Lesson lesson2 = findById(lessonId2, lessons);

        if (lesson1 == null || lesson2 == null) {
            System.out.println("Một hoặc cả hai ID bài học không tồn tại.");
            return;
        }

        String order1 = lesson1.getOrder();
        String order2 = lesson2.getOrder();

        lesson1.setOrder(order2);
        lesson2.setOrder(order1);
    }


    public Lesson findById(String lessonId, ArrayList<Lesson>lessons){
        for (Lesson lesson : lessons) {
            if (lesson.getId().equals(lessonId)) {
                return lesson;
            }
        }
        return null;
    }

}
