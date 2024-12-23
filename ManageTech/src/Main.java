import entities.Course;
import entities.Student;
import entities.Teacher;
import entities.User;
import enums.Mode;
import enums.Role;
import service.AppContext;
import view.Menu;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AppContext appContext = AppContext.getInstance();

        User user = new User("admin", "Admin1.", "admin@gmail.com", Role.ADMIN);
        Student user1 = new Student("admin1", "Admin1.", "admin@gmail.com", Role.STUDENT, "asddaaas");
        appContext.getUsers().add(user);
        appContext.getUsers().add(user1);

        Course course = new Course("cou", "askuhdhjalksdj",1222, Mode.OFFLINE);
        appContext.getCourses().add(course);


        Menu menu = new Menu();

        while (true){
            menu.displayMenu();
        }

    }
}
