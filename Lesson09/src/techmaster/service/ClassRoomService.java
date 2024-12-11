package techmaster.service;

import techmaster.entities.ClassRoom;
import techmaster.entities.Student;

import java.util.ArrayList;
import java.util.Scanner;

public class ClassRoomService {
    Student student = new Student();
    static StudentService service = new StudentService();
    static ArrayList<Student> students = new ArrayList<>();
    public  ClassRoom inputClass(Scanner scanner){
        System.out.println("nhap ten mon hoc");
        String subject = scanner.nextLine();
        System.out.println("nhap so hoc sinh");
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            students.add(service.inputStudent(scanner));
        }
        return new ClassRoom(subject,students);
    }

}
