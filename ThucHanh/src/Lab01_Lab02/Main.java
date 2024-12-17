package Lab01_Lab02;

import Lab01_Lab02.entities.Student;
import Lab01_Lab02.views.Menu;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Student> students = new ArrayList<>();

        Menu menu = new Menu();

        while (true){
            menu.displayMenu(scanner,students);
        }

    }
}
