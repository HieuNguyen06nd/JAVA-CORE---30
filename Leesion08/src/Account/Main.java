package Account;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Account account = new Account("hieu", 12122, "ashk@gmail" , 100000);
        System.out.println(account.displayInfo());

        System.out.println("Change balane: " +account.recharge(1000));

        System.out.println( "Change Email: \n" + account.changeEmail("Hieunt@gmail.com"));


        Scanner scanner = new Scanner(System.in);
        System.out.println("moi nhao so account");
        int n = Integer.parseInt(scanner.nextLine());
        Account[]  accounts = new Account[n];
        for (int i = 0; i <accounts.length ; i++) {
            accounts[i] = inputAccount(scanner);
        }
        for (int i = 0; i < accounts.length; i++) {
           accounts[i].displayInfo();
           System.out.println( accounts[i].displayInfo());
        }

    }
    public static Account inputAccount(Scanner scanner){
        System.out.println("Nhap ten tk");
        String name = scanner.nextLine();

        System.out.println("Nhap stk");
        int accountNumber = Integer.parseInt(scanner.nextLine());

        System.out.println("Nhap balane");
        double balane = Double.parseDouble(scanner.nextLine());

        System.out.println("Nhap Email");
        String email = scanner.nextLine();

        return new Account(name,accountNumber,email,balane);
    }
}
