package service;

import entities.Classes;
import entities.Lesson;
import entities.Score;
import entities.User;
import exist.Exist;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScoreService {
    Exist exist = new Exist();
    LessonService lessonService = new LessonService();
    public void inputScoreForLesson(AppContext appContext, User user) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Classes> classes = appContext.getClasses();
        ArrayList<Lesson> lessons = appContext.getLessons();

        String teacher_id = user.getId();

        Lesson lesson= null;
        String lessonId;
        while (true) {
            System.out.print("Nhập ID bài học: ");
            lessonId = scanner.nextLine();
            lesson =lessonService.findById(lessonId,lessons);
            if (lesson != null) {
                break;
            } else {
                System.out.println("Bài học không tồn tại! Vui lòng nhập lại.");
            }
        }

        String studentId;
        while (true) {
            System.out.print("Nhập ID học sinh: ");
            studentId = scanner.nextLine();
            if (exist.isStudentInClass(studentId, appContext)) {
                break;
            } else {
                System.out.println("Học sinh không có trong lớp học này! Vui lòng nhập lại.");
            }
            break;
        }

        Score existingScore = findScoreByStudentAndLesson(studentId, lessonId, appContext);
        double score;
        if (existingScore != null) {
            System.out.println("Học sinh đã có điểm cho bài học này.");
            while (true) {
                System.out.print("Nhập điểm mới cho học sinh (0 - 10): ");
                score = Double.parseDouble(scanner.nextLine());
                if (score >= 0 && score <= 10) {
                    existingScore.setScore(score);
                    existingScore.setUpdate_at(LocalDate.now());
                    System.out.println("Điểm của học sinh đã được cập nhật thành công.");
                    break;
                } else {
                    System.out.println("Điểm không hợp lệ, vui lòng nhập lại.");
                }
            }
        } else {
            while (true) {
                System.out.print("Nhập điểm cho học sinh (0 - 10): ");
                score = Double.parseDouble(scanner.nextLine());
                if (score >= 0 && score <= 10) {
                    Score newScore = new Score(studentId, lessonId, score, teacher_id, LocalDate.now(), LocalDate.now());
                    appContext.getScores().add(newScore);
                    System.out.println("Đã nhập điểm thành công cho học sinh.");
                    break;
                } else {
                    System.out.println("Điểm không hợp lệ, vui lòng nhập lại.");
                }
            }
        }
    }

    public Score findScoreByStudentAndLesson(String studentId, String lessonId, AppContext appContext) {
        for (Score score : appContext.getScores()) {
            if (score.getStudent_id().equals(studentId) && score.getLesson_id().equals(lessonId)) {
                return score;
            }
        }
        return null;
    }

}
