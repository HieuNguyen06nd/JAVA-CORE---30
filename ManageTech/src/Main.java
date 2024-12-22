import entities.User;
import enums.Role;
import view.Menu;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<User> users = new ArrayList<>();

        User user = new User("admin", "Admin1.", "admin@gmail.com", Role.ADMIN);
        users.add(user);

        Menu menu = new Menu();

        while (true){
            menu.displayMenu(scanner,users);
        }

    }
}
