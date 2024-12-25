import data.DataService;
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

//        User user = new User("admin", "Admin1.", "admin@gmail.com", Role.ADMIN);
//        appContext.getUsers().add(user);

        DataService dataService = new DataService();
        dataService.data(appContext);


        Menu menu = new Menu();

        while (true){
            menu.displayMenu();
        }

    }
}
