import entities.*;
import enums.Role;
import view.MenuAdmin;
import view.MenuCustomer;

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
        ArrayList<Courses>courses =new ArrayList<>();
        ArrayList<Teacher>teachers =new ArrayList<>();

        Teacher teacher = new Teacher("anhha@gmail.com", "teacher","0982234222","Anhhieu1." ,3, "ass");
        teachers.add(teacher);
        MenuCustomer menuCustomer = new MenuCustomer();
        MenuAdmin menuAdmin =new MenuAdmin();
        while (true){
//            menuCustomer.displayMenu(scanner,users);
            menuAdmin.displayAdmin(scanner, users,teachers,courses);
        }
    }
}