package BtThem.service;

import BtThem.entities.Client;
import BtThem.entities.Receipt;

import java.util.Scanner;

public class ReceiptService {

    ClientService clientService = new ClientService();
    public Receipt inputReceipt(Scanner scanner){
        Client client = clientService.inputClient(scanner);

        System.out.println("nhap oldIndex");
        double oldIndex = Double.parseDouble(scanner.nextLine());

        System.out.println("nhap newIndex");
        double newIndex = Double.parseDouble(scanner.nextLine());

        return new Receipt(client,oldIndex,newIndex);
    }
}
