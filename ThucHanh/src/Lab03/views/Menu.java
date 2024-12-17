package Lab03.views;

import Lab03.entities.Worker;
import Lab03.service.WorkerService;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    WorkerService workerService =new WorkerService();
    public void displayMenu(Scanner scanner, ArrayList<Worker> workers){
        System.out.println("1. Them cong nhanh\n" +
                "2. in danh sach cong nhan \n" +
                "3. tang luong + id \n" +
                "4. giam luong + id \n");
        System.out.println("moi lua chon");
        selectMenu(scanner, workers);
    }

    public void selectMenu(Scanner scanner, ArrayList<Worker>workers){
        int choose = Integer.parseInt(scanner.nextLine());
        switch (choose){
            case 1:
                Worker worker = workerService.inputWorker(scanner);
                workers.add(worker);
                break;
            case 2:
                System.out.println(workers);
                break;
            case 3:
                workerService.updateSalary("UP",scanner,workers);
                break;
            case 4:
                workerService.updateSalary("DOWN",scanner,workers);
                break;
            default:
                System.out.println("Lua chon sai");

        }
    }
}
