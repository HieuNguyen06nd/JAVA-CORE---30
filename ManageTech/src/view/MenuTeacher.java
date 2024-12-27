package view;


import entities.Score;
import entities.User;
import enums.Role;
import service.*;

import java.util.Scanner;

public class MenuTeacher {
    PrintService printService = new PrintService();
    EnrollmentService enrollmentService = new EnrollmentService();
    MenuStudent menu = new MenuStudent();
    LessonService lessonService = new LessonService();
    BlogService blogService = new BlogService();
    ScoreService scoreService= new ScoreService();

    public void displayTeacher(AppContext context, User user){
        while (true) {
            System.out.println("\n====== MENU CHỨC NĂNG ======");
            System.out.println("1. Quản lý thông tin cá nhân");
            System.out.println("3. Chấm điểm cho học sinh");
            System.out.println("4. Xem lịch dạy");
            System.out.println("5. Tạo bài học cho Class");
            System.out.println("7. Xem thông tin lương");
            System.out.println("8. Đăng bài lên Blog");
            System.out.println("9. Quay lại");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");
            selectMenu(context, user);
        }
    }
    public void selectMenu(AppContext context, User user){
        Scanner scanner = context.getScanner();
        int choose = Integer.parseInt(scanner.nextLine());
        switch (choose){
            case 1:
                menu.loginMenu(context,user);
                break;
            case 3:
               scoreService.inputScoreForLesson(context,user);
                break;
            case 4:
                printService.printTeachingSchedule(context);
                break;
            case 5:
                lessonService.inputLesson(context);
                break;
            case 7:

                break;
            case 8:
                blogService.inpuBlog(context,user);
                break;
            case 9:
                return;
            case 0:
                System.exit(1);
            default:
                System.out.println("Lựa chọn không hợp lệ");
        }
    }



}
