package BtThem.service;

import BtThem.entities.Client;
import BtThem.entities.Receipt;

import java.util.ArrayList;
import java.util.Scanner;

public class ClientService {

    public Client inputClient(Scanner scanner){
        System.out.println("nhap ten");
        String name = scanner.nextLine();

        System.out.println("nhap homeNumber");
        String homeNumber = scanner.nextLine();

        System.out.println("nhap meterCode");
        String meterCode = scanner.nextLine();

        return new Client(name,homeNumber,meterCode);
    }

    public Client findById(String id, ArrayList<Client> clients){
        for (Client client: clients){
            if (client.getId().equals(id)){
                return client;
            }
        }
        return null;
    }
}
