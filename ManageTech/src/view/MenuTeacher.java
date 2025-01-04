package view;


import entities.Score;
import entities.Teacher;
import entities.User;
import enums.Role;
import exist.Utils;
import service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuTeacher {
    PrintService printService = new PrintService();
    MenuStudent menu = new MenuStudent();
    LessonService lessonService = new LessonService();
    BlogService blogService = new BlogService();
    ScoreService scoreService= new ScoreService();
    UserService userService= new UserService();
    TeacherService teacherService = new TeacherService();
    Utils utils = new Utils();

    public void displayTeacher(AppContext context, User user){
        while (true) {
            System.out.println("\n====== MENU CHỨC NĂNG ======");
            System.out.println("1. Quản lý thông tin cá nhân");
            System.out.println("2. Xem Diểm của học vien mk dạy theo lớp");
            System.out.println("3. Chấm điểm cho học sinh");
            System.out.println("4. Xem lịch dạy");
            System.out.println("5. Tạo lesson cho Class");
            System.out.println("7. Quản lý lesson");
            System.out.println("8. Đăng bài lên Blog");
            System.out.println("9. Quay lại");
            System.out.println("0. Thoát");
            selectMenu(context, user);
        }
    }
    public void selectMenu(AppContext context, User user){
        Scanner scanner = context.getScanner();
        int choose = utils.inputInt(scanner, " Mời lựa chọn: " );
        switch (choose){
            case 1:
                menu.loginMenu(context,user);
                break;
            case 2:
                printService.printStudentScoresByClassAndLesson(context, user);
                break;
            case 3:
               scoreService.scoreStudentsForLesson(context,user);
                break;
            case 4:
                printService.printTeachingSchedule(context);
                break;
            case 5:

                break;
            case 7:
                manageLesson(context, user);
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
    public void manageLesson(AppContext appContext, User user) {
        Scanner scanner = appContext.getScanner();


        while (true) {
            System.out.println("\n====== QUẢN LÝ LESSON ======");
            System.out.println("1. Tạo lesson");
            System.out.println("2. Cập nhật lesson");
            System.out.println("3. Xóa lesson theo ID");
            System.out.println("4. Đổi chỗ hai bài học theo thứ tự");
            System.out.println("7. Hiển thị danh sách lesson theo lớp");
            System.out.println("9. Quay lại");

            int choose = utils.inputInt(scanner, " Mời lựa chọn: ");
            switch (choose) {
                case 1:
                    lessonService.inputLesson(appContext);
                    break;
                case 2:
                    lessonService.changeLesson(appContext, user);
                    break;
                case 3:
                    lessonService.deleteLesson(appContext, user);
                    break;
                case 4:
                    lessonService.swapLessonOrders(appContext, user);
                    break;
                case 7:
                    printService.printClassesAndLessonsForTeacher(appContext, user);
                    break;
                case 9:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        }
    }



}
