package ManageHouse.service;

import ManageHouse.entities.Menber;

import java.util.Scanner;

public class MemberService {
    public Menber inputMember(Scanner scanner){
            System.out.println("nhap id");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.println("nhap ten");
            String name = scanner.nextLine();
            System.out.println("nhap dob");
            String dob = scanner.nextLine();
            System.out.println("nhap job");
            String job = scanner.nextLine();
            return new Menber(id, name, dob, job);
    }
}
