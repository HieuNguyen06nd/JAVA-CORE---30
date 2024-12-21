package service;

import entities.*;
import validate.ExistsCheck;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScoreService {
    ExistsCheck existsCheck = new ExistsCheck();
    public Scores inputScore(Scanner scanner, ArrayList<Student> students, ArrayList<Lesson>lessons, ArrayList<ClassRoom>classRooms) {

        String student_id;
        while (true) {
            System.out.print("Nhập student_id: ");
            student_id = scanner.nextLine();
            if (existsCheck.isValidStudent(student_id, students)) {
                break;
            } else {
                System.out.println("student_id không hợp lệ. Vui lòng nhập lại.");
            }
        }

        String lesson_id;
        while (true) {
            System.out.print("Nhập lesson_id: ");
            lesson_id = scanner.nextLine();
            if (existsCheck.isValidLesson(lesson_id,lessons)) {
                break;
            } else {
                System.out.println("lesson_id không hợp lệ. Vui lòng nhập lại.");
            }
        }

        String class_id;
        while (true) {
            System.out.print("Nhập class_id: ");
            class_id = scanner.nextLine();
            if (existsCheck.isValidClasRoom(class_id, classRooms)) {
                break;
            } else {
                System.out.println("class_id không hợp lệ. Vui lòng nhập lại.");
            }
        }

        double score;
        while (true) {
            System.out.print("Nhập điểm (0 - 10): ");
            score = scanner.nextDouble();
            if (score >= 0 && score <= 10) {
                break;
            } else {
                System.out.println("Điểm không hợp lệ. Vui lòng nhập lại điểm từ 0 đến 10.");
            }
        }

        return new Scores(student_id, lesson_id, class_id, score);
    }

    public void updateScore(Scanner scanner, ArrayList<Scores> scores, ArrayList<User> users, ArrayList<ClassRoom> classRooms, ArrayList<Lesson> lessons){
        String class_id;
        while (true) {
            System.out.print("Nhập class_id: ");
            class_id = scanner.nextLine();
            if (existsCheck.isValidClasRoom(class_id, classRooms)) {
                break;
            } else {
                System.out.println("class_id không hợp lệ hoặc không tồn tại. Vui lòng nhập lại.");
            }
        }
        String student_id;
        while (true) {
            System.out.print("Nhập student_id: ");
            student_id = scanner.nextLine();
            if (existsCheck.isValidUser(student_id, users)) {
                break;
            } else {
                System.out.println("student_id không hợp lệ. Vui lòng nhập lại.");
            }
        }
        String lesson_id;
        while (true) {
            System.out.print("Nhập lesson_id: ");
            lesson_id = scanner.nextLine();
            if (existsCheck.isValidLesson(lesson_id, lessons)) {
                break;
            } else {
                System.out.println("lesson_id không hợp lệ hoặc không tồn tại. Vui lòng nhập lại.");
            }
        }
        Scores scoreEntry = null;
        for (Scores score : scores) {
            if (score.getStudent_id().equals(student_id) && score.getClass_id().equals(class_id)) {
                scoreEntry = score;
                break;
            }
        }

        if (scoreEntry != null) {
            double newScore;
            while (true) {
                System.out.print("Nhập điểm mới (0 - 10): ");
                newScore = scanner.nextDouble();
                if (newScore >= 0 && newScore <= 10) {
                    scoreEntry.setScore(newScore);  // Update the score
                    System.out.println("Điểm đã được cập nhật thành công.");
                    break;
                } else {
                    System.out.println("Điểm không hợp lệ. Vui lòng nhập lại điểm từ 0 đến 10.");
                }
            }
        } else {
            System.out.println("Không tìm thấy điểm của sinh viên với ID " + student_id + " trong lớp " + class_id);
        }
    }


}
