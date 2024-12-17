package Lab04.service;

import Lab04.entities.MarketingStaff;

import java.util.Scanner;

public class MarketingStaffService {
    public MarketingStaff inputMarketing(Scanner scanner){
       System.out.println("nhap ten ");
       String name = scanner.nextLine();

       System.out.println("nhap luong ");
       int salary = Integer.parseInt(scanner.nextLine());

       System.out.println("nhap doanh so");
       double sale = Double.parseDouble(scanner.nextLine());

       System.out.println("nhap ty le hoa hong");
       double rate = Double.parseDouble(scanner.nextLine());

       return new MarketingStaff(name,salary,sale,rate);
    }



}
