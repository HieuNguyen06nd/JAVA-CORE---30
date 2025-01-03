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
        List<User> users = context.getList(User.class);
        int choose = utils.inputInt(scanner, " Mời lựa chọn...");
        Teacher teacher = (Teacher) userService.findById(user.getId(),users);
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
                lessonService.inputLesson(context);
                break;
            case 7:
                teacherService.printTeacherSalary(teacher);
                break;
            case 8:
                blogService.inpuBlog(context,user);
                break;
            case 9:
                return;
            case 99:

            case 0:
                System.exit(1);
            default:
                System.out.println("Lựa chọn không hợp lệ");
        }
    }



}
