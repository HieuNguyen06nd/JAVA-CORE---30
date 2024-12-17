package Lab04.service;

import Lab04.entities.AdministrativeStaff;
import Lab04.entities.Person;

import java.util.Scanner;

public class AdministrativeStaffService {

    public AdministrativeStaff inputAdministrative(Scanner scanner){
        System.out.println("nhap ten ");
        String name = scanner.nextLine();

        System.out.println("nhap luong ");
        int salary = Integer.parseInt(scanner.nextLine());

        return new AdministrativeStaff(name,salary);
    }
    public double calSalary(AdministrativeStaff administrativeStaff){
        return administrativeStaff.getSalary();
    }
}
