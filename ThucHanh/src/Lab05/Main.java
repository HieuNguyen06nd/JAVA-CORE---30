package Lab05;

import Lab05.entities.Pet;
import Lab05.enums.TYPE;
import Lab05.service.PetService;
import Lab05.view.Menu;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Menu menu =new Menu();

        ArrayList<Pet> pets = menu.addPets();

        menu.displayMenu(scanner,pets);


    }
}
