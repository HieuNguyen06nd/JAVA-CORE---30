import entities.*;
import enums.Role;
import view.MenuAdmin;
import view.Menu;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<User> users =new ArrayList<>();
        User user = new User("anhha@gmail.com", "Anhhieu1","0982234222","Anhhieu1." , Role.ADMIN);
        users.add(user);

        ArrayList<Category> categories = new ArrayList<>();
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Lesson> lessons =new ArrayList<>();
        ArrayList<Teacher>teachers =new ArrayList<>();
        ArrayList<Courses>courses = new ArrayList<>();
        ArrayList<ClassRoom>classRooms= new ArrayList<>();
        ArrayList<Blog>blogs =new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse("1995-02-09", formatter);
        LocalDate endDate = LocalDate.parse("1992-09-09", formatter);
//        courses.add(new Courses("cou 1", "ashdk asjd", 1000, "KH2", startDate, endDate));


        Teacher teacher = new Teacher("anhha@gmail.com", "teacher","0982234222","Anhhieu1." ,3, "ass");
        users.add(teacher);

        Student student = new Student("student@gmail.com", "stu", "0123456798","Anhhieu1.","2");
        users.add(student);
        Student student1 = new Student("student@gmail.com", "stu1", "0123456798","Anhhieu1.","2");
        users.add(student1);



        Menu menu = new Menu();
        while (true){
            menu.displayMenu(scanner,users,lessons,user, classRooms, blogs,courses);
//            menuAdmin.displayAdmin(scanner, users,teachers,courses);
        }
    }
}