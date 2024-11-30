package BTVN;

import java.time.LocalTime;
import java.util.Scanner;

public class main {
    static String userName = "admin";
    static String passWord = "admin";
    static String choose="";



    public static void main(String[] args) {
        double balance = 1000000;
        int choice ;
        String choose="";
        Scanner scanner = new Scanner(System.in);
        if (login() ==true) {
            do {
                System.out.println("Chọn \n" +
                        "1. Xem thông tin tài khoản \n" +
                        "2. Rút tiền\n");
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        System.out.println("username: " + userName + "Password: " + passWord + "balance: " + balance);
                        System.out.println("Do you want to continue? (Y/N)");
                        choose = scanner.nextLine();
                        if (choose.equalsIgnoreCase("n")){
                            System.exit(1);
                        }
                    case 2:
                        System.out.println("Nhập số tiền muốn rút:");
                        double money = Double.parseDouble(scanner.nextLine());
                        System.out.println("Ban đã rút " + money + " Vào lúc " + LocalTime.now() + " Số tiền còn lại : " + (balance - money));
                }

                System.out.println("Do you want to continue? (Y/N)");
                choose = scanner.nextLine();

            } while (choose.equalsIgnoreCase("y"));
        }

    }

    public  static  boolean login() {
        Scanner scanner = new Scanner(System.in);
        String checkLogin ="";
        do {
            System.out.print("Enter username: ");
            String userName = scanner.next();

            System.out.print("Enter password: ");
            String passWord = scanner.next();
            if (userName.equals("admin") && passWord.equals("admin")) {
                return true;
            } else {
                System.out.println("Sai tài khoản hoặc mật khẩu! ");
                checkLogin = scanner.nextLine();
                if (checkLogin.equalsIgnoreCase("n")){
                    System.exit(1);
                }
            }
            System.out.println("Do you want to continue? (Y/N)");
            checkLogin = scanner.nextLine();
        }while (checkLogin.equalsIgnoreCase("y"));

        return false;
    }
}
