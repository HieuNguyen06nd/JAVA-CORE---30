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
import java.util.function.Function;

public class ScoreService {

    public void scoreStudentsForLesson(AppContext context, User currentUser) {
        Scanner scanner = context.getScanner();
        List<Classes> classes = context.getList(Classes.class); // Lấy danh sách lớp học
        List<Lesson> lessons = context.getList(Lesson.class); // Lấy danh sách bài học
        List<Score> scores = context.getList(Score.class); // Lấy danh sách điểm
        List<User> users = context.getList(User.class); // Lấy danh sách người dùng
        PrintService printService = new PrintService();

        // Nhập ID lớp học
        printService.printClassesForTeacher(context, currentUser);
        System.out.print("Nhập ID lớp học: ");
        String classId = scanner.nextLine();

        // Tìm lớp học theo ID
        Classes classRoom = findById(classId, classes, Classes::getId);
        if (classRoom == null) {
            System.out.println("Không tìm thấy lớp học với ID: " + classId);
            return;
        }

        // Kiểm tra xem giáo viên hiện tại có dạy lớp này không
        if (!classRoom.getTeacher_id().equals(currentUser.getId())) {
            System.out.println("Bạn không dạy lớp học này.");
            return;
        }
        printService.displayLessonsWithStudentsAndScores(context, classId);

        // Nhập ID bài học
        System.out.print("Nhập ID bài học: ");
        String lessonId = scanner.nextLine();

        // Tìm bài học theo ID
        Lesson lesson = findById(lessonId, lessons, Lesson::getId);
        if (lesson == null || !lesson.getClass_id().equals(classId)) {
            System.out.println("Không tìm thấy bài học với ID: " + lessonId + " trong lớp học này.");
            return;
        }

        // Lấy danh sách học sinh trong lớp
        List<String> studentIds = classRoom.getStudent_id();
        if (studentIds == null || studentIds.isEmpty()) {
            System.out.println("Lớp học không có học sinh nào.");
            return;
        }

        // Chấm điểm cho từng học sinh
        System.out.println("=== Chấm điểm cho bài học: " + lesson.getTitle() + " ===");
        for (String studentId : studentIds) {
            // Tìm học sinh theo ID
            User student = findById(studentId, users, User::getId);
            if (student == null) {
                System.out.println("Không tìm thấy học sinh với ID: " + studentId);
                continue;
            }

            // Nhập điểm cho học sinh
            System.out.print("Nhập điểm cho học sinh " + student.getUsername() + " (" + studentId + ") (Nhập '99' nếu học sinh nghỉ): ");
            double scoreValue;
            try {
                scoreValue = Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Điểm không hợp lệ. Vui lòng nhập lại.");
                continue;
            }

            // Kiểm tra nếu học sinh nghỉ (điểm là 99)
            if (scoreValue == 99) {
                System.out.println("Học sinh " + student.getUsername() + " nghỉ học. Điểm được ghi là 99.");
            }

            // Tìm xem học sinh đã có điểm cho bài học này chưa
            Score existingScore = findScoreByStudentAndLesson(studentId, lessonId, context);
            if (existingScore != null) {
                // Cập nhật điểm nếu đã tồn tại
                existingScore.setScore(scoreValue);
                existingScore.setUpdate_at(LocalDate.now()); // Cập nhật thời gian sửa đổi
                System.out.println("Đã cập nhật điểm cho học sinh: " + student.getUsername());
            } else {
                // Thêm điểm mới nếu chưa tồn tại
                Score newScore = new Score(
                        studentId,
                        lessonId,
                        scoreValue,
                        currentUser.getId(),
                        LocalDate.now(),
                        LocalDate.now()
                );
                scores.add(newScore);
                System.out.println("Đã thêm điểm cho học sinh: " + student.getUsername());
            }
        }
    }

    public void calculateAverageScoreForStudent(AppContext context, String studentId) {
        // Lấy danh sách bài học và điểm từ AppContext
        List<Lesson> lessons = context.getList(Lesson.class);
        List<Score> scores = context.getList(Score.class);

        double totalScore = 0;
        int totalLessons = 0;

        for (Lesson lesson : lessons) {
            // Tìm điểm của học sinh trong bài học này
            Score score = findScoreByStudentAndLesson(studentId, lesson.getId(), context);
            if (score != null && score.getScore() != 99) { // Bỏ qua điểm 99
                totalScore += score.getScore();
                totalLessons++;
            }
        }

        // Tính điểm trung bình
        double averageScore = (totalLessons > 0) ? totalScore / totalLessons : 0;

        // Hiển thị kết quả
        System.out.println("=== Điểm trung bình của học sinh " + studentId + " ===");
        System.out.println("Tổng số bài học có điểm (không tính điểm nghỉ): " + totalLessons);
        System.out.println("Điểm trung bình: " + String.format("%.2f", averageScore));
        System.out.println("----------------------------------------");
    }

    public Score findScoreByStudentAndLesson(String studentId, String lessonId, AppContext appContext) {
        for (Score score : appContext.getList(Score.class)) {
            if (score.getStudent_id().equals(studentId) && score.getLesson_id().equals(lessonId)) {
                return score;
            }
        }
        return null;
    }
    public static <T> T findById(String id, List<T> list, Function<T, String> idGetter) {
        if (id == null || list == null || idGetter == null) {
            return null;
        }
        for (T item : list) {
            if (id.equals(idGetter.apply(item))) {
                return item;
            }
        }
        return null;
    }

}
