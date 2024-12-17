package Lab01_Lab02.views;

import Lab01_Lab02.entities.Student;
import Lab01_Lab02.service.StudentService;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    StudentService studentService = new StudentService();
    public void displayMenu(Scanner scanner, ArrayList<Student>students){
        System.out.println("1. Them hoc sinh\n" +
                "2. in danh sach hoc sinh \n" +
                "3. % hoc sinh loai (A/B/C)");
        System.out.println("moi lua chon");
        selectMenu(scanner,students);
    }
    public void selectMenu(Scanner scanner, ArrayList<Student>students){
        int choose = Integer.parseInt(scanner.nextLine());
        switch (choose){
            case 1:
                Student student = studentService.inputStudent(scanner);
                students.add(student);
                break;
            case 2:
                System.out.println(students);
                break;
            case 3:
                System.out.println("nhap diem muon tinh(A/B/C)");
                String diem = scanner.nextLine();
                studentService.percentScore(diem,students);
                System.out.println(studentService.percentScore(diem,students) + "%");

        }
    }
}
