package Lab06.service.fptSchoolService;

import Lab06.entities.fptSchool.Teacher;
import Lab06.enums.Major;

import java.util.Scanner;

public class TeacherService {
    public Teacher inputTeacher(Scanner scanner){
        System.out.println("Nhập tên ");
        String name = scanner.nextLine();

        System.out.println("Nhập password");
        String password= scanner.nextLine();

        System.out.println("Nhập phone");
        String phone= scanner.nextLine();

        System.out.println("Nhập address");
        String address= scanner.nextLine();

        System.out.println("Nhập email");
        String email= scanner.nextLine();

        System.out.println("Nhập môn học ");
        Major major= Major.valueOf(scanner.nextLine());

        return new Teacher(address,name,password,phone,email,major);
    }
}
