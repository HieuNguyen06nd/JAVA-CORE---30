package BtThem;

import BtThem.entities.Client;
import BtThem.entities.Receipt;
import BtThem.service.ClientService;
import BtThem.service.ReceiptService;
import BtThem.views.Menu;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        ArrayList<Receipt> receipts = new ArrayList<>();
        ArrayList<Client> clients = new ArrayList<>();

        Menu menu = new Menu();

        while (true){
            menu.displayMenu(scanner,clients,receipts);

        }

    }
}
