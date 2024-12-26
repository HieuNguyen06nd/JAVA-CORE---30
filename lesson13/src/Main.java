import entities.Book;
import entities.BookRental;
import entities.User;
import menu.Menu;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<User> users = new ArrayList<>();
        ArrayList<BookRental>bookRentals= new ArrayList<>();

        users.add(new User("hieu", "123", "ash ahs"));

        ArrayList<Book>books = new ArrayList<>();
        books.add(new Book("sach 1", " nguey van a", 123));

        Menu menu = new Menu();

        while (true){
           menu.displayMenu(scanner,books, users, bookRentals);
        }
    }
}
