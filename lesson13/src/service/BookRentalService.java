package service;

import entities.Book;
import entities.BookRental;
import entities.User;
import utils.Validate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class BookRentalService {
    Validate validate = new Validate();
    public BookRental inputRental(Scanner scanner, ArrayList<Book>books, ArrayList<User>users){

        String user_id;
        while (true){
            System.out.println(" nhap id khach hang thue");
            user_id = scanner.nextLine();
            if (!validate.existUser(user_id, users)){
                System.out.println("user_id khong ton tai");
            }else {
                break;
            }
        }

        System.out.println("Nhap so sach muon thue");
        int number= Integer.parseInt(scanner.nextLine());

        ArrayList<String> bookList = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            String book_id;
            while (true) {
                System.out.print("Nhập ID sach " + (i + 1) + ": ");
                book_id = scanner.nextLine();
                if (validate.existBook(book_id,books)){
                    bookList.add(book_id);
                    break;
                }else {
                    System.out.println(" id sach k hojp le hoac k ton tai");
                }

            }
        }
        System.out.print("nhap ngay tra (yyyy-MM-dd): ");
        String input = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate paymentDate = LocalDate.parse(input, formatter);

        return  new BookRental(user_id,bookList,paymentDate);
    }
}
