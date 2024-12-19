package BtThem.service;

import BtThem.entities.Client;
import BtThem.entities.Receipt;
import Lab06.entities.fptShop.Customer;

import java.util.ArrayList;
import java.util.Scanner;

public class ReceiptService {

    ClientService clientService = new ClientService();
    public Receipt inputReceipt(Scanner scanner, ArrayList<Client>clients){
//        Client client = clientService.inputClient(scanner);

        System.out.println("nhap oldIndex");
        double oldIndex = Double.parseDouble(scanner.nextLine());

        System.out.println("nhap newIndex");
        double newIndex = Double.parseDouble(scanner.nextLine());

        System.out.println("nhap id khach hang ");
        String id = scanner.nextLine();
        String choose;

        if (checkID(id,clients)){
            return new Receipt(oldIndex, newIndex, id);
        }else {
            System.out.println("Ma khach hang sai");
        }
//        System.out.println("Ban co muon thu lai ?");
//        choose = scanner.nextLine();
        return null;

    }
    public boolean checkID(String id,ArrayList<Client> clients){
        for (Client checkId : clients){
            if (checkId.getId().equals(id)){
                return true;
            }
        }
        return false;
    }

    public void  printInfo(ArrayList<Receipt>receipts, ArrayList<Client> clients){
        for (Receipt receipt: receipts){
            Customer customer = clientService.findById(receipt.getId_Client(), clients);
            System.out.println(" hoa don" +
                    "cutomer: "+ );
        }

    }
}
