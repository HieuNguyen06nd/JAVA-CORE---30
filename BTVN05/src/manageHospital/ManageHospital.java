package manageHospital;

import java.util.Date;
import java.util.Scanner;

public class ManageHospital {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Patient patient = inputInfoPatient(scanner);
        Doctor doctor = inputInfoDoctor(scanner);
        System.out.println(patient);
        System.out.println(doctor);

    }
    public static Patient inputInfoPatient(Scanner scanner){
        System.out.println("nhap ten benh nhan");
        String name = scanner.nextLine();

        System.out.println("nhap tuooi");
        int age = Integer.parseInt(scanner.nextLine());

        System.out.println("Nhap so benh an");
        int medicalRecordNumber = Integer.parseInt(scanner.nextLine());

        System.out.println("nhap ngay vao vien yyyy/MM/dd");
        String dateOfAdmission = scanner.nextLine();

        return new Patient(name, age, medicalRecordNumber, dateOfAdmission);
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
}
