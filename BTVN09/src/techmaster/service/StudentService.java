package techmaster.service;

import techmaster.entities.Student;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentService {
    public  Student inputStudent(Scanner scanner){
        System.out.println("nhap id hoc sinh");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("nhap ten hoc sinh");
        String name = scanner.nextLine();
        System.out.println("nhap tuoi ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.println("nhap hoc luc");
        String hocLuc = scanner.nextLine();
        return new Student(id,name,age,hocLuc);
    }

    public Student findById( int id, ArrayList<Student>students){
        for (Student student: students){
            if (student.getId() ==id){
                return student;
            }
        }
        return null;
    }
    public void updateHocLuc(Scanner scanner, ArrayList<Student>students){
        System.out.println("nhap id hoc sinh ");
        int id = Integer.parseInt(scanner.nextLine());
        boolean check = false;

        Student student = findById(id, students);

        if (student == null){
            System.out.println("khong tim thay hoc sinh id: "+id);
        }else {


        }
    }

    public void deleteStudent(Scanner scanner,ArrayList<Student>students){
        System.out.println("nhap id hoc sinh ");
        int id = Integer.parseInt(scanner.nextLine());
        boolean check = false;
        for (Student student : students){
            if (student.getId() ==id){
                students.remove(student);
                check=true;
                System.out.println("xoa thanh cong");
                break;
            }
        }
        if (!check){
            System.out.println("khong tim thay hoc sinh id: "+id);
        }
    }

}
