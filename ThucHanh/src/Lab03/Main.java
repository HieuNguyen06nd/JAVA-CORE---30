package Lab03;

import Lab03.entities.Worker;
import Lab03.views.Menu;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Worker> workers= new ArrayList<>();

        Menu menu = new Menu();

        while (true){
            menu.displayMenu(scanner,workers);
        }
    }
}
