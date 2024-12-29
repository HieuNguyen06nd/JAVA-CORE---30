import data.DataService;
import entities.Course;
import entities.Student;
import entities.Teacher;
import entities.User;
import enums.Mode;
import enums.Role;
import service.AppContext;
import service.PrintService;
import view.Menu;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AppContext appContext = AppContext.getInstance();

        DataService dataService = new DataService();
        dataService.data(appContext);

        Menu menu = new Menu();

        while (true){
            menu.displayMenu();
        }

    }
}
