package manageHospital;

import java.util.Date;
import java.util.Scanner;

public class ManageHospital {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

//        Patient patient = inputInfoPatient(scanner);
//        Doctor doctor = inputInfoDoctor(scanner);

        System.out.println("Nhap so bac si");
        int n = Integer.parseInt(scanner.nextLine());
        Doctor[] doctors = new Doctor[n];
        for (int i = 0; i < doctors.length; i++) {
            doctors[i]= inputInfoDoctor(scanner);
        }

        System.out.println("Nhap so benh nhanh");
        n = Integer.parseInt(scanner.nextLine());
        Patient[] patients = new Patient[n];
        for (int i = 0; i < patients.length; i++) {
            patients[i]= inputInfoPatient(scanner);
        }

        printInfo(patients);
        printInfo(doctors);


    }
    public static Patient inputInfoPatient(Scanner scanner){
        System.out.println("nhap ten benh nhan");
        String name = scanner.nextLine();

        System.out.println("nhap tuooi");
        int age = Integer.parseInt(scanner.nextLine());

        System.out.println("Nhap so benh an");
        int medicalRecordNumber = Integer.parseInt(scanner.nextLine());


        return new Patient(name, age, medicalRecordNumber);
    }
    public static Doctor inputInfoDoctor(Scanner scanner){
        System.out.println("nhap ten bac si");
        String name = scanner.nextLine();

        System.out.println("nhap tuooi");
        int age = Integer.parseInt(scanner.nextLine());

        System.out.println("chuyen khoa");
        String specialty = scanner.nextLine();

        System.out.println("nhap so gio lam viec");
        int numberOfWorking = Integer.parseInt(scanner.nextLine());

        return new Doctor(name, age, specialty, numberOfWorking);
    }

    public  static void printInfo(Object[] objects){
        for (Object i : objects){
            System.out.println(i);
        }
    }
}
