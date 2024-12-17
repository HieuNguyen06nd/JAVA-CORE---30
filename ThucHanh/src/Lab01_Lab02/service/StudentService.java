package Lab01_Lab02.service;

import Lab01_Lab02.entities.Student;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentService {
    public Student inputStudent(Scanner scanner){
        System.out.println("nhap ten");
        String name = scanner.nextLine();

        System.out.println("nhap dia chi");
        String address = scanner.nextLine();

        System.out.println(" nhap diem toan");
        double scoreMath = Double.parseDouble(scanner.nextLine());

        System.out.println("nhap diem hoa hoc");
        double scorePhysic = Double.parseDouble(scanner.nextLine());

        return new Student(name, address, scoreMath, scorePhysic);
    }

    public double percentScore(String type, ArrayList<Student> students){
        int count=0;
        for (Student student : students){
            if (student.checkScore().equalsIgnoreCase(type)){
                count++;
            }
        }
        double tb= (double) count /(students.size()) *100;
        return tb;
    }

}
