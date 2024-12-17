package Lab06.service;

import Lab06.entities.Person;

import java.util.ArrayList;
import java.util.Scanner;

public class PersonService {

    public Person login(Scanner scanner, ArrayList<Person>people){
        System.out.println("nhap Username: ");
        String name = scanner.nextLine();

        System.out.print("nhap Password: ");
        String password = scanner.nextLine();

        for (Person person:people){
            if (person.getName().equals(name) && person.getPassword().equals(password)){
                return person;
            }
        }
        return null;
    }
}
