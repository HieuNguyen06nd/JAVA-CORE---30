package Lab06;


import Lab06.entities.Person;
import Lab06.views.Menu;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Person> people = new ArrayList<>();

        Menu menu = new Menu();
        while (true){
            menu.displayInfo(scanner,people);
        }
    }
}
