package service;

import entities.*;
import validate.ExistsCheck;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScoreService {
    ExistsCheck existsCheck = new ExistsCheck();
    public Scores inputScore(Scanner scanner, ArrayList<Student> students, ArrayList<Lesson>lessons, ArrayList<Courses>courses) {

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
            if (existsCheck.isValidLesson(lesson_id, lessons)) {
                break;
            } else {
                System.out.println("lesson_id không hợp lệ. Vui lòng nhập lại.");
            }
        }

        String course_id;
        while (true) {
            System.out.print("Nhập course_id: ");
            course_id = scanner.nextLine();
            if (existsCheck.isValidCourse(course_id, courses)) {
                break;
            } else {
                System.out.println("course_id không hợp lệ. Vui lòng nhập lại.");
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

        return new Scores(student_id, lesson_id, course_id, score);
    }


}
