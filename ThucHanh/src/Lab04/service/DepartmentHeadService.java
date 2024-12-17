package Lab04.service;

import Lab04.entities.DepartmentHead;
import Lab04.entities.MarketingStaff;

import java.util.Scanner;

public class DepartmentHeadService {
    public DepartmentHead inputDepartmentHead(Scanner scanner){
        System.out.println("nhap ten ");
        String name = scanner.nextLine();

        System.out.println("nhap luong ");
        int salary = Integer.parseInt(scanner.nextLine());

        System.out.println("nhap luong trach nhiem");
        double responsibilitySalary = Double.parseDouble(scanner.nextLine());

        return new DepartmentHead(name,salary,responsibilitySalary);
    }

}
