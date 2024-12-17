package BtThem.views;

import BtThem.entities.Client;
import BtThem.entities.Receipt;
import BtThem.service.ClientService;
import BtThem.service.ReceiptService;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    ClientService clientService = new ClientService();
    ReceiptService receiptService = new ReceiptService();

    public void displayMenu(Scanner scanner , ArrayList<Client> clients, ArrayList<Receipt> receipts){
        System.out.println("1. nhập thông tin riêng của mỗi hộ sử dụng điện\n" +
                "2. hiển thị một thông tin riêng của mỗi hộ sử dụng điện\n" +
                "3. nhap biên lai \n" +
                "4. hien thi bien lai\n");
        System.out.println("moi lua chon");
        selectMenu(scanner,clients,receipts);
    }
    public void selectMenu(Scanner scanner, ArrayList<Client> clients, ArrayList<Receipt> receipts){
        int choose = Integer.parseInt(scanner.nextLine());
        switch (choose){
            case 1:
                Client client = clientService.inputClient(scanner);
                clients.add(client);
                break;
            case 2:
                System.out.println(clients);
                break;
            case 3:
                Receipt receipt= receiptService.inputReceipt(scanner);
                receipts.add(receipt);
                break;
            case 4:
                System.out.println(receipts);
                break;
            default:
                System.out.println("lua chon k hop le");
        }
    }
}
