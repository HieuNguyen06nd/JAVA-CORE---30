package Lab03.service;

import Lab03.entities.Worker;

import java.util.ArrayList;
import java.util.Scanner;

public class WorkerService {

    public Worker inputWorker(Scanner scanner){
        System.out.println("nhap ten");
        String name = scanner.nextLine();

        System.out.println("nhap tuoi");
        int age = Integer.parseInt(scanner.nextLine());

        System.out.println("nhap luong");
        double salary = Double.parseDouble(scanner.nextLine());

        System.out.println("nhap workPlace");
        String workPlace = scanner.nextLine();

        return new Worker(name,age, salary, workPlace);
    }
    public Worker findById(int id, ArrayList<Worker> workers){
        for (Worker worker: workers){
            if (worker.getId() ==id){
                return worker;
            }
        }
        return null;
    }
    public void updateSalary(String status, Scanner scanner, ArrayList<Worker>workers){
        System.out.println("nhap id");
        int id = Integer.parseInt(scanner.nextLine());
        Worker worker = findById(id,workers);
        if (worker ==null){
            System.out.println("khong ton tai id "+ id);
        }else {
            if (status.equalsIgnoreCase("UP")){
                System.out.println("nhap luong tang");
                double salary = Double.parseDouble(scanner.nextLine());
                worker.setSalary(salary+ worker.getSalary());
            }else if (status.equalsIgnoreCase("DOWN")){
                System.out.println("nhap luong giam");
                double salary = Double.parseDouble(scanner.nextLine());
                worker.setSalary(salary+ worker.getSalary());
            }
        }
    }

}
