import entities.Category;
import entities.Student;
import entities.User;
import enums.Role;
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

        MenuCustomer menu = new MenuCustomer();
        while (true){
            menu.displayMenu(scanner,users);
        }
    }
}