package Lab04.service;

import Lab04.entities.Person;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class PersonService {

    public void findBySalary(Scanner scanner, ArrayList<Person> people){
        System.out.println("nhap muc luong can tim");
        double salary = Double.parseDouble(scanner.nextLine());
        boolean check = false;
        for (Person person: people){
            if (person.getSalary() >= salary){
                System.out.println(person);
                check =true;
                break;
            }
        }
        if (!check){
            System.out.println("Khong co nhan vien luong = "+salary);
        }
    }
    public void deleteById(Scanner scanner, ArrayList<Person>people){
        System.out.println("nhap id nhan vien can xoa");
        int id = Integer.parseInt(scanner.nextLine());

        Person person = findById(id,people);
        if (person ==null){
            System.out.println("khong ton tai id: "+id);
        }else {
            people.remove(person);
        }
    }
    public void updateById(Scanner scanner, ArrayList<Person>people){
        System.out.println("nhap id nhan vien can update");
        int id = Integer.parseInt(scanner.nextLine());

        Person person = findById(id,people);
        if (person ==null){
            System.out.println("khong ton tai id: "+id);
        }else {
            System.out.print("Cap nhat ten: ");
            person.setName(scanner.nextLine());
            System.out.print("Cap nhat luong: ");
            person.setSalary(Double.parseDouble(scanner.nextLine()));
        }
    }

    public Person findById(int id, ArrayList<Person> people){

        for (Person person: people){
            if (person.getId() ==id){
                return person;
            }
        }
        return null;
    }

    public void sortName(ArrayList<Person>people){
           people.sort(Comparator.comparing(Person::getName));
    }

    public void top5(ArrayList<Person> people){
        people.sort(Comparator.comparing(Person::calSalary).reversed());
        for (int i = 0; i <Math.min(5, people.size()); i++) {
        }
        System.out.println(people);
    }

}
