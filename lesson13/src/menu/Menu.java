package menu;

import entities.Book;
import entities.BookRental;
import entities.User;
import service.BookRentalService;
import service.BookService;
import service.UserService;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    BookService bookService = new BookService();
    UserService userService = new UserService();
    BookRentalService bookRentalService = new BookRentalService();
    public void displayMenu(Scanner scanner, ArrayList<Book>books, ArrayList<User>users,ArrayList<BookRental>bookRentals){
        while (true){
            System.out.println("Menu book");
            System.out.println("1. Them sach moi");
            System.out.println("2. Sua sach ");
            System.out.println("3. Xoa sach");
            System.out.println("4. Them user thue sach");
            System.out.println("5. Tao hoa don thu sach");
            System.out.println("6. hien thu sach");
            System.out.println("7. hien thi don hang");
            selectMenu(scanner,books,users, bookRentals);
        }

    }
    public  void selectMenu(Scanner scanner, ArrayList<Book>books, ArrayList<User>users, ArrayList<BookRental>bookRentals){
        System.out.println(" moi lua chon");
        int choose =Integer.parseInt(scanner.nextLine());
        switch (choose){
            case 1:
                books.add(bookService.inputBook(scanner));
                break;
            case 2:
                bookService.updateBook(scanner,books);
                break;
            case 3:
                bookService.daleteBook(scanner,books);
                break;
            case 4:
                users.add(userService.inputUser(scanner));
                break;
            case 5:
                bookRentals.add(bookRentalService.inputRental(scanner,books, users));
                break;
            case 6:
                System.out.println(books);
                break;
            case 7:
                System.out.println(users);
                break;
            case 8:
                System.out.println(bookRentals);
                break;
            case 0:
                return;
            default:
                System.out.println("lua chon k hojp le");
        }
    }
}
