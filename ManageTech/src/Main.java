import data.Data;
import data.DataService;
import entities.User;
import service.AppContext;
import view.Menu;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        AppContext appContext = AppContext.getInstance();

        ArrayList<User> users = appContext.getUsers();
        DataService dataService = new DataService();
        dataService.data(appContext);

        Data data = new Data();

        Menu menu = new Menu();

        while (true){
            menu.displayMenu();
            data.writeUsersToJsonFile(users, "data.txt");
            ArrayList<User> loadedUsers = (ArrayList<User>) data.readUsersFromJsonFile("data.txt");

            System.out.println(loadedUsers);
        }

    }
}
